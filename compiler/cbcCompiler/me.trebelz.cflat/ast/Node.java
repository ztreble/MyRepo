package ast;

import java.io.PrintStream;

abstract public class Node implements Dumpable {
    public Node() {
    }

    abstract public Location location();

    public void dump() {
        dump(System.out);
    }
    //以文本形式表示抽象语法树
    public void dump(PrintStream s) {
        dump(new Dumper(s));
    }

    @Override
    public void dump(Dumper d) {
        d.printClass(this, location());
        _dump(d);
    }

    abstract protected void _dump(Dumper d);
}
