package trebelz.jvav;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author treblez
 * @Description 使用jcommander完成命令行解析
 */
public class Cmd {

    @Parameter(names = {"-help","-?","--help","--?"}, description = "print help message", required = false, help = true,order = 0)
    private Boolean helpFlag = false;

    @Parameter(names = {"-version","-v","--version","--v"}, description = "print version and exit", required = false,order = 1)
    private Boolean versionFlag = false;

    @Parameter(names = {"--classpath","-cp","--cp"}, required = false,description = "classpath", order = 2)
    private String cpOption = "";

    @Parameter(names = {"-Xjre","--Xjre"}, required = false,description = "path to jre", order = 3)
    private String XjreOption = "";

    /**
     * 匹配剩下的部分
     */
    @Parameter(description = "class name", order = 4)
    private ArrayList<String> release;

    public String _class;
    public String[] args;
    Cmd(){
        release = new ArrayList<String>();
        args = new String[10];
    }
    public void printUsage(){
        System.out.println("Usage: "+ System.getProperty("java.class.path")+" [-options] class [args...]\n");
    }
    public Boolean getHelpFlag() {
        return helpFlag;
    }

    public String getXjreOption() {
        return XjreOption;
    }

    public void setXjreOption(String xjreOption) {
        XjreOption = xjreOption;
    }

    public String get_class() {
        return _class;
    }
    public ArrayList<String> getRelease(){
        return release;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Boolean getVersionFlag() {
        return versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }
}
