package trebelz.jvav.classfile.attributetable;

import trebelz.jvav.classfile.ClassReader;
import trebelz.jvav.classfile.ConstantPool;

/**
 * @author treblez
 */
/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
*/
public interface AttributeInfo {
    /**
     * @param reader
     */
    void readInfo(ClassReader reader) throws Exception;

    /**
     *   读取属性表
     */
    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) throws Exception {
        var attributesCount = reader.readUint16();
        var attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    /**
     *   读取单个属性
     */
    static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) throws Exception {
        // 读取属性名索引
        var attrNameIndex = reader.readUint16();
        // 在常量池中寻找属性名
        var attrName = cp.getUtf8(attrNameIndex);
        // 寻找属性长度
        var attrLen = reader.readUint32();
        // 创建属性实体
        var attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }
    /**
     *   读取单个属性
     */
    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool cp) {
        return switch (attrName) {
            case "Code" -> new CodeAttribute(cp);
            case "ConstantValue" -> new ConstantValueAttribute();
            case "Deprecated" -> new DeprecatedAttribute();
            case "Exceptions" -> new ExceptionsAttribute();
            case "LineNumberTable" -> new LineNumberTableAttribute();
            case "LocalVariableTable" -> new LocalVariableTableAttribute();
            case "SourceFile" -> new SourceFileAttribute(cp);
            case "Synthetic" -> new SyntheticAttribute();
            default -> new UnparsedAttribute(attrName, attrLen, new byte[]{});
        };
    }
}
