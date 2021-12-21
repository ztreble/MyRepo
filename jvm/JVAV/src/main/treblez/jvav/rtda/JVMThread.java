package treblez.jvav.rtda;

/**
 * @author treblez
 * 线程私有的数据区
 * 每个线程都有自己的pc寄存器，和java虚拟机栈
 * 当当前方法为Java方法的时候，pc寄存器指向虚拟机指令的地址
 * 否则当前方法为本地方法，pc寄存器中的值没有意义。
 *
 */
public class JVMThread {
    /**
     * 指令寄存器
     */
    private int pc;
    /**
       虚拟机栈
        通过-Xss选项指定虚拟机栈的大小
     虚拟机栈由栈帧构成，帧中保存方法执行的状态，包括局部变量表，操作数栈等。
     */
    private JVMStack stack;
    public JVMThread(){
        stack = new JVMStack(1024);

    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
    public void pushFrame(JVMFrame frame){
        stack.push(frame);
    }
    public JVMFrame popFrame() throws Exception {
        return stack.pop();
    }
    public JVMFrame currentFrame() throws Exception {
        return stack.top();
    }
    public JVMFrame newFrame(int maxLocals, int maxStack){
        return new JVMFrame(maxLocals,maxStack);
    };

}
