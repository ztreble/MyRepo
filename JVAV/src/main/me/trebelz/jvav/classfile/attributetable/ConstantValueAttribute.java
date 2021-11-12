package trebelz.jvav.classfile.attributetable;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 * 用于表示常量表达式的值
 */
/*
ConstantValue_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 constantvalue_index;
}
*/
public class ConstantValueAttribute implements AttributeInfo {
    private int constValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constValueIndex = reader.readUint16();
    }

    public int getConstValueIndex(){
        return constValueIndex;
    }
}
