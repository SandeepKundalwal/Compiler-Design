package cs502;

public class Test {
    public static void main(String[] args) {
        B b;
        int r;

        b = new B();
        

        r = b.set(20, 30);

        r = b.m1(10);
        System.out.println(r);
    }
}

class A {
    int x;
    int y;

    public int m1(int z) {
        int t1;
        int t2;

        t1 = x + y;
        t2 = t1 + z;
        return t2;
    }


    public int m2() {
        return x;
    }

    public int m3() {
        System.out.println(3);
        return 3;
    }

    public int m4() {
        System.out.println(4);
        return 4;
    }

}

class B extends A {
    int x;
    int z;

    public int m1(int p) {
        int t1;
        int t2;
        int t3;
        int t4;
        int t5;
        B t6;

        t6 = this;
        t1 = t6.m2();
        
        t2 = y * 2;
        t3 = t1 + t2;
        t4 = t3 - p;
        t5 = t1 + t4;
        return t5;
    }

    public int set(int a, int b) {
        x = a;
        z = b;
        return 5;
    }
}
