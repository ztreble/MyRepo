package trebelz.jvav.instruction.constants;

import trebelz.jvav.instruction.base.NoOperandsInstruction;
import trebelz.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 使用内部类,增加内聚
 */
public class Const {
    public class ACONST_NULL extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushRef(null);
        }
    }
    public class DCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushDouble(0.0);
        }
    }
    public class DCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushDouble(1.0);
        }
    }
    public class FCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushFloat(0);
        }
    }
    public class FCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushDouble(1.0);
        }
    }
    public class FCONST_2 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushDouble(2.0);
        }
    }
    public class ICONST_M1 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(-1);
        }
    }
    public class ICONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(0);
        }
    }
    public class ICONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(1);
        }
    }
    public class ICONST_2 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(2);
        }
    }
    public class ICONST_3 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(3);
        }
    }
    public class ICONST_4 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(4);
        }
    }
    public class ICONST_5 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushInt(5);
        }
    }

    public class LCONST_0 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushLong(0);
        }
    }
    public class LCONST_1 extends NoOperandsInstruction{
        @Override
        public void execute(JVMFrame frame){
            frame.getOperandStack().pushLong(1);
        }
    }
}
