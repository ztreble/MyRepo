package trebelz.jvav.classfile;

import trebelz.jvav.classfile.constinfotype.ConstantClassInfo;
import trebelz.jvav.classfile.constinfotype.ConstantInfo;
import trebelz.jvav.classfile.constinfotype.ConstantNameAndTypeInfo;
import trebelz.jvav.classfile.constinfotype.ConstantUtf8Info;

/**
 *
 * @author treblez
 */
public class ConstantPool {


    // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
    // All 8-byte constants take up two entries in the constant_pool table of the class file.
    // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
    // table at index n, then the next usable item in the pool is located at index n+2.
    // The constant_pool index n+1 must be valid but is considered unusable.
    public ConstantInfo[] cp;
//    ConstantPool(){
//        constantPool = new ConstantInfo[cpCount];
//    }

    /** 构造函数
     *
     * 常量池大小比实际大1
     * 有效的索引是1-n
     *
     */
    public ConstantPool readConstantPool(ClassReader reader) throws Exception {
        // 表头值
        var cpCount = (int)(reader.readUint16());
        var tmpCp =  new ConstantPool(new ConstantInfo[cpCount]);

        // The constant_pool table is indexed from 1 to constant_pool_count - 1.
        for(var i = 1; i < cpCount; i++ ){
            tmpCp.cp[i] = ConstantInfo.readConstantInfo(reader, tmpCp);
            var tmp = tmpCp.cp[i].getClass().getName();
            switch (tmp) {
                // long 和 double 在常量池中占两个位置
                //TODO: 2021/11/12 这个地方有问题
                case "ConstantLongInfo", "ConstantDoubleInfo" -> {
                    i++;
                    break;
                }
                default -> {
                    break;
                }
            }
        }
        return tmpCp;
    }

    ConstantPool() {
    }
    ConstantPool(ConstantInfo[] cp) {
        this.cp = cp;
    }

    /**
     * 按照索引查找常量
     * @param index
     * @return
     * @throws Exception
     */
    public ConstantInfo getConstantInfo(int index) throws Exception {
        var ret = cp[index];
        if(ret!=null){
            return ret;
        }
        throw new Exception("Invalid constant pool index: %"+index+"!");
    }

    /**
     * 从常量池中查找字段或方法的名字和描述符
     * @param index
     * @return
     */
    public String getName(char index) throws Exception {
        var ntInfo = (ConstantNameAndTypeInfo)getConstantInfo(index);
        return getUtf8(ntInfo.nameIndex);
    }
    public String getType(char index) throws Exception {
        var ntInfo = (ConstantNameAndTypeInfo)getConstantInfo(index);
        return getUtf8(ntInfo.descriptorIndex);
    }
    /**
     * 从常量池中查找类名
     * @param
     */
    public String getClassName(int index) throws Exception {
        var classInfo = (ConstantClassInfo)getConstantInfo(index);
        return getUtf8(classInfo.nameIndex);
    }

    /**
     * 从常量池中查找UTF-8字符串
     * @param index
     * @return
     * @throws Exception
     */
    public String getUtf8(int index) throws Exception {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) getConstantInfo(index);
        return utf8Info.str;
    }







}
