package treblez.jvav.instruction.control;

import treblez.jvav.instruction.base.BranchInstruction;
import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Goto extends BranchInstruction {
    @Override
    public void execute(JVMFrame frame) {
        BranchLogic.branch(frame,offSet);
    }
}
