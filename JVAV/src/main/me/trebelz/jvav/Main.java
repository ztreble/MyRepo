package trebelz.jvav;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import trebelz.jvav.classpath.ClassPath;

import java.util.Arrays;

/**
 * 启动类
 * @author treblez
 * @version
 */
class Main {
    public static void main(String[] args) throws Exception {
        Cmd cmd = new Cmd();
        JCommander jCommander = JCommander.newBuilder().addObject(cmd).build();
        jCommander.parse(args);
        if(args.length>0){
            cmd._class = cmd.getRelease().get(0);
            System.arraycopy(cmd.getRelease().toArray(), 1,cmd.args, 0, cmd.getRelease().size()-1);
        }
        if (cmd.getVersionFlag()) {
            // 打印版本信息
            System.out.println("version 0.0.1");
        } else if(cmd.getHelpFlag() || cmd._class==null || "".equals(cmd._class)) {
            // -help 或者调用错误，打印正确格式
            cmd.printUsage();
        } else {
            startJVM(cmd);
        }
    }
    public static void startJVM(Cmd cmd) throws Exception {
        //根据命令行参数初始化类路径
        ClassPath cp = new ClassPath(cmd.getXjreOption(),cmd.getCpOption());


        System.out.println("classpath:"+cp+" class:"+cmd._class+" args:"+Arrays.toString(cmd.args));
        var className = cmd._class.replace(".","/");
        //查找class文件
        var classData = cp.readClass(className);

        System.out.println("class data:"+ Arrays.toString(classData));
    }

}