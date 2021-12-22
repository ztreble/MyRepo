package treblez.jvav.instruction.constants;

import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class IPush {

    /**
     * 将指令扩展到int推入栈顶
     * todo 可能有问题
     */
    public class BIPUSH implements Instruction {
        public byte base;
        @Override
        public void fetchOperands(BytecodeReader reader){
            base = reader.readInt8();
        }
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(Byte.toUnsignedInt(base));
        }
    }

    /**
     * 将指令扩展到int推入栈顶
     * todo 可能有问题
     */
    public class SIPUSH implements Instruction{
        public short base;
        @Override
        public void fetchOperands(BytecodeReader reader){
            base = reader.readInt16();
        }
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(Short.toUnsignedInt(base));
        }
    }

}
