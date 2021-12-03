package trebelz.jvav.instruction.stores;

import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 将变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Istore {
    public void _istore(JVMFrame frame,int index){
        var ref = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index,ref);
    }
    public class ISTORE extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _istore(frame,index);
        }
    }
    public class ISTORE_0 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _istore(frame,0);
        }
    }
    public class ISTORE_1 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _istore(frame,1);
        }
    }
    public class ISTORE_2 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _istore(frame,2);
        }
    }
    public class ISTORE_3 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _istore(frame,3);
        }
    }

}
