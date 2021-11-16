package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantFloatInfo implements ConstantInfo{
    public float val;

    @Override
    public void readInfo(ClassReader reader){
        val = reader.readUint32();
    }
    public float value(){
        return val;
    }
}