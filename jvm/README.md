目标：
实现Java语言的子集，包括一个前端的Parser和后端的interpter。使用语言是java，目标是实现的feature尽可能的多。后端不涉及JIT、PGO优化，仅相当于一个(使用java解释class文件的)解释器。
大致的安排如下：
1. 实现搜索class文件
2. 实现class文件解析
3. 实现运行时数据区
4. 实现简单的解释器
5. 扩展解释器，实现方法调用、面向对象、异常处理等高级功能
6. 实现parser

可能出现的问题：
class文件的解析
如何在虚拟机中实现类和对象
如何实现异常处理

开发环境：
Windows10，IDEA

## Class文件搜索
**主要文件在classpath目录下，使用`jcommander`解析命令行参数。
`jcommander`的文档：[https://jcommander.org/](https://jcommander.org/)
从类路径中搜索类，java类路径分为三个部分：启动类路径，扩展类路径，用户类路径
类路径由用户使用命令行参数指定
执行顺序是 类路径初始化-->查找用户提供的类
Entry接口用来表示类路径项，组合实现DirEntry、ZipEntry、CompositeEntry和WildcardEntry四个类，DirEntry表示目录形式的类路径，ZipEntry用来表示zip或者jar形式的类路径，CompositeEntry用来表示文件分隔符分割多个文件的路径，WildcardEntry用来表示以`*`结尾指代目录下所有文件的情况。**
## Class文件解析
![在这里插入图片描述](https://img-blog.csdnimg.cn/108c14d523424991964061f97bb0f6c0.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAVHJlYmxlX3o=,size_20,color_FFFFFF,t_70,g_se,x_16)

**构成class文件的基本数据单位是字节，数据在class文件中以大端方式存储。
比较关键的是ClassReader类，用于辅助字节操作。**
```java
/**
 * @author treblez
 * @Description 辅助读取数据的类
 */
public class ClassReader {
    private final ByteBuffer buf;
    ClassReader(byte[] data){
        buf = ByteBuffer.allocate(data.length+5);
        buf.put(data);
        // 注意，清除标志位
        buf.rewind();
    }
    
    public byte readUint8() {
        return buf.get();
    }
    
    public char readUint16() {
        byte[] tmp = new byte[2];
        buf.get(tmp,0,2);
        return (char) (((tmp[0] & 0xFF) << 8) | (tmp[1] & 0xFF));
    }
    
    public int readUint32()  {
        byte[] tmp = new byte[4];
        buf.get(tmp,0,4);
        // 注意运算符优先级
        return  ((tmp[3]&0xff) |((tmp[2]&0xff) << 8) | ((tmp[1]&0xff)  << 16) | ((tmp[0]&0xff) << 24));
    }
    
    public long readUint64() {
        byte[] tmp = new byte[8];
        buf.get(tmp,0,8);
        return  (((long)(tmp[0] & 0xFF) << 56) | ((long)(tmp[1] & 0xFF) << 48) | ((long)(tmp[2] & 0xFF) << 40)
                | ((long)(tmp[3] & 0xFF) << 32) |
                (tmp[4] & 0xFF << 24) | (tmp[5] & 0xFF << 16) | (tmp[6] & 0xFF << 8) | (tmp[7] & 0xFF));
    }
    /**
     *读取uint16表，大小由开头的数据指定
      */
    public char[] readUint16s() {
        var n = readUint16();
        char[] s = new char[n];
        for(int i=0;i<n;i++){
            s[i] = readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n) {
        byte[] ret = new byte[n];
        buf.get(ret, 0, n);
        return ret;
    }
}
```
**字节流的读取顺序如下所示：**
```java
    void read(ClassReader reader) throws Exception {
    	// 验证魔数
        readAndCheckMagic(reader);
        // 验证版本号
        readAndCheckVersion(reader);
        // 读取常量池
        constantPool = new ConstantPool().readConstantPool(reader);
        //类访问标志 bitmask
        accessFlags = reader.readUint16();
        /*
         * 类和超类索引，thisClass必须是有效的常量池索引
         * superClass只在Object.class中是0，其它文件中必须有效
         */
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        //接口索引表，给出该类实现的所有接口的名字
        interfaces = reader.readUint16s();
        // 字段表
        fields = MemberInfo.readMembers(reader, constantPool);
        // 方法表
        methods = MemberInfo.readMembers(reader, constantPool);
        // 属性表
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }
```
**魔数的值必须为`0xCAFEBABE` 类、超类、接口表都以常量池索引的方式存放。
字段、方法、类都有使用`bitmask`实现的访问标志，访问标志后是常量池索引，给出字段或者方法的描述符，最后是属性表。
常量池中放着很多的常量信息，包括数字和字符串常量、类和接口名、字段和方法名等。以8比特无符号整数来标志常量类型：**
```java
int CONSTANT_CLASS = 7;
    int CONSTANT_FIELDREF = 9;
    int CONSTANT_METHODREF = 10;
    int CONSTANT_INTERFACE_METHODREF = 11;
    int CONSTANT_STRING = 8;
    int CONSTANT_INTEGER = 3;
    int CONSTANT_FLOAT = 4;
    int CONSTANT_LONG = 5;
    int CONSTANT_DOUBLE = 6;
    int CONSTANT_NAME_AND_TYPE = 12;
    int CONSTANT_UTF8 = 1;
    int CONSTANT_METHOD_HANDLE = 15;
    int CONSTANT_METHOD_TYPE = 16;
    int CONSTANT_INVOKE_DYNAMIC = 18;

    /**
     * 读取常量信息
     *
     * @param reader
     */
    void readInfo(ClassReader reader) throws IOException;

    /**
     * 读取tag值，new创建具体常量，然后调用readInfo读取常量信息
     * @param reader
     * @param cp
     * @return
     * @throws Exception
     */
    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp) throws Exception {
        var tag = reader.readUint8();
        ConstantInfo ret = switch (tag) {
            case CONSTANT_INTEGER -> new ConstantIntegerInfo();
            case CONSTANT_FLOAT -> new ConstantFloatInfo();
            case CONSTANT_LONG -> new ConstantLongInfo();
            case CONSTANT_DOUBLE -> new ConstantDoubleInfo();
            case CONSTANT_UTF8 -> new ConstantUtf8Info();
            case CONSTANT_STRING -> new ConstantStringInfo(cp);
            case CONSTANT_CLASS -> new ConstantClassInfo(cp);
            case CONSTANT_FIELDREF -> new ConstantFieldRefInfo(cp);
            case CONSTANT_METHODREF -> new ConstantMethodRefInfo(cp);
            case CONSTANT_INTERFACE_METHODREF -> new ConstantInterfaceMethodRefInfo(cp);
            case CONSTANT_NAME_AND_TYPE -> new ConstantNameAndTypeInfo();
            // 以下三条为了支持SE7 invokedynamic指令
            // 即：先在运行时动态解析出调用点限定符所引用的方法，然后再执行该方法
            case CONSTANT_METHOD_TYPE -> new ConstantMethodTypeInfo();
            case CONSTANT_METHOD_HANDLE -> new ConstantMethodHandleInfo();
            case CONSTANT_INVOKE_DYNAMIC -> new ConstantInvokeDynamicInfo();
            default -> throw new Exception("java.lang.ClassFormatError: constant pool tag!");
        };
        ret.readInfo(reader);
        return ret;
    }
```

**方法的字节码存放在属性表中，Deprecated（不建议使用）、Synthetic（源文件不存在）起标记作用，SourceFile指示源文件名，ConstantValue表示常量表达式的值，constantValue表示常量表达式的值，Code属性存放字节码等方法信息，Exceptions表示抛出的异常表，LineNumberTable和LocalVariableTable存放方法的行号和局部变量信息。**


## 实现运行时数据区
运行时数据区分为两个部分：
多线程共享的数据区（jvm启动时创建，退出时销毁）
线程私有的数据区（和线程一起创建销毁）


#### 多线程共享的数据区
包括两类数据：类数据和对象数据

类数据包括字段和方法信息，字节码，运行时常量池，存放在方法区中
对象数据存放在堆中



#### 线程私有的数据区
用于辅助执行java字节码
每个线程都有自己的pc寄存器，和java虚拟机栈
当当前方法为Java方法的时候，pc寄存器指向虚拟机指令的地址
否则当前方法为本地方法，pc寄存器中的值没有意义。
虚拟机栈由栈帧构成，帧中保存方法执行的状态，包括局部变量表，操作数栈等。
局部变量表和操作数栈的大小由编译器预先计算好，存放在method_info的code中.
Java虚拟机规范规定 每一个局部变量可以存放一个int值或者引用值




#### 类型系统
基本类型包括布尔类型和数字类型
数字类型可以分为整数类型和浮点数类型
引用类型包括类类型、接口类型、引用类型


