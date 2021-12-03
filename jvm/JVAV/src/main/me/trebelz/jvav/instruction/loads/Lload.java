package trebelz.jvav.instruction.loads;


import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.instruction.base.NoOperandsInstruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 加载指令从局部变量表获取变量，推入操作数栈顶
 */
public class Lload {
    public void _lload(JVMFrame frame,int index){
        var val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
    public class LLOAD extends  Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lload(frame,index);
        }
    }
    public class LLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _lload(frame,0);
        }
    }
    public class LLOAD_1 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _lload(frame,1);
        }
    }
    public class LLOAD_2 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _lload(frame,2);
        }
    }
    public class LLOAD_3 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _lload(frame,3);
        }
    }

}
