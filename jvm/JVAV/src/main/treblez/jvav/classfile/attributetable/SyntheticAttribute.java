package treblez.jvav.classfile.attributetable;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 * 用来标记源文件不存在、由编译器生成的类成员 主要是为了支持嵌套类和嵌套接口
 */

/*
Synthetic_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
}
*/
public class SyntheticAttribute implements AttributeInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }
}
