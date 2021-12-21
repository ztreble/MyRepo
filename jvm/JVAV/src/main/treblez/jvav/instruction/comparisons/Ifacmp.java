package treblez.jvav.instruction.comparisons;

import treblez.jvav.instruction.base.BranchInstruction;
import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.rtda.JVMFrame;

public class Ifacmp {
    private boolean _acmp(JVMFrame frame){
        var stack = frame.getOperandStack();
        var ref2 = stack.popRef();
        var ref1 = stack.popRef();
        return ref1==ref2;
    }
    public class IF_ACMPEQ extends BranchInstruction{
        @Override
        public void execute(JVMFrame frame) {
            if(_acmp(frame)){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ACMPNE extends BranchInstruction{
        @Override
        public void execute(JVMFrame frame) {
            if(!_acmp(frame)){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
}
