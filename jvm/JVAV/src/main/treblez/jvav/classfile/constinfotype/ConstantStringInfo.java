package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;
import treblez.jvav.classfile.ConstantPool;

/**
 * 不存放字符串数据，只存放常量池引用和索引
 * @author treblez
 */
public class ConstantStringInfo implements ConstantInfo{

    public ConstantPool cp;
    public int stringIndex;
    ConstantStringInfo(ConstantPool cp){
        this.cp = cp;
    }

    /**
     * 读取常量池索引
     * @param reader
     */
    @Override
    public void readInfo(ClassReader reader){
        stringIndex = reader.readUint16();
    }

    public String string() throws Exception {
        return cp.getUtf8(stringIndex);
    }
}
