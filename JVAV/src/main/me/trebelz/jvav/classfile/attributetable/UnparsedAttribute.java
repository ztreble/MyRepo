package trebelz.jvav.classfile.attributetable;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class UnparsedAttribute implements AttributeInfo{
    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen, byte[] bytes) {

    }

    @Override
    public void readInfo(ClassReader reader){
        info = reader.readBytes(length);
    }
    public byte[] info(){
        return info;
    }
}
