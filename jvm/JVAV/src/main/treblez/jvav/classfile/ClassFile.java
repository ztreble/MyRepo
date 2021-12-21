package treblez.jvav.classfile;

import treblez.jvav.classfile.attributetable.AttributeInfo;

/**
 * @author treblez
 * @Description class文件定义和解析
 */
public class ClassFile {


    /**
     * 结构体字段
     */
    public int minorVersion;
    public int majorVersion;
    public ConstantPool constantPool;
    public int accessFlags;
    public char thisClass;
    public char superClass;
    public char[] interfaces;
    public MemberInfo[] fields;
    public MemberInfo[] methods;
    public AttributeInfo[] attributes;

    public ClassFile(byte[] classData) throws Exception {
        /**
         *
         */
        ClassReader cr = new ClassReader(classData);
        read(cr);
    }

//    static ClassFile parser(byte[] classData) throws Exception {
//        var cr = new ClassReader(classData);
//        var cf = new ClassFile();
//
//    }

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

    /**
     * 读取魔数 错误立即抛出
     * @param reader
     * @throws Exception
     */
    public void readAndCheckMagic(ClassReader reader) throws Exception {
        var magic = reader.readUint32();
        if(magic!=(int)0xCAFEBABE){
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
     * 类访问标志
     * 16位的bitmask，指出class文件定义的是类还是接口、
     * 访问级别
     * @return
     */


    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
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
    public String getClassName() throws Exception {
        return constantPool.getClassName(thisClass);
    }

    /**
     * 从常量池查找超类名
     * @return 名称
     */
    public String getSuperClassName() throws Exception {
        if(superClass>0){
            return constantPool.getClassName(superClass);
        }
        return "";
    }

    /**
     * 从常量池查找接口名
     * @return 接口名
     */
    public String[] getInterfaceNames() throws Exception {
        String[] ret = new String[interfaces.length];
        for(int i=0;i<interfaces.length;i++){
            ret[i] = constantPool.getClassName(interfaces[i]);
        }
        return ret;
    }
}
