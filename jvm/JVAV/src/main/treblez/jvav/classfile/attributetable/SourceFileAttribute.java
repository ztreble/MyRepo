package treblez.jvav.classfile.attributetable;

import treblez.jvav.classfile.ClassReader;
import treblez.jvav.classfile.ConstantPool;

/**
 * @author treblez
 * 可选定长属性，用于指出源文件名
 *
 *
 */
/*
SourceFile_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 sourcefile_index;
}
*/
public class SourceFileAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int sourceFileIndex;
    public SourceFileAttribute(ConstantPool cp) {
        this.cp = cp;
    }
    @Override
    public void readInfo(ClassReader reader){
        this.sourceFileIndex = reader.readUint16();
    }
    public String fileName() throws Exception {
        return this.cp.getUtf8(sourceFileIndex);
    }

}
