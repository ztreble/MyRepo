package treblez.jvav.instruction.conversions;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 类型转换
 * Long转换其它类型
 */
public class L2x {
    public class L2D extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popLong();
            var f = (double)(d);
            stack.pushDouble(f);
        }
    }
    public class L2F extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popLong();
            var f = (int)(d);
            stack.pushFloat(f);
        }
    }
    public class L2I extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popLong();
            var f = (int)(d);
            stack.pushLong(f);
        }
    }
}
