package treblez.jvav.rtda;

/**
 * @author treblez
 */
public class JVMFrame {
    /**
     *
     */
    public JVMFrame next;
    /**
     * 局部变量表 局部变量表和操作数栈的大小由编译器预先计算好，存放在method_info的code中
     */
    public LocalVars localVars;
    /**
     * 操作数栈
     */
    public OperandStack operandStack;

    public JVMThread thread;
    private int nextPC;


    public JVMFrame(JVMThread thread,int maxLocals, int maxStack){
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
    public LocalVars getLocalVars(){
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC){
        this.nextPC = nextPC;
    }

    public JVMThread getThread(){return thread;}
}
