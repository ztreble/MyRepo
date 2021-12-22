package treblez.jvav.instruction.control;

/**
 * @author treblez
 * switch case指令有两种实现方式：如果case值可以编码成一个索引表，那么实现成tableswitch指令，否则实现为lookupswitch指令
 *
 */

import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
lookupswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
npairs1
npairs2
npairs3
npairs4
match-offset pairs...
 * @author treblez
 */
public class LookUpSwitch implements Instruction {
    int defaultOffset;
    int npairs;
    int[] matchOffsets;
    @Override
    public void fetchOperands(BytecodeReader reader){
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs*2);
    }
    @Override
    public void execute(JVMFrame frame){
        var key = frame.getOperandStack().popInt();
        for(int i=0;i<npairs*2;i+=2){
            if(matchOffsets[i]==key){
                var offset = matchOffsets[i+1];
                BranchLogic.branch(frame,offset);
                return;
            }
        }
        BranchLogic.branch(frame,defaultOffset);
    }

}
