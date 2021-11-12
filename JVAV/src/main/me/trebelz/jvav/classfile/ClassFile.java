package trebelz.jvav.classfile;

/**
 * @author treblez
 * @Description class文件定义和解析
 */
public class ClassFile {


    /**
     * 结构体字段
     */
    public char minorVersion;
    public char majorVersion;
    public ConstantPool constantPool;
    public char accessFlags;
    public char thisClass;
    public char superClass;
    public char[] interfaces;
    public MemberInfo[] fields;
    public MemberInfo[] methods;
    public AttributeInfo[] attributes;

    ClassFile(byte[] classData) throws Exception {
        /**
         *
         */
        ClassReader cr = new ClassReader(classData);
        read(cr);
    }
    void read(ClassReader reader) throws Exception {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = readConstantPool(reader);
        //类访问标志
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
        attributes = readAttributes(reader, constantPool);
    }

    /**
     * 读取魔数 错误立即抛出
     * @param reader
     * @throws Exception
     */
    public void readAndCheckMagic(ClassReader reader) throws Exception {
        var magic = reader.readUint32();
        if(magic!=0xCAFEBABE){
            throw new Exception("java.lang.ClassFormatError: magic!");
        }
    }

    /**
     * 检查版本号
     * @param reader
     * @throws Exception
     */
    public void readAndCheckVersion(ClassReader reader) throws Exception {
        minorVersion = reader.readUint16();
        majorVersion = reader.readUint16();
        switch (majorVersion){
            case 45:
                return;
            case 46,47,48,49,50,51,52:
                if(minorVersion==0) {
                    return;
                }
            default:break;
        }
        throw new Exception("java.lang.UnsupportedClassVersionError!");
    }

    /**
     * 解析常量池
     * @return
     */
    public ConstantPool readConstantPool(ClassReader reader){



    }


    /**
     * 类访问标志
     * 16位的bitmask，指出class文件定义的是类还是接口、
     * 访问级别
     * @return
     */




    public char getMinorVersion() {
        return minorVersion;
    }

    public char getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public char getAccessFlags() {
        return accessFlags;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }
    /**
     * 从常量池查找类名
     */
    public String getClassName(){
        return constantPool.getClassName(thisClass);
    }

    /**
     * 从常量池查找超类名
     * @return 名称
     */
    public String getSuperClassName(){
        if(superClass>0){
            return constantPool.getClassName(superClass);
        }
        return "";
    }

    /**
     * 从常量池查找接口名
     * @return 接口名
     */
    public String[] getInterfaceNames(){
        String[] ret = new String[interfaces.length];
        for(int i=0;i<interfaces.length;i++){
            ret[i] = constantPool.getClassName(interfaces[i]);
        }
        return ret;
    }
}
