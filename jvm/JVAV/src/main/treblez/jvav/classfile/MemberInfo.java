package treblez.jvav.classfile;

import treblez.jvav.classfile.attributetable.AttributeInfo;
import treblez.jvav.classfile.attributetable.CodeAttribute;

/**
 * 存储字段和方法信息
 * 字段和方法有自己的访问标志，常量池索引，描述符，属性表
 * @author treblez
 */
public class MemberInfo {

    /** 保存常量池指针
     *
     */
    private ConstantPool cp;
    private char accessFlags;
    private char nameIndex;
    private char descriptorIndex;
    private AttributeInfo[]    attributes;

    MemberInfo(ClassReader reader, ConstantPool cp) throws Exception {
        this.cp = cp;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader,cp);
    }
    /**
     * 读取字段或方法数据
     * @param
     * @return
     */
    public static MemberInfo[] readMembers(ClassReader reader,ConstantPool cp) throws Exception {
        var memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];
        for(int i=0;i<memberCount;i++){
            members[i] = new MemberInfo(reader,cp);
        }
        return members;
    }

    public char getAccessFlags(){
        return accessFlags;
    }
    /**
     * 从常量池查找字段或者方法名
 */
    public String Name() throws Exception {
        return cp.getUtf8(nameIndex);
    }

    /**
     * 从常量池查找字段或方法描述符
     * @return
     */
    public String descriptor() throws Exception {
        return cp.getUtf8(descriptorIndex);
    }

    /**
     * 获取code属性
     * @return
     * todo 判断类型
     */
    public CodeAttribute codeAttribute(){
        for(var attrInfo:attributes){
            switch (attrInfo.toString()){
                case "CodeAttribute"->{ return (CodeAttribute) attrInfo;}
            }
        }
        return null;
    }
}
