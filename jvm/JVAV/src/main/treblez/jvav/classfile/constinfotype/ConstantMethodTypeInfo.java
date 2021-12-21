package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantMethodTypeInfo implements ConstantInfo{
    public char descriptorIndex;

    @Override
    public void readInfo(ClassReader reader){
        descriptorIndex = reader.readUint16();
    }
}
