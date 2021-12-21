package treblez.jvav.classpath;

import java.io.File;

/**
 * 入口函数
 * @author treblez
 */
public class ClassPath extends Entry{

    Entry bootClasspath;
    Entry extClasspath;
    Entry userClasspath;

    public ClassPath(String jreOption, String cpOption) throws Exception {
        //启动类路径和扩展类路径
        parseBootAndExtClasspath(jreOption);
        //用户类路径
        parseUserClasspath(cpOption);
    }

    /**
     * 解析启动类路径和扩展类路径
     * @param jreOption
     * @throws Exception
     * 启动类路径默认对应jre\lib目录，扩展类路径默认对应jre\lib\ext目录
     */
    private void parseBootAndExtClasspath(String jreOption) throws Exception {
        String jreDir = getJreDir(jreOption);

        // jre/lib/*
        // 注意此函数调用第一个参数是分隔符
        var jreLibPath = String.join(PATH_LIST_SEPARATOR,jreDir, "lib", "*");
        bootClasspath = new WildcardEntry(jreLibPath);

        // jre/lib/ext/*
        String jreExtPath = String.join(PATH_LIST_SEPARATOR,jreDir, "lib", "ext", "*");
        extClasspath = new WildcardEntry(jreExtPath);
    }

    String getJreDir(String jreOption) throws Exception {
        //优先使用用户输入的jre目录
        if(!"".equals(jreOption)&&exists(jreOption)){
            return jreOption;
        }
        //在当前目录下寻找jre目录
        if(exists("./jre")) {
            return "./jre";
        }
        //找不到则使用JAVA_HOME环境变量
        var jh = System.getenv("JAVA_HOME");
        if (!"".equals(jh)){
            return String.join(PATH_LIST_SEPARATOR,jh,"jre");
        }
        throw new Exception("Can not find jre folder!");
    }

    boolean exists(String path)  {
        File file = new File(path);
        return file.exists();
    }

    void parseUserClasspath(String cpOption) {
        //没有提供-cp选项则使用当前路径作为用户类路径
        if ("".equals(cpOption)) {
            cpOption = ".";
        }
        userClasspath = newEntry(cpOption);
    }

    /**
     *
     * @param className 文件相对路径 依次搜索class文件
     * @return
     */
    @Override
    public byte[] readClass(String className) {
        className = className+".class";
        byte[] tmp;
        if((tmp = bootClasspath.readClass(className)).length!=0){
            return tmp;
        }
        if((tmp = extClasspath.readClass(className)).length!=0){
            return tmp;
        }
        if((tmp = userClasspath.readClass(className)).length!=0){
            return tmp;
        }
        return null;
    }

    @Override
    public String string() {
        return userClasspath.string();
    }
}
