package cs502;

import static cs502.memmgr.MemMgr.*;

public class Main {
    public static void main(String[] args) {
        Object x1;
        Object x2;
        Object b;
        Object vTablePtr;
        String fnName;
        int r;

        x1 = alloc(20);
        store(x1, 4, 0);
        store(x1, 8, 0);
        store(x1, 12, 0);
        store(x1, 16, 0);

        x2 = alloc(20);
        store(x2, 0, "B_m1");
        store(x2, 4, "A_m2");
        store(x2, 8, "A_m3");
        store(x2, 12, "A_m4");
        store(x2, 16, "B_set");

        store(x1, 0, x2);
        b = x1;

        vTablePtr = load(b, 0);
        fnName = (String) load(vTablePtr, 16);
        r = (Integer) callFunc(fnName, b, 20, 30);

        vTablePtr = load(b, 0);
        fnName = (String) load(vTablePtr, 0);
        r = (Integer) callFunc(fnName, b, 10);
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

    public static int B_m1(Object mthis, int p) {
        Object vTablePtr;
        String fnName;
        int t1;
        int t2;
        int t3;
        int t4;
        int t5;
        int t6;
        int t7;

        vTablePtr = load(mthis, 0);
        fnName = (String) load(vTablePtr, 4);
        t1 = (Integer) callFunc(fnName, mthis);
        t2 = (Integer) load(mthis, 12);
        t3 = t2 * 2;
        t4 = (Integer) load(mthis, 8);
        t5 = t3 + t4;
        t6 = t5 - p;
        t7 =  t1 + t6;
        return t7;
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

    public static int B_set(Object mthis, int a, int b) {
        store(mthis, 12, a);
        store(mthis, 16, b);
        
        return 5;
    }

}
