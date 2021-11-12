package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

import java.io.IOException;

/**
 * @author treblez
 */
public class ConstantNameAndTypeInfo implements ConstantInfo{
    public int nameIndex;
    public int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) throws IOException {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }
}
