package trebelz.jvav.instruction.stores;

import trebelz.jvav.instruction.base.Index8Instruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 将变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Astore {
    public void _astore(JVMFrame frame,int index){
        var ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index,ref);
    }
    public class ASTORE extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _astore(frame,index);
        }
    }
    public class ASTORE_0 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _astore(frame,0);
        }
    }
    public class ASTORE_1 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _astore(frame,1);
        }
    }
    public class ASTORE_2 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _astore(frame,2);
        }
    }
    public class ASTORE_3 extends Index8Instruction{
        @Override
        public void execute(JVMFrame frame) {
            _astore(frame,3);
        }
    }

}
