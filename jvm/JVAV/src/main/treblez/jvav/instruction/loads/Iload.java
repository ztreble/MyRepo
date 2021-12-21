package treblez.jvav.instruction.loads;


import treblez.jvav.instruction.base.Index8Instruction;
import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 加载指令从局部变量表获取变量，推入操作数栈顶
 */
public class Iload {
    public void _iload(JVMFrame frame,int index){
        var val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
    public class ILOAD extends  Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _iload(frame,index);
        }
    }
    public class ILOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _iload(frame,0);
        }
    }
    public class ILOAD_1 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _iload(frame,1);
        }
    }
    public class ILOAD_2 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _iload(frame,2);
        }
    }
    public class ILOAD_3 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _iload(frame,3);
        }
    }

}
