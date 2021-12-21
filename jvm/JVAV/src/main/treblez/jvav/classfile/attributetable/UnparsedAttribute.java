package treblez.jvav.classfile.attributetable;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class UnparsedAttribute implements AttributeInfo{
    public String name;
    public int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen, byte[] bytes) {
        name = attrName;
        length = attrLen;
        info = bytes;
    }

    @Override
    public void readInfo(ClassReader reader){
        info = reader.readBytes(length);
    }
    public byte[] info(){
        return info;
    }
}
