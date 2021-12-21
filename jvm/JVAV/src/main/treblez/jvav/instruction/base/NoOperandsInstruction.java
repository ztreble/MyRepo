package treblez.jvav.instruction.base;

import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 没有操作数的指令
 */
public class NoOperandsInstruction implements Instruction{
    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
