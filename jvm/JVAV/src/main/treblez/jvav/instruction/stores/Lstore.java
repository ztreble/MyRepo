package treblez.jvav.instruction.stores;

import treblez.jvav.instruction.base.Index8Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 将变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Lstore {
    public void _lstore(JVMFrame frame,int index){
        var ref = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index,ref);
    }
    public class LSTORE extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lstore(frame,index);
        }
    }
    public class LSTORE_0 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lstore(frame,0);
        }
    }
    public class LSTORE_1 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lstore(frame,1);
        }
    }
    public class LSTORE_2 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lstore(frame,2);
        }
    }
    public class LSTORE_3 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _lstore(frame,3);
        }
    }

}
