package treblez.jvav.instruction.comparisons;

import treblez.jvav.instruction.base.BranchInstruction;
import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Ificmp {
    int _icmpPop(JVMFrame frame) {
        var stack = frame.getOperandStack();
        var val = stack.popInt();
        return val;
    }
    public class IF_ICMPEQ extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1==val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ICMPNE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1!=val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ICMPLT extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1<val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ICMPLE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1<=val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ICMPGT extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1>val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IF_ICMPGE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val1 = _icmpPop(frame);
            var val2 = _icmpPop(frame);
            if(val1>=val2){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
}
