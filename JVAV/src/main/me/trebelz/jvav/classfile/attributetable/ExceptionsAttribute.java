package trebelz.jvav.classfile.attributetable;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 * 记录方法抛出的异常表
 */
public class ExceptionsAttribute implements AttributeInfo {
    private char[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) throws Exception {
        exceptionIndexTable = reader.readUint16s();
    }
    public char[] exceptionIndexTable(){
        return exceptionIndexTable;
    }
}
