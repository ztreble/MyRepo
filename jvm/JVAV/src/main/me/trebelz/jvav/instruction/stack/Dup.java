package trebelz.jvav.instruction.stack;


import trebelz.jvav.instruction.base.NoOperandsInstruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 复制栈顶的单个变量
 */
public class Dup {
    public class DUP extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot = stack.popSlot();
            stack.pushSlot(slot);
            stack.pushSlot(slot);
        }
    }
    public class DUP_X1 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot1 = stack.popSlot();
            var slot2 = stack.popSlot();
            stack.pushSlot(slot1);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }
    public class DUP_X2 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot1 = stack.popSlot();
            var slot2 = stack.popSlot();
            var slot3 = stack.popSlot();
            stack.pushSlot(slot1);
            stack.pushSlot(slot3);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }
    public class DUP2 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot1 = stack.popSlot();
            var slot2 = stack.popSlot();
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }
    public class DUP2_X1 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot1 = stack.popSlot();
            var slot2 = stack.popSlot();
            var slot3 = stack.popSlot();
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
            stack.pushSlot(slot3);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }
    public class DUP2_X2 extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var slot1 = stack.popSlot();
            var slot2 = stack.popSlot();
            var slot3 = stack.popSlot();
            var slot4 = stack.popSlot();
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
            stack.pushSlot(slot4);
            stack.pushSlot(slot3);
            stack.pushSlot(slot2);
            stack.pushSlot(slot1);
        }
    }
}
