package cs502;

import static cs502.memmgr.MemMgr.*;

public class Main {
    public static void main(String[] args) {
        Object x1; //to store variable of class
        Object x2; // to store methods
        Object a;
        Object vTablePtr;
        String fnName;
        int r;

        x1 = alloc(12);
        store(x1, 4, 0);
        store(x1, 8, 0);

        x2 = alloc(16);
        store(x2, 0, "A_m1");
        store(x2, 4, "A_m2");
        store(x2, 8, "A_m3");
        store(x2, 12, "A_m4");
     
        store(x1, 0, x2); //store vTable entry at first position
        a = x1; // a = new A()

        vTablePtr = load(a, 0);
        fnName = (String) load(vTablePtr, 0);
        r = (Integer) callFunc(fnName, a, 10);
        System.out.println(r);

        vTablePtr = load(a, 0);
        fnName = (String) load(vTablePtr, 8);
        r = (Integer) callFunc(fnName, a);
        System.out.println(r);
    }

    public static int A_m1(Object mthis, int z) {
        int t1;
        int t2;
        int t3;
        int t4;
        t1 = (Integer) load(mthis, 4);
        t2 = (Integer) load(mthis, 8);
        t3 = t1 + t2;
        t4 = t3 + z;
        return t4;
    }

    public static int A_m2(Object mthis) {
        int t1;
        t1 = (Integer) load(mthis, 4);
        return t1;
    }
    public static int A_m3(Object mthis) {
        System.out.println(3);
        return 3;
    }
    public static int A_m4(Object mthis) {
        System.out.println(4);
        return 4;
    }

}
