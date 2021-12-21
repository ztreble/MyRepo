package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Div {
    public class DDIV extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popDouble();
            var v2 = stack.popDouble();

            var result = v1/v2;
            stack.pushDouble(result);
        }
    }
    public class IDIV extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popInt();
            var v2 = stack.popInt();
            var result = v1/v2;
            stack.pushInt(result);
        }
    }
    public class FDIV extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popFloat();
            var v2 = stack.popFloat();
            var result = v1/v2;
            stack.pushFloat(result);
        }
    }
    public class LDIV extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popLong();
            var v2 = stack.popLong();
            var result = v1/v2;
            stack.pushLong(result);
        }
    }
}
