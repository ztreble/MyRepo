package treblez.jvav;

import treblez.jvav.classfile.MemberInfo;
import treblez.jvav.instruction.base.BytecodeReader;
import treblez.jvav.instruction.factory.InstructionFactory;
import treblez.jvav.rtda.JVMThread;

/**
 * @author treblez
 */
public class Interpreter {
    void interpreter(MemberInfo methodInfo) throws Exception {
        /**
         * code属性
         */
        var codeAttr = methodInfo.codeAttribute();
        /**
         * 局部变量表的大小
         */
        var maxLocals = codeAttr.getMaxLocals();

        var maxStack = codeAttr.getMaxStack();

        var byteCode = codeAttr.getCode();

        //创建Thread实例，创建一个帧并且将其推入虚拟机栈顶
        var thread = new JVMThread();
        var frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        //todo 异常处理
        // 在golang中使用recover函数
        loop(thread, byteCode);

    }


    void loop(JVMThread thread, byte[] bytecode) throws Exception {
        var frame = thread.popFrame();
        var reader = new BytecodeReader();

        for (; ; ) {
            //计算pc
            var pc = frame.getNextPC();
            thread.setPc(pc);

            // decode 解码指令
            reader.reset(bytecode, pc);
            byte opcode = reader.readUint8();
            var inst = InstructionFactory.newInstruction(opcode);

            assert inst != null;
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPc());

            // execute 执行指令
            System.out.printf("pc:%2d inst:%T %v\n", pc, inst, inst);
            inst.execute(frame);
        }
    }
}