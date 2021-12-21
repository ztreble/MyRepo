package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 位移指令
 */
public class Sh {
    public class ISHL extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popInt();
            var s = v1<<(v2&0x1f);
            stack.pushInt(s);
        }
    }
    public class ISHR extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popInt();
            var s = v1>>(v2&0x1f);
            stack.pushInt(s);
        }
    }
    public class IUSHR extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popInt();
            var s = v1>>(v2&0x1f);
            stack.pushInt(s);
        }
    }
    public class LSHL extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popLong();
            var s = v1<<(v2&0x3f);
            stack.pushLong(s);
        }
    }
    public class LSHR extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popLong();
            var s = v1>>(v2&0x3f);
            stack.pushLong(s);
        }
    }
    public class LUSHR extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popLong();
            var s = v1>>(v2&0x3f);
            stack.pushLong(s);
        }
    }
}
