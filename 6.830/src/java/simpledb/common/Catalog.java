package simpledb.common;

import simpledb.common.Type;
import simpledb.storage.DbFile;
import simpledb.storage.HeapFile;
import simpledb.storage.TupleDesc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Catalog keeps track of all available tables in the database and their
 * associated schemas.
 * For now, this is a stub catalog that must be populated with tables by a
 * user program before it can be used -- eventually, this should be converted
 * to a catalog that reads a catalog table from disk.
 *   catalog类描述的是数据库实例。包含了数据库现有的表信息以及表的schema信息。现在需要实现添加新表
 *   的功能，以及从特定的表中提取信息。提取信息时通过表对应的TupleDesc对象决定操作的字段类型和数量。
 *
 *   全局catalog是分配给整个SimpleDB进程的Catalog类一个实例，可以
 *   通过方法Database.getCatalog()获得，global buffer pool可以通过方法Database.getBufferPool()获得。
 * ————————————————
 * @Threadsafe
 */
public class Catalog {
    private static final long serialVersionUID = 1L;
    private final ConcurrentHashMap<Integer,Table> hashTable;

    private static class Table{
        private static final long serialVersionUID = 1L;

        public final DbFile dbFile;
        public final String tableName;
        public final String pk;

        public Table(DbFile file,String name,String pkeyField){
            dbFile = file;
            tableName = name;
            pk = pkeyField;
        }

        public String toString(){
            return tableName + "(" + dbFile.getId() + ":" + pk +")";
        }
    }

    /**
     * Constructor.
     * Creates a new, empty catalog.
     */
    public Catalog() {
        hashTable = new ConcurrentHashMap<Integer, Table>();
    }

    /**
     * Add a new table to the catalog.
     * This table's contents are stored in the specified DbFile.
     * @param file the contents of the table to add;  file.getId() is the identfier of
     *    this file/tupledesc param for the calls getTupleDesc and getFile
     * @param name the name of the table -- may be an empty string.  May not be null.  If a name
     * conflict exists, use the last table to be added as the table for a given name.
     * @param pkeyField the name of the primary key field
     */
    public void addTable(DbFile file, String name, String pkeyField) {
        // some code goes here
        Table t = new Table(file,name,pkeyField);
        hashTable.put(file.getId(),t);
    }

    public void addTable(DbFile file, String name) {
        addTable(file, name, "");
    }

    /**
     * Add a new table to the catalog.
     * This table has tuples formatted using the specified TupleDesc and its
     * contents are stored in the specified DbFile.
     * @param file the contents of the table to add;  file.getId() is the identfier of
     *    this file/tupledesc param for the calls getTupleDesc and getFile
     */
    public void addTable(DbFile file) {
        addTable(file, (UUID.randomUUID()).toString());
    }

    /**
     * Return the id of the table with a specified name,
     * @throws NoSuchElementException if the table doesn't exist
     */
    public int getTableId(String name) throws NoSuchElementException {
        // some code goes here
        var ret = hashTable.searchValues(1,value->{
            if(value.tableName.equals(name)) return value.dbFile.getId();
            return null;
        });
        return 0;
    }

    /**
     * Returns the tuple descriptor (schema) of the specified table
     * @param tableid The id of the table, as specified by the DbFile.getId()
     *     function passed to addTable
     * @throws NoSuchElementException if the table doesn't exist
     */
    public TupleDesc getTupleDesc(int tableid) throws NoSuchElementException {
        // some code goes here
        var t = hashTable.getOrDefault(tableid,null);
        if(t!=null){
            return t.dbFile.getTupleDesc();
        }else{
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the DbFile that can be used to read the contents of the
     * specified table.
     * @param tableid The id of the table, as specified by the DbFile.getId()
     *     function passed to addTable
     */
    public DbFile getDatabaseFile(int tableid) throws NoSuchElementException {
        // some code goes here
        Table t = hashTable.getOrDefault(tableid,null);
        if(t != null){
            return t.dbFile;
        }else{
            throw new NoSuchElementException("not found db file for table " + tableid);
        }
    }

    public String getPrimaryKey(int tableid) {
        Table t = hashTable.getOrDefault(tableid,null);
        if(t != null){
            return t.pk;
        }else{
            throw new NoSuchElementException("not found primary key for table " + tableid);
        }
    }

    public Iterator<Integer> tableIdIterator() {
        // 返回一个内部引用，并且指向一个内部类对象，该内部类重写了迭代器方法
        return hashTable.keySet().iterator();
    }

    public String getTableName(int id) {
        // some code goes here
        Table t = hashTable.getOrDefault(id,null);
        if(t != null){
            return t.tableName;
        }else{
            throw new NoSuchElementException("not found name for table " + id);
        }
    }
    
    /** Delete all tables from the catalog */
    public void clear() {
        // some code goes here
        hashTable.clear();
    }
    
    /**
     * Reads the schema from a file and creates the appropriate tables in the database.
     * @param catalogFile
     */
    public void loadSchema(String catalogFile) {
        String line = "";
        String baseFolder=new File(new File(catalogFile).getAbsolutePath()).getParent();
        try {
            BufferedReader br = new BufferedReader(new FileReader(catalogFile));
            
            while ((line = br.readLine()) != null) {
                //assume line is of the format name (field type, field type, ...)
                String name = line.substring(0, line.indexOf("(")).trim();
                //System.out.println("TABLE NAME: " + name);
                String fields = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
                String[] els = fields.split(",");
                ArrayList<String> names = new ArrayList<>();
                ArrayList<Type> types = new ArrayList<>();
                String primaryKey = "";
                for (String e : els) {
                    String[] els2 = e.trim().split(" ");
                    names.add(els2[0].trim());
                    if (els2[1].trim().equalsIgnoreCase("int"))
                        types.add(Type.INT_TYPE);
                    else if (els2[1].trim().equalsIgnoreCase("string"))
                        types.add(Type.STRING_TYPE);
                    else {
                        System.out.println("Unknown type " + els2[1]);
                        System.exit(0);
                    }
                    if (els2.length == 3) {
                        if (els2[2].trim().equals("pk"))
                            primaryKey = els2[0].trim();
                        else {
                            System.out.println("Unknown annotation " + els2[2]);
                            System.exit(0);
                        }
                    }
                }
                Type[] typeAr = types.toArray(new Type[0]);
                String[] namesAr = names.toArray(new String[0]);
                TupleDesc t = new TupleDesc(typeAr, namesAr);
                HeapFile tabHf = new HeapFile(new File(baseFolder+"/"+name + ".dat"), t);
                addTable(tabHf,name,primaryKey);
                System.out.println("Added table : " + name + " with schema " + t);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println ("Invalid catalog entry : " + line);
            System.exit(0);
        }
    }
}

