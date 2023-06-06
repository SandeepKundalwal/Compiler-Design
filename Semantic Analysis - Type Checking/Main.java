import syntaxtree.*;
import visitor.*;

import java.io.ByteArrayInputStream;
import java.util.*;

public class Main {
   public static void main(String [] args) {
      try {
         String ip = "class Test2 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        System.out.println(10.5+true); // 1 Binop error\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    public int bar(boolean b, int q) {\n" +
                 "        int a;\n" +
                 "        float f;\n" +
                 "        a = 10;\n" +
                 "        f = 10;\n" +
                 "        // NOTE: Assignment of int to float is allowed, but not vice versa\n" +
                 "        f = a;\n" +
                 "        b = !b;\n" +
                 "        b = !a;  // 1 Assignment error\n" +
                 "        a = f; // 1 Assignment error\n" +
                 "        a = b; // 1 Assignment error\n" +
                 "        while (!a){} //1 control error\n" +
                 "        if (a + f) {} // 1 Control error\n" +
                 "        if(a <= f) {}\n" +
                 "        if(a <= b) {}   //1 Binop, 1 control error\\n \" +\n" +
                 "        return b; // 1 Function error\n" +
                 "    }\n" +
                 "\n" +
                 "    public int test(A o1) {\n" +
                 "        return 0;\n" +
                 "    }\n" +
                 "\n" +
                 "    public boolean foo(int p) {\n" +
                 "        int a;\n" +
                 "        boolean c;\n" +
                 "        Test2 t;\n" +
                 "        A o1;\n" +
                 "        B o2;\n" +
                 "        C o3;\n" +
                 "        D o4;\n" +
                 "        c = false;\n" +
                 "        t = new Test2();\n" +
                 "        o1 = new B();\n" +
                 "        o2 = new B();\n" +
                 "        o3 = new C();\n" +
                 "        o1 = new D();   //1 Assignment Error\n" +
                 "        o4 = new A();   //1 Assignment Error\n" +
                 "        a = ((10 + (10.1 + t)) + 10); // 3 Binop errors, 1 Assignment Error\n" +
                 "        p = 20;\n" +
                 "        p = this.bar(8, c); //2 function error\n" +
                 "        p = this.test(o1);\n" +
                 "        p = this.test(o2);\n" +
                 "        p = this.test(o3); //\n" +
                 "        // Each argument matching failure\n" +
                 "        // will be a separate Function error\n" +
                 "        c = a + p; // 1 Assignment error\n" +
                 "\n" +
                 "        if(o1 <= o2) // 1 Binop, 1 control\n" +
                 "        if(o1 != o2) {}\n" +
                 "        if(o1 != o4) {} // 1 Binop, 1 Control\n" +
                 "        return c;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class B extends A {\n" +
                 "    public int blah(boolean u){\n" +
                 "        u = this.foo(8);\n" +
                 "        return 0;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class C extends B{\n" +
                 "    public A blahblah(){\n" +
                 "        int x;\n" +
                 "        C o1;\n" +
                 "        x = this.foo(7);  // 1 Assignment Error\n" +
                 "        return o1;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class D{\n" +
                 "    public A blahblahblah(){\n" +
                 "        D obj;\n" +
                 "        return obj;  // 1 Function Error\n" +
                 "    }\n" +
                 "}\n";
         //new ByteArrayInputStream(ip.getBytes())
         Node root = new Tchk(System.in).Goal();
         root.accept(new TypeCheckVisitor(), null); // Your assignment part is invoked here.
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 
