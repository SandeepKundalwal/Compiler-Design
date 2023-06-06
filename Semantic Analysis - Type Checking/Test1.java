class Test1 {
    public static void main(String[] args) {
        System.out.println(10+true); // 1 Binop error
    }
}

class A {
    public int bar(boolean b) {
        int a;
        float f;
        a = 10;
        f = 10;
        // NOTE: Assignment of int to float is allowed, but not vice versa
        f = a;
        a = f; // 1 Assignment error
        a = b; // 1 Assignment error
        if (a + f) {} // 1 Control error
        return b; // 1 Function error
    }

    public int test(A o1) {
        return 0;
    }

    public boolean foo(int p) {
        int a;
        boolean c;
        Test1 t;
        A o1;
        B o2;
        C o3;
        A o4;
        c = false;
        t = new Test1();
        o1 = new B();
        o2 = new B();
        o3 = new C();
        o4 = new D();
        a = ((10 + (10.1 + t)) + 10); // 3 Binop errors, 1 Assignment error
        p = 20;
        p = this.bar(c);
        p = this.test(o1);
        p = this.test(o2);
        p = this.test(o3); // 1 Function error, 1 Assignment error
                           // Each argument matching failure
                           // will be a separate Function error
        c = a + p; // 1 Assignment error
        return c;
    }
}

class B extends A {}

class D extends B {}

class C {}