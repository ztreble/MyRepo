package treblez.jvav.classpath;

import java.util.ArrayList;

/**
 * @author treblez
 */
public class CompositeEntry extends Entry{

    protected ArrayList<Entry> compositeEntry;

    public CompositeEntry(String path) {

        this.compositeEntry = new ArrayList<>();
        for(String iter:path.split(PATH_LIST_SEPARATOR)){
            Entry entry = newEntry(iter);
            compositeEntry.add(entry);
        }
    }

    public CompositeEntry() {
    }

    @Override
    public byte[] readClass(String className) {
        for(int i=0;i<compositeEntry.size();i++){
            if(i==10){
                System.out.println("ok");
            }
            Entry iter =compositeEntry.get(i);
            var data = iter.readClass(className);
            if(data!=null){
                return data;
            }
        }
        throw new RuntimeException("class not found "+className);
    }

    @Override
    public String string() {
        ArrayList<String> strs = new ArrayList<>();
        for(Entry iter:compositeEntry){
            strs.add(iter.string());
        }
        return String.join(PATH_LIST_SEPARATOR,strs);
    }



}
