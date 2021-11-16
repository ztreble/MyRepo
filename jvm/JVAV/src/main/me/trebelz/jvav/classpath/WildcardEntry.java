package trebelz.jvav.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * WildcardEntry是特殊的CompositeEntry，只需要更改构造函数，其它则继承自父类
 * 扫描目录下所有符合的jar文件，目录则跳过
 * WildcardEntry代表以*结尾的
 * @author treblez
 */
public class WildcardEntry extends CompositeEntry{

    public WildcardEntry(String path) {
        this.compositeEntry = new ArrayList<>();
        String baseDir = path.substring(0,path.length()-1);

        //类似于golang filepath.Walk的实现
        File file = new File(baseDir);

        for (File iter : Objects.requireNonNull(file.listFiles())){
            try {
                if(iter.isDirectory()&& !iter.getName().equals(baseDir)) {
                    System.out.println("skip this directory"+iter.getName());
                }
                if(iter.getName().endsWith(PATH_JAR_LOWER)||iter.getName().endsWith(PATH_JAR_UPPER)) {
                    ZipEntry jarEntry = new ZipEntry(iter.getAbsolutePath());
                    compositeEntry.add(jarEntry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
