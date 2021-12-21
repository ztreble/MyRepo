package treblez.jvav.instruction.math;

import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 */
public class Iinc {
    public class IINC implements Instruction {
        public int index;
        public int _const;
        @Override
        public void fetchOperands(BytecodeReader reader){
            index = reader.readUint8();
            _const = reader.readInt8();
        }
        @Override
        public void execute(JVMFrame frame){
            var localVars = frame.getLocalVars();
            var val = localVars.getInt(index);
            val +=_const;
            localVars.setInt(index,val);

        }
    }
}
