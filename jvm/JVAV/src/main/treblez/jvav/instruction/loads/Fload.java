package treblez.jvav.instruction.loads;


import treblez.jvav.instruction.base.Index8Instruction;
import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 加载指令从局部变量表获取变量，推入操作数栈顶
 */
public class Fload {
    public void _fload(JVMFrame frame,int index){
        var val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
    public class FLOAD extends  Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fload(frame,index);
        }
    }
    public class FLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _fload(frame,0);
        }
    }
    public class FLOAD_1 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _fload(frame,1);
        }
    }
    public class FLOAD_2 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _fload(frame,2);
        }
    }
    public class FLOAD_3 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _fload(frame,3);
        }
    }

}
