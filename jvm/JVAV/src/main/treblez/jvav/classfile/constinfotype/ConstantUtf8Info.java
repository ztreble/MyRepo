package treblez.jvav.classfile.constinfotype;

import treblez.jvav.classfile.ClassReader;
import treblez.jvav.util.ModifiedUtf8Charset;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;

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
        var bytes = reader.readBytes(length);
        //java.io.DataInputStream.readUTF()
        ByteBuffer tmpBuffer = ByteBuffer.allocate(bytes.length);
        tmpBuffer.put(bytes);
        // 还是需要截断
        tmpBuffer.rewind();
        ModifiedUtf8Charset modifiedUtf8Charset = new ModifiedUtf8Charset();
        CharsetDecoder charsetDecoder = modifiedUtf8Charset.newDecoder();
        CharBuffer charBuffer = charsetDecoder.decode(tmpBuffer);
        str = charBuffer.toString();

    }
}
