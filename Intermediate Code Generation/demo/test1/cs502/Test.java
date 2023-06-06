package cs502;

public class Test {
    public static void main(String[] args) {
        A a;
        int r;
        a = new A();
        r = a.m1(10);
        r = a.y;
        r = (Integer) load(a, 8);
        System.out.println(r);
        r = a.m3();
        System.out.println(r);
    }
}

class A {
    int x;
    int y;

    public int m1(int z) {
        int t1;
        int t2;
        A a;

        a = this;
        t1 = a.x;
        t1 = (Integer) load(mthis, 4);

        t1 = a.y;
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
