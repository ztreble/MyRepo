package trebelz.jvav.rtda;

/**
 * @author treblez
 */
public class JVMFrame {
    /**
     *
     */
    JVMFrame next;
    /**
     * 局部变量表 局部变量表和操作数栈的大小由编译器预先计算好，存放在method_info的code中
     */
    LocalVars localVars;
    /**
     * 操作数栈
     */
    OperandStack operandStack;

    public JVMFrame(int maxLocals, int maxStack){
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
    public LocalVars getLocalVars(){
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }
}
