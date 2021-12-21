package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantMethodHandleInfo implements ConstantInfo{
    public byte referenceKind;
    public char referenceIndex;

    @Override
    public void readInfo(ClassReader reader){
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }
}
