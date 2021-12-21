package treblez.jvav.instruction.base;

import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class BranchLogic {
    public static void branch(JVMFrame frame,int offset){
        var pc = frame.getThread().getPc();
        var nextPc = pc+offset;
        frame.setNextPC(nextPc);
    }
}
