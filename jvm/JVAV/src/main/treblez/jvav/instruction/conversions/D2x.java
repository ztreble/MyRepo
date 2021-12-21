package treblez.jvav.instruction.conversions;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * Double 转换其它类型
 */
public class D2x {
    public class D2F extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popDouble();
            var f = (float)(d);
            stack.pushFloat(f);
        }
    }
    public class D2I extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popDouble();
            var f = (int)(d);
            stack.pushFloat(f);
        }
    }
    public class D2L extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popDouble();
            var f = (long)(d);
            stack.pushFloat(f);
        }
    }


}
