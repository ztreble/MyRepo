package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;
import treblez.jvav.classfile.ConstantPool;

import java.io.IOException;


/**
 * @author treblez
 * 本文件夹中解析常量池
 * 常量池中的常量分为两类：字面量和符号引用
 * 字面量包括数字常量和字符串常量
 * 符号引用包括类和接口名，字段，方法信息等
 * todo ugly code
 */

public interface ConstantInfo {
    //常量池标签
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
}
