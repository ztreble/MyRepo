package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantIntegerInfo implements ConstantInfo{
    public int val;

    @Override
    public void readInfo(ClassReader reader){
        val = reader.readUint32();
    }
    public int Value(){
        return val;
    }
}
