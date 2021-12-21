package treblez.jvav.classpath;

import java.io.File;

/**
 * @author treblez
 * @Description 表示类路径项的抽象类
 */
public abstract class Entry {
    /**
     * 跨平台分隔符
     */
    public static final String PATH_LIST_SEPARATOR = File.separator;

    /**
     * 消除魔法值
     */
    public static final String PATH_ASTERISK = "*";
    public static final String PATH_JAR_LOWER = "jar";
    public static final String PATH_JAR_UPPER = "JAR";
    public static final String PATH_ZIP_LOWER = "zip";
    public static final String PATH_ZIP_UPPER = "ZIP";

    /**
     *
     * @param className 文件相对路径
     * @return  byte[]
     * byte和 uint8_t都是1字节4位，暂时用byte代替
     */
    public abstract byte[] readClass(String className) ;

    /**
     * 返回变量的字符串表示
     * @return String
     */
    public abstract String string();

    /**
     *
     * @param path class文件路径
     * @return Entry 用来处理的实体类
     */
    public static Entry newEntry(String path){
        //文件分隔符分割多个文件的情况
        if(path.contains(PATH_LIST_SEPARATOR)){
            return new CompositeEntry(path);
        }
        //指代所有文件
        if(path.endsWith(PATH_ASTERISK)){
            return new WildcardEntry(path);
        }
        //指定jar包
        if(path.endsWith(PATH_JAR_LOWER)||path.endsWith(PATH_JAR_UPPER)||path.endsWith(PATH_ZIP_LOWER)||path.endsWith(PATH_ZIP_UPPER)){
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }
}
