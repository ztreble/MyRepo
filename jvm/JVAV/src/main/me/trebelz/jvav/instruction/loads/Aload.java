package trebelz.jvav.instruction.loads;


import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.instruction.base.NoOperandsInstruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 加载指令从局部变量表获取变量，推入操作数栈顶
 */
public class Aload {
    public void _aload(JVMFrame frame,int index){
        var val = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(val);
    }
    public class ALOAD extends  Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _aload(frame,index);
        }
    }
    public class ALOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _aload(frame,0);
        }
    }
    public class ALOAD_1 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _aload(frame,1);
        }
    }
    public class ALOAD_2 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _aload(frame,2);
        }
    }
    public class ALOAD_3 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _aload(frame,3);
        }
    }

}
