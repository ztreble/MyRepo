package treblez.jvav.instruction.extended;

import treblez.jvav.instruction.base.BranchInstruction;
import treblez.jvav.instruction.base.BranchLogic;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 根据引用是否为null跳转
 * 先将栈顶的引用弹出
 */
public class IfNull {
    public class IFNULL extends BranchInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var ref = frame.getOperandStack().popRef();
            if(ref==null){
                BranchLogic.branch(frame,offSet);
            }
        }
    }
    public class IFNONNULL extends BranchInstruction{
        @Override
        public void execute(JVMFrame frame) {
            var ref = frame.getOperandStack().popRef();
            if(ref!=null){
                BranchLogic.branch(frame,offSet);
            }
        }
    }

}
