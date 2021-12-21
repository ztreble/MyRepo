package treblez.jvav.instruction.stores;

import treblez.jvav.instruction.base.Index8Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 将变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Dstore {
    public void _dstore(JVMFrame frame,int index){
        var ref = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index,ref);
    }
    public class DSTORE extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dstore(frame,index);
        }
    }
    public class DSTORE_0 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dstore(frame,0);
        }
    }
    public class DSTORE_1 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dstore(frame,1);
        }
    }
    public class DSTORE_2 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dstore(frame,2);
        }
    }
    public class DSTORE_3 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _dstore(frame,3);
        }
    }

}
