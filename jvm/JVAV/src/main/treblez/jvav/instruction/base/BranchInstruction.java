package treblez.jvav.instruction.base;


import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 跳转指令
 */
public class BranchInstruction implements Instruction{
    /**
     * 跳转偏移量
     */
    public int offSet;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offSet = reader.readUint16();
    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
