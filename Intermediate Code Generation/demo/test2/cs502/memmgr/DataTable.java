package cs502.memmgr;

import java.util.HashMap;

public class DataTable {


    // create two hash maps
    private Integer size;
    private HashMap<Integer, Object> table; // put an object based on intger/boolean fields

    DataTable(int size){
        this.size = size;
        table = new HashMap<>();
//        should it be required to have a constructor
    }
//    = new HashMap<Integer, String>();

    void store(Integer index, Object value){
        //        check index validation
        table.put(index, value);
    }

    // fetches given field name with index and object to certain DataTable
    Object load(Integer index){
//        check index validation
        return table.get(index);
    }

}
