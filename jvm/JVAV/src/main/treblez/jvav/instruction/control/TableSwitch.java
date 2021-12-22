package treblez.jvav.instruction.control;

import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */

public class TableSwitch implements Instruction {
    int defaultOffset;
    int low;
    int high;
    int[] jumpOffsets;
    @Override
    public void fetchOperands(BytecodeReader reader){
        reader.skipPadding();
        //默认情况下执行跳转所需要的字节码偏移量
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        var jumpOffsetsCount = high - low + 1;
        jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }
    @Override
    public void execute(JVMFrame frame){
        var index = frame.getOperandStack().popInt();
        int offset;
        if(index>=low&&index<=high){
            offset = jumpOffsets[index-low];

        }else{
            offset = defaultOffset;
        }

    }
}
