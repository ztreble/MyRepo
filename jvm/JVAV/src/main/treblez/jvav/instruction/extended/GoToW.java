package treblez.jvav.instruction.extended;

import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 宽字节的goto指令
 */
public class GoToW {
    public int offset;
    public void fetchOperands(BytecodeReader reader){
        offset = reader.readInt32();
    }
    public void execute(JVMFrame frame){
        BranchLogic.branch(frame,offset);
    }

}
