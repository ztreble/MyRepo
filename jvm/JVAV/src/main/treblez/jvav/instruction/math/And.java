package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class And {
    public class IAND extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popInt();
            var v1 = stack.popInt();
            var result = v1&v2;
            stack.pushInt(result);
        }
    }
    public class LAND extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popLong();
            var v1 = stack.popLong();
            var result = v1&v2;
            stack.pushLong(result);
        }
    }
}
