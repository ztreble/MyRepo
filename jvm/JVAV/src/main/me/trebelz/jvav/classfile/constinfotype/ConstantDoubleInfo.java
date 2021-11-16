package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

/**
 * @author treblez
 */
public class ConstantDoubleInfo  implements ConstantInfo{
    public double val;

    @Override
    public void readInfo(ClassReader reader){
        val = reader.readUint64();
    }
    public double value(){
        return val;
    }
}