package trebelz.jvav.rtda;

/**
 * @author treblez
 * 操作数栈
 */
public class OperandStack {
    int size;
    Slot[] slots;
    OperandStack(int maxStack){
        slots = new Slot[maxStack];
        for(int i=0;i<maxStack;i++){
            slots[i] = new Slot();
        }
    }
    public void pushInt(int val){
        slots[size++].num = val;
    }
    public int popInt(){
        return slots[--size].num;
    }

    public void pushFloat(float val) {
        slots[size++].num = Float.floatToIntBits(val);
    }
    public float popFloat() {
        return  Float.intBitsToFloat(slots[--size].num);
    }

    // long consumes two slots
    public void pushLong( long val) {
        slots[size++].num = (int)val;
        slots[size++].num = (int)(val >> 32);
    }
    public long popLong()  {
        size-=2;
        var low  = slots[size].num;
        var high = slots[size+1].num;
        return (long)high<<32|(0x00000000FFFFFFFFL&low);
    }

    // double consumes two slots
    public void pushDouble(double val) {
        pushLong(Double.doubleToLongBits(val));
    }
    public double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(Object obj) {
        slots[size++].ref = obj;
    }
    public Object popRef()  {
        return slots[size--].ref;
    }
    public void pushSlot(Slot slot){
        slots[size] = slot;
        size++;
    }
    public Slot popSlot(){
        size--;
        return slots[size];
    }
}
