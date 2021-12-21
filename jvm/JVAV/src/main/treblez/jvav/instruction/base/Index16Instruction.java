package treblez.jvav.instruction.base;

import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 访问运行时常量池的指令
 * 索引由两字节操作数给出
 *
 */
public class Index16Instruction implements Instruction{
    public int index;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
