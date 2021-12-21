package treblez.jvav.classfile.attributetable;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 * 用来标记类、字段、方法已经不建议使用
 */
/*
Deprecated_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
*/
public class DeprecatedAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {

    }
}
