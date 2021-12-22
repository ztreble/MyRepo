package treblez.jvav.instruction.extended;

import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.instruction.loads.*;
import treblez.jvav.instruction.math.Iinc;
import treblez.jvav.instruction.stores.*;
import treblez.jvav.rtda.JVMFrame;

/**
 * @author treblez
 * 用于扩展方法的指令局部变量表
 * 扩展指令宽度
 */
public class Wide implements Instruction{
    //被改变的指令
    Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
//todo byte是否有问题？
        var opcode = reader.readUint8();
        //解码modifiedInstruction
        switch(opcode) {
            // iload
            case 0x15: {
                var outInst = new Iload();
                var inst = outInst.new ILOAD();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
            }
            // lload
            case 0x16:{
                var outInst = new Lload();
                var inst = outInst.new LLOAD();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x17:{
                var outInst = new Fload();
                var inst = outInst.new FLOAD();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x18:{
                var outInst = new Dload();
                var inst = outInst.new DLOAD();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x19:{
                var outInst = new Aload();
                var inst = outInst.new ALOAD();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x36:{
                var outInst = new Istore();
                var inst = outInst.new ISTORE();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x37:{
                var outInst = new Lstore();
                var inst = outInst.new LSTORE();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x38:{
                var outInst = new Fstore();
                var inst = outInst.new FSTORE();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x39:{
                var outInst = new Dstore();
                var inst = outInst.new DSTORE();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case 0x3a:{
                var outInst = new Astore();
                var inst = outInst.new ASTORE();
                inst.index = reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case (byte) 0x84:{
                var outInst = new Iinc();
                var inst = outInst.new IINC();
                inst.index= reader.readUint16();
                modifiedInstruction = inst;
                return;
            }
            case (byte) 0xa9:{
                throw new Exception("Unsupported opcode: 0xa9!");
            }
        }
    }

    @Override
    public void execute(JVMFrame frame) {
        modifiedInstruction.execute(frame);
    }


}
