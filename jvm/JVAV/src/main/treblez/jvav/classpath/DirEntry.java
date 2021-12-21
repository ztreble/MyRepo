package treblez.jvav.classpath;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author treblez
 */
public class DirEntry extends Entry{

    private final String absDir;
    DirEntry(String path){
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
    public byte[] readClass(String className)  {
        String fileName = String.join(absDir,className);
        byte[] contents = null;
        try (FileInputStream in = new FileInputStream(fileName)) {
            contents = in.readAllBytes();
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
