package treblez.jvav.instruction.comparisons;

import treblez.jvav.instruction.base.NoOperandsInstruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 大于等于小于和无法比较
 */
public class Dcmp {
    public void _dcmp(JVMFrame frame,boolean gFlag){
        var stack = frame.getOperandStack();
        var v2 = stack.popDouble();
        var v1 = stack.popDouble();
        if(v1>v2){
            stack.pushInt(1);
        }else if(v1==v2){
            stack.pushInt(0);
        }else if(v1<v2){
            stack.pushInt(-1);
        }else if(gFlag){
            stack.pushInt(1);
        }else{
            stack.pushInt(-1);
        }
    }
    public class DCMPG extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _dcmp(frame,true);
        }
    }
    public class DCMPL extends NoOperandsInstruction {
        @Override
        public void execute(JVMFrame frame) {
            _dcmp(frame,false);
        }
    }
}
