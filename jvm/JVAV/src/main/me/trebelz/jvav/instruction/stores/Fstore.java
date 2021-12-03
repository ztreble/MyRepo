package trebelz.jvav.instruction.stores;

import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 将变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Fstore {
    public void _fstore(JVMFrame frame,int index){
        var ref = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index,ref);
    }
    public class FSTORE extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fstore(frame,index);
        }
    }
    public class FSTORE_0 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fstore(frame,0);
        }
    }
    public class FSTORE_1 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fstore(frame,1);
        }
    }
    public class FSTORE_2 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fstore(frame,2);
        }
    }
    public class FSTORE_3 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _fstore(frame,3);
        }
    }

}
