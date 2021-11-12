package trebelz.jvav.classfile.constinfotype;

import trebelz.jvav.classfile.ClassReader;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 解析字符串到utf-8
 * 在class文件中中字符串以MUTF-8编码
 * @author treblez
 * todo mutf-8 转换 utf-8
 */
public class ConstantUtf8Info implements ConstantInfo{
    public String str;

    @Override
    public void readInfo(ClassReader reader) throws IOException {
        int length = reader.readUint16();
        var bytes = new ByteArrayInputStream(reader.readBytes(length));
        //java.io.DataInputStream.readUTF()
        DataInputStream in = new DataInputStream(bytes);
        str = java.io.DataInputStream.readUTF(in);

    }
}
