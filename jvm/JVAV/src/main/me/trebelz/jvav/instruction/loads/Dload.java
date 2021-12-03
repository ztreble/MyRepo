package trebelz.jvav.instruction.loads;


import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.instruction.base.NoOperandsInstruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 加载指令从局部变量表获取变量，推入操作数栈顶
 */
public class Dload {
    public void _dload(JVMFrame frame,int index){
        var val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
    public class DLOAD extends  Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dload(frame,index);
        }
    }
    public class DLOAD_0 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _dload(frame,0);
        }
    }
    public class DLOAD_1 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _dload(frame,1);
        }
    }
    public class DLOAD_2 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _dload(frame,2);
        }
    }
    public class DLOAD_3 extends  NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            _dload(frame,3);
        }
    }

}
