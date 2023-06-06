package cs502.memmgr;

import java.lang.reflect.Method;

public class MemMgr {

    public static Object alloc(int size){
        return new DataTable(size);
    }
    // stores given field with index and object to certain DataTable
    public static void store(Object obj, Integer index, Object value){
        ((DataTable)obj).store(index, value);
    }

    // fetches given field name with index and object to certain DataTable
    public static Object load(Object obj, Integer index){
        return ((DataTable)obj).load(index);
    }

    public static Object callFunc(String fnName, Object... args) {
        Object result = null;
        try {
            Class<cs502.Main> clazz = cs502.Main.class;
            Method method = null;
            for (Method mtd : clazz.getDeclaredMethods()) {
                if (mtd.getName().equals(fnName)) {
                    method = mtd;
                    break;
                }
            }
            result = method.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
