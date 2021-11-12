package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;
import trebelz.jvav.classfile.ConstantPool;

import java.io.IOException;

/**
 * @author treblez
 * 符号引用类
 */
public class ConstantMemberRefInfo implements ConstantInfo{
    public ConstantPool cp;
    public int classIndex;
    public int nameAndTypeIndex;
    ConstantMemberRefInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) throws IOException {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String className() throws Exception {
        return cp.getClassName(classIndex);
    }

}
