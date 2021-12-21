package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Neg {
    public class DNEG extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v = stack.popDouble();
            stack.pushDouble(-v);
        }
    }
    public class INEG extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v = stack.popInt();
            stack.pushInt(-v);
        }
    }
    public class FNEG extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v = stack.popFloat();
            stack.pushFloat(-v);
        }
    }
    public class LNEG extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v = stack.popLong();
            stack.pushLong(-v);
        }
    }
}
