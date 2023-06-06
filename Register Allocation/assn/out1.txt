
/*1*/
import static a5.Memory.*;

class TC21 {
    public static void main(String[] a) {
        Object R0;
        alloca(1);
        store(0, new Fac());
        R0 = 10;
        R0 = ((Fac) load(0)).ComputeFac(((int) R0));
        System.out.println(((int) R0));
    }
}

class Fac {
    public int ComputeFac(int num) {
        // Interference Graph
        // mtTmp8 : []
        // mtTmp9 : [mtTmp10, mtTmp11]
        // mtTmp10 : [mtTmp9]
        // num_aux : []
        // mtTmp6 : []
        // mtTmp11 : [mtTmp9]
        // mtTmp7 : []
        // mtTmp4 : []
        // mtTmp5 : []
        // Register Allocation
        // mtTmp8 : Allocated to R0
        // mtTmp9 : Spilled to 0
        // mtTmp10 : Allocated to R0
        // num_aux : Allocated to R0
        // mtTmp6 : Allocated to R0
        // mtTmp11 : Allocated to R0
        // mtTmp7 : Allocated to R0
        // mtTmp4 : Allocated to R0
        // mtTmp5 : Allocated to R0
        Object R0;
        alloca(1);
        R0 = 0;
        R0 = num <= ((int) R0);
        if (((boolean) R0)) {
            R0 = 1;
            R0 = ((int) R0);
        }

        else {
            store(0, new Fac());
            R0 = 1;
            R0 = num - ((int) R0);
            R0 = ((Fac) load(0)).ComputeFac(((int) R0));
            R0 = num * ((int) R0);
            R0 = ((int) R0);
        }

        return ((int) R0);
    }
}
