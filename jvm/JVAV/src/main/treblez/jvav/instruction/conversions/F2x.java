package treblez.jvav.instruction.conversions;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * Float 转换其它
 */
public class F2x {
    public class F2D extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popFloat();
            var f = (double)(d);
            stack.pushDouble(f);
        }
    }
    public class F2I extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popFloat();
            var f = (int)(d);
            stack.pushInt(f);
        }
    }
    public class F2L extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popFloat();
            var f = (long)(d);
            stack.pushLong(f);
        }
    }


}