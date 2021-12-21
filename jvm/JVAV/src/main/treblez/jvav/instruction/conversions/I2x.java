package treblez.jvav.instruction.conversions;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 类型转换
 * Int转换其它类型
 */
public class I2x {

    public class I2B extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (byte)(d);
            stack.pushInt(f);
        }
    }
    public class I2C extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (char)(d);
            stack.pushInt(f);
        }
    }
    public class I2S extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (short)(d);
            stack.pushInt(f);
        }
    }
    public class I2L extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (long)(d);
            stack.pushLong(f);
        }
    }
    public class I2F extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (float)(d);
            stack.pushFloat(f);
        }
    }
    public class I2D extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var d = stack.popInt();
            var f = (double)(d);
            stack.pushDouble(f);
        }
    }
}
