class Test2 {
    public static void main(String[] args) {
        System.out.println(10+true); // 1 Binop error
    }

    //1 Binop
}

class A {
    public int bar(boolean b, int q) {
        int a;
        float f;
        a = 10;
        f = 10.1;
        // NOTE: Assignment of int to float is allowed, but not vice versa
        f = a;
        b = !b;
        b = !a;  // 1 Assignment error
        a = f; // 1 Assignment error
        a = b; // 1 Assignment error
        if(!a) {} //1 control error
        if (a + f) {} // 1 Control error
        if(a <= f) {}
        if(a <= b) {}   //1 Binop, 1 control error
        while (a <= f) {}
        while (a <= b) {} //1 Binop, 1 control
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
        D o4;
        c = false;
        t = new Test1();
        o1 = new B();
        o2 = new B();
        o3 = new C();
        o1 = new D();   //1 Assignment Error
        o4 = new A();   //1 Assignment Error
        a = ((10 + (10.1 + t)) + 10); // 3 Binop errors, 1 Assignment Error
        p = 20;
        p = this.bar(8, c);
        p = this.test(o1);
        p = this.test(o2);
        p = this.test(o3); //
        // Each argument matching failure
        // will be a separate Function error
        c = a + p; // 1 Assignment error

        if(o1 <= o2) {} // 1 Binop, 1 control
        return c;
    }
}

class B extends A {
    public int blah(boolean u){
        u = this.foo(8);
        return 0;
    }
}

class C extends B{
    public A blahblah(){
        int x;
        C o1;
        x = this.foo(7);  // 1 Assignment Error
        return o1;
    }
}

class D{
    public A blahblahblah(){
        D o1;
        return o1;  // 1 Function Error
    }
}


