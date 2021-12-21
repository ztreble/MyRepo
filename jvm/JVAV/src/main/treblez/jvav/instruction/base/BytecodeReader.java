package treblez.jvav.instruction.base;

/**
 * @author treblez
 */
public class BytecodeReader {
    private byte[] code;
    private int pc;


    public int getPc(){
        return pc;
    }

    public void reset(byte[] bytecode, int pc) {
        this.code = bytecode;
        this.pc = pc;
    }

    public byte readInt8(){
        return readUint8();
    }

    public byte readUint8() {
        var i = code[pc];
        ++pc;
        return i;
    }
    public short readInt16(){
        return readUint16();
    }

    /**
     * todo 这里可能有问题
     */
    public short readUint16() {
        var byte1 = readUint8();
        var byte2 = readUint8();
        return (short) (((short)byte1<<8)|(short)byte2);
    }


    public int readInt32()  {
        var byte1 = readUint8();
        var byte2 = readUint8();
        var byte3 = readUint8();
        var byte4 = readUint8();
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    // used by lookupswitch and tableswitch
    public int[] readInt32s(int n){
        int[] ints = new int[n];
        for (int i=0;i<n;i++) {
            ints[i] = readInt32();
        }
        return ints;
    }

    // used by lookupswitch and tableswitch
    public void skipPadding() {
        while(pc%4 != 0) {
            readUint8();
        }
    }


}
