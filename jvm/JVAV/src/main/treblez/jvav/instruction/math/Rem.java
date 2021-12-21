package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Rem {
    public class DREM extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popDouble();
            var v2 = stack.popDouble();
            var result = v1%v2;
            stack.pushDouble(result);
        }
    }
    public class IREM extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popInt();
            var v2 = stack.popInt();
            var result = Math.floorMod(v1,v2);
            stack.pushInt(result);
        }
    }
    public class FREM extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popFloat();
            var v2 = stack.popFloat();
            var result = v1%v2;
            stack.pushFloat(result);
        }
    }
    public class LREM extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v1 = stack.popLong();
            var v2 = stack.popLong();
            var result = Math.floorMod(v1,v2);
            stack.pushLong(result);
        }
    }
}
