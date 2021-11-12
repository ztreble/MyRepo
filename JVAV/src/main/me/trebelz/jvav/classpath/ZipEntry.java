package trebelz.jvav.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

/**
 * @author treblez
 */
public class ZipEntry extends Entry{
    private final String absDir;

    public ZipEntry(String path) {
        try {
            File directory = new File(path);
            //获取绝对路径
            absDir = directory.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public byte[] readClass(String className) {
        //String fileName = String.join("",absDir,className);
        byte[] contents = null;
        // 首先读取压缩文件，在压缩文件目录中寻找对应文件
        try (FileInputStream in = new FileInputStream(absDir)) {
            try(ZipInputStream zipInputStream = new ZipInputStream(in);) {
                var nowEntry = zipInputStream.getNextEntry();
                while(nowEntry!=null){
                    if(nowEntry.getName().equals(className)){
                        contents = zipInputStream.readAllBytes();
                    }
                    // 要关闭当前的Entry
                    zipInputStream.closeEntry();
                    nowEntry = zipInputStream.getNextEntry();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    @Override
    public String string() {
        return absDir;
    }
}
