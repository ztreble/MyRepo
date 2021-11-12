package trebelz.jvav.classfile;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author treblez
 * @Description 辅助读取数据的类
 * todo 大端还是小端？可能有很多问题
 * todo 为什么不直接原类型读取呢
 */
public class ClassReader {
    private final ByteBuffer buf;
    ClassReader(byte[] data){
        buf = ByteBuffer.allocate(data.length+5);
        buf.put(data);
    }

    public byte readUint8() {
        return buf.get();
    }

    public char readUint16() {
        byte[] tmp = new byte[2];
        buf.get(tmp,0,2);
        return (char) (((tmp[0] & 0xFF) << 8) | (tmp[1] & 0xFF));
    }

    public int readUint32()  {
        byte[] tmp = new byte[4];
        buf.get(tmp,0,4);
        return  (((tmp[0] & 0xFF) << 24) | (tmp[1] & 0xFF << 16) | (tmp[2] & 0xFF << 8) | (tmp[3] & 0xFF));
    }

    public long readUint64() {
        byte[] tmp = new byte[8];

        buf.get(tmp,0,8);
        return  (((long)(tmp[0] & 0xFF) << 56) | ((long)(tmp[1] & 0xFF) << 48) | ((long)(tmp[2] & 0xFF) << 40)
                | ((long)(tmp[3] & 0xFF) << 32) |
                (tmp[4] & 0xFF << 24) | (tmp[5] & 0xFF << 16) | (tmp[6] & 0xFF << 8) | (tmp[7] & 0xFF));
    }
    /**
     *读取uint16表，大小由开头的数据指定
      */
    public char[] readUint16s() {
        byte[] tmp = new byte[2];
        buf.get(tmp,0,2);
        //表的大小
        var size = Character.getNumericValue((((tmp[0] & 0xFF) << 8) | (tmp[1] & 0xFF))) ;
        tmp = new byte[size*2];
        buf.get(tmp,0,2*size);
        char[] ret = new char[size];
        for(int i=0;i<size;i++){
            ret[i] = (char) (((tmp[i*2] & 0xFF) << 8) | (tmp[i*2+1] & 0xFF));
        }
        return ret;
    }

    public byte[] readBytes(int n) {
        byte[] ret = new byte[n];
        buf.get(ret, 0, n);
        return ret;
    }
}
