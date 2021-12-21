package treblez.jvav.instruction.stack;


import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Pop {

    public class POP extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            stack.popSlot();
        }
    }
    public class POP2 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            stack.popSlot();
            stack.popSlot();
        }
    }




}
