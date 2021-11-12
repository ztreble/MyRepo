package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

public class ConstantLongInfo implements ConstantInfo{
    public long val;

    @Override
    public void readInfo(ClassReader reader){
        val = reader.readUint64();
    }
    public long value(){
        return val;
    }
}