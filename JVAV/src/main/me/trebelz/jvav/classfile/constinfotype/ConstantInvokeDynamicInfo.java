package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo{

    public char bootstrapMethodAttrIndex;
    public char nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader reader){
        bootstrapMethodAttrIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

}
