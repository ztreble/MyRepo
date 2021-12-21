package treblez.jvav.instruction.comparisons;

import treblez.jvav.instruction.base.BranchInstruction;
import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Ifcond {
    public class IFEQ extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val==0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFNE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val!=0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFLT extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val<0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFLE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val<=0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFGT extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val>0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFGE extends BranchInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var val = frame.getOperandStack().popInt();
            if(val>=0){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
}
