package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;
import trebelz.jvav.classfile.ConstantPool;

import java.io.IOException;

/**
 * 类或接口的符号引用
 * @author treblez
 */
public class ConstantClassInfo implements ConstantInfo{
    public ConstantPool cp;
    public int nameIndex;
    ConstantClassInfo(ConstantPool cp){
        this.cp = cp;
    }
    @Override
    public void readInfo(ClassReader reader) throws IOException {
        nameIndex = reader.readUint16();
    }
    public String name() throws Exception {
        return cp.getUtf8(nameIndex);
    }
}
