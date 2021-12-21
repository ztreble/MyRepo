package treblez.jvav.instruction.comparisons;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 比较指令，比价long型变量
 */
public class Lcmp {
    public class LCMP extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            var stack = frame.getOperandStack();
            var v2 = stack.popLong();
            var v1 = stack.popLong();
            if(v1>v2){
                stack.pushInt(1);
            }else if(v1==v2){
                stack.pushInt(0);
            }else{
                stack.pushInt(-1);
            }
        }
    }


}
