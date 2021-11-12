package trebelz.jvav.classfile.attributetable;

import trebelz.jvav.classfile.ClassReader;
import trebelz.jvav.classfile.ConstantPool;

/**
 * @author treblez
 */
public interface AttributeInfo {
    /**
     *
     * @param reader
     */
    void readInfo(ClassReader reader);

    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp)  {
        var attributesCount = reader.readUint16();
        var attributes = new AttributeInfo[attributesCount];
        for(int i=0;i<attributesCount;i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) throws Exception {
        var attrNameIndex = reader.readUint16();
        var attrName = cp.getUtf8(attrNameIndex);
        var attrLen = reader.readUint32();
        var attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName,int attrLen,ConstantPool cp)  {
        switch(){
            case "Code":
                return &CodeAttribute{cp: cp}
            case "ConstantValue":
                return &ConstantValueAttribute{}
            case "Deprecated":
                return &DeprecatedAttribute{}
            case "Exceptions":
                return &ExceptionsAttribute{}
            case "LineNumberTable":
                return &LineNumberTableAttribute{}
            case "LocalVariableTable":
                return &LocalVariableTableAttribute{}
            case "SourceFile":
                return &SourceFileAttribute{cp: cp}
            case "Synthetic":
                return &SyntheticAttribute{}
            default:
                return &UnparsedAttribute{attrName, attrLen, nil}
        }
}
