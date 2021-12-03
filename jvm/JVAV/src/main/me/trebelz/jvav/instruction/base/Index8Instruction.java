package trebelz.jvav.instruction.base;

import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 存储和加载类指令
 * 根据索引存取局部变量表
 * 索引由单字节操作数给出
 */
public class Index8Instruction implements Instruction{

    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
    }

    @Override
    public void execute(JVMFrame frame) {

    }
}
