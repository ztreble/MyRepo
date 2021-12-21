package treblez.jvav.classfile.attributetable;

import treblez.jvav.classfile.ClassReader;
import treblez.jvav.classfile.ConstantPool;

/**
 * @author treblez
 *  变长属性
 *
 */

/*
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
*/

class ExceptionTableEntry{
    int startPc;
    int endPc;
    int handlerPc;
    int catchType;

    public ExceptionTableEntry(char startPc, char endPc, char handlerPc, char catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;

    }
}
/**
 * @author treblez
 */
public class CodeAttribute implements AttributeInfo {
    private ConstantPool cp;
    /**
     *    操作数栈的深度
     */
    private int maxStack;
    /**
    *    局部变量表的大小
    */
    private int maxLocals;

    /**
     *    字节码
     */
    private byte[] code;
    /**
     *    异常处理表
     */
    private ExceptionTableEntry[] exceptionTable;
    /**
     *    局部变量表
     */
    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) throws Exception {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        var  codeLength = reader.readUint32();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = readAttributes(reader, cp);
    }
    ExceptionTableEntry[] readExceptionTable(ClassReader reader){
        var exceptionTableLength = reader.readUint16();
        var exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for( var i : exceptionTable ){
            i = new ExceptionTableEntry(reader.readUint16(),reader.readUint16(),reader.readUint16(),reader.readUint16());
        }
        return exceptionTable;
    }

    public ConstantPool getCp() {
        return cp;
    }

    public void setCp(ConstantPool cp) {
        this.cp = cp;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(ExceptionTableEntry[] exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }


}
