//import syntaxtree.*;
//import visitor.*;

import syntaxtree.Node;
import visitor.GJNoArguDepthFirst;
import java.io.ByteArrayInputStream;

public class Main {
    public static void main(String [] args) {
        try {
            String code = "package cs502;\n" +
                    "\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        B b;\n" +
                    "        int r;\n" +
                    "\n" +
                    "        b = new B();\n" +
                    "\n" +
                    "        r = b.set(20, 30);\n" +
                    "\n" +
                    "        r = b.m1(10);\n" +
                    "        System.out.println(r);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class A {\n" +
                    "    int x;\n" +
                    "    int y;\n" +
                    "\n" +
                    "    public int m1(int z) {\n" +
                    "        int t1;\n" +
                    "        int t2;\n" +
                    "\n" +
                    "        t1 = x + y;\n" +
                    "        t2 = t1 + z;\n" +
                    "        return t2;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "    public int m2() {\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m3() {\n" +
                    "        System.out.println(3);\n" +
                    "        return 3;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m4() {\n" +
                    "        System.out.println(4);\n" +
                    "        return 4;\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "class B extends A {\n" +
                    "    int x;\n" +
                    "    int z;\n" +
                    "\n" +
                    "    public int m1(int p) {\n" +
                    "        int t1;\n" +

                    "        int t2;\n" +
                    "        int t3;\n" +
                    "        int t4;\n" +
                    "        int t5;\n" +
                    "        B t6;\n" +
                    "\n" +

                    "        t6 = this;\n" +

                    "        t1 = t6.m2();\n" +
                    "        t2 = x * 2;\n" +
                    "        t3 = t1 + t2;\n" +

                    "        t4 = t3 - p;\n" +
                    "        t5 = t1 + t4;\n" +

                    "        return t5;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int set(int a, int b) {\n" +
                    "        x = a;\n" +
                    "        z = b;\n" +
                    "        return 5;\n" +
                    "    }\n" +
                    "}\n";

            String code1 = "package cs502;\n" +
                    "\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        B b;\n" +
                    "        int r;\n" +
                    "        A a;\n" +
                    "\n" +
                    "        b = new B();\n" +
                    "\n" +
                    "        r = b.set(20, 30);\n" +
                    "\n" +
                    "        r = a.x;\n" +
                    "        b.x = r;\n" +
                    "        a = new A();\n" +
                    "        r = a.m1(12);\n" +
                    "        r = b.m1(10);\n" +
                    "        r = r + 8;\n" +
                    "        r = r < 12;\n" +
                    "        System.out.println(r);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class A {\n" +
                    "    int x;\n" +
                    "    int y;\n" +
                    "\n" +
                    "    public int m1(int z) {\n" +
                    "        int t1;\n" +
                    "        int t2;\n" +
                    "\n" +
                    "        t1 = x + y;\n" +
                    "        t2 = t1 + z;\n" +
                    "        return t2;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "    public int m2() {\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m3() {\n" +
                    "        System.out.println(3);\n" +
                    "        return 3;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m4() {\n" +
                    "        System.out.println(4);\n" +
                    "        return 4;\n" +
                    "    }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "class B extends A {\n" +
                    "    int x;\n" +
                    "    int z;\n" +
                    "\n" +
                    "    public int m1(int p) {\n" +
                    "        int t1;\n" +
                    "        A a;\n" +
                    "        int t2;\n" +
                    "        int t3;\n" +
                    "        int t4;\n" +
                    "        int t5;\n" +
                    "        B t6;\n" +
                    "\n" +
                    "        t6 = this;\n" +
                    "        a = new A();\n" +
                    "        t1 = t6.z;\n" +
                    "        a.x = z;\n" +
                    "\n" +
                    "        t1 = a.m1();\n" +
                    "        t2 = y * 2;\n" +
                    "        t3 = t1 + t2;\n" +
                    "        t1 = t2 || x;\n" +
                    "        t4 = t3 - p;\n" +
                    "        t4 = x / 4;\n" +
                    "        t5 = t1 + t4;\n" +
                    "        t5 = a.m2();\n" +
                    "        return t5;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int set(int a, int b) {\n" +
                    "        boolean isClass;\n" +
                    "        isClass = true;\n" +
                    "        x = z;\n" +
                    "        z = b;\n" +
                    "        isClass = !isClass;\n" +
                    "        while(isClass){\n" +
                    "        x = z;\n" +
                    "        }\n" +
                    "        return 5;\n" +
                    "    }\n" +
                    "}\n";


            String code2 = "package cs502;\n" +
                    "\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        A a;\n" +
                    "        int r;\n" +
                    "        a = new A();\n" +
                    "        r = a.m1(10);\n" +
                    "        System.out.println(r);\n" +
                    "        r = a.m3();\n" +
                    "        System.out.println(r);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class A {\n" +
                    "    int x;\n" +
                    "    int y;\n" +
                    "\n" +
                    "    public int m1(int z) {\n" +
                    "        int t1;\n" +
                    "        int t2;\n" +
                    "\n" +
                    "        t1 = x + y;\n" +
                    "        t2 = t1 + z;\n" +
                    "        return t2;\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "    public int m2() {\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m3() {\n" +
                    "        System.out.println(3);\n" +
                    "        return 3;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m4() {\n" +
                    "        System.out.println(4);\n" +
                    "        return 4;\n" +
                    "    }\n" +
                    "\n" +
                    "}";

            String code3 = "package cs502;\n" +
                    "// arithmatic operations\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        \n" +
                    "        int x1;\n" +
                    "        int x2;\n" +
                    "        int x3;\n" +
                    "        int x4;\n" +
                    "        G g;\n" +
                    "        \n" +
                    "        g = new G();\n" +
                    "        \n" +
                    "        x1 = g.m1(51, 23);\n" +
                    "        x2 = g.m2(44,66);\n" +
                    "        x3 = g.m3(21, 7);\n" +
                    "        x4 = g.m4(34,35);\n" +
                    "        \n" +
                    "        System.out.println(x1);\n" +
                    "        System.out.println(x2);\n" +
                    "        System.out.println(x3);\n" +
                    "        System.out.println(x4);        \n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class G{\n" +
                    "int a;\n" +
                    "int b;\n" +
                    "\n" +
                    "public int m1(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x/y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "\n" +
                    "public int m2(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x-y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "public int m3(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x*y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "public int m4(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x+y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "}\n" +
                    "\n";

            String code4 = "package cs502;\n" +
                    "// call from one class to other\n" +
                    "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        E a;\n" +
                    "        int a1;\n" +
                    "        int a2;\n" +
                    "        a = new E();\n" +
                    "        a1 = a.m1(10);\n" +
                    "        System.out.println(a1);\n" +
                    "        a2 = a.m2();\n" +
                    "        System.out.println(a2);\n" +
                    "    }\n" +
                    "}\n" +
                    "class E {\n" +
                    "    int x;\n" +
                    "    int y;\n" +
                    "    public int m1(int z) {\n" +
                    "        int t1;\n" +
                    "        int t2;\n" +
                    "        t1 = x + y;\n" +
                    "        t2 = t1 + z;\n" +
                    "        return t2;\n" +
                    "    }\n" +
                    "    public int m2() {\n" +
                    "        F b;\n" +
                    "        int x;\n" +
                    "        int q;\n" +
                    "        int r;\n" +
                    "\n" +
                    "        b = new F();\n" +
                    "        // to initialize\n" +
                    "        x = b.m1(7);\n" +
                    "        q = b.m2(9);\n" +
                    "        r = b.m2(6);\n" +
                    "\n" +
                    "        System.out.println(q);\n" +
                    "        System.out.println(r);\n" +
                    "\n" +
                    "        return x;\n" +
                    "\n" +
                    "    }\n" +
                    "}\n" +
                    "class F {\n" +
                    "    int x;\n" +
                    "    public int m1(int z) {\n" +
                    "        x = z;\n" +
                    "        return 1;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m2(int z) {\n" +
                    "        boolean y;\n" +
                    "        boolean zx;\n" +
                    "        zx = z<x;\n" +
                    "        if(zx) {\n" +
                    "            y= true;\n" +
                    "            x = z*z;\n" +
                    "        }\n" +
                    "        else{ y=false;\n" +
                    "            x = z/2;\n" +
                    "        }\n" +
                    "        System.out.println(y);\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "}";

            String code5 = "package cs502;\n" +
                    "// call from one class to other\n" +
                    "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        E a;\n" +
                    "        int a1;\n" +
                    "        int a2;\n" +
                    "        a = new E();\n" +
                    "        a1 = a.m1(10);\n" +
                    "        System.out.println(a1);\n" +
                    "        a2 = a.m2();\n" +
                    "        System.out.println(a2);\n" +
                    "    }\n" +
                    "}\n" +
                    "class E {\n" +
                    "    int x;\n" +
                    "    int y;\n" +
                    "    public int m1(int z) {\n" +
                    "        int t1;\n" +
                    "        int t2;\n" +
                    "        t1 = x + y;\n" +
                    "        t2 = t1 + z;\n" +
                    "        return t2;\n" +
                    "    }\n" +
                    "    public int m2() {\n" +
                    "        F b;\n" +
                    "        int x;\n" +
                    "        int q;\n" +
                    "        int r;\n" +
                    "\n" +
                    "        b = new F();\n" +
                    "        // to initialize\n" +
                    "        x = b.m1(7);\n" +
                    "        q = b.m2(9);\n" +
                    "        r = b.m2(6);\n" +
                    "\n" +
                    "        System.out.println(q);\n" +
                    "        System.out.println(r);\n" +
                    "\n" +
                    "        return x;\n" +
                    "\n" +
                    "    }\n" +
                    "}\n" +
                    "class F {\n" +
                    "    int x;\n" +
                    "    public int m1(int z) {\n" +
                    "        x = z;\n" +
                    "        return 1;\n" +
                    "    }\n" +
                    "\n" +
                    "    public int m2(int z) {\n" +
                    "        boolean y;\n" +
                    "        boolean zx;\n" +
                    "        zx = z<x;\n" +
                    "        if(zx) {\n" +
                    "            y= true;\n" +
                    "            x = z*z;\n" +
                    "        }\n" +
                    "        else{ y=false;\n" +
                    "            x = z/2;\n" +
                    "        }\n" +
                    "        System.out.println(y);\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "}\n";

            String code6 = "package cs502;\n" +
                    "\n" +
                    "\n" +
                    "// arithmatic operations\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        \n" +
                    "        int x1;\n" +
                    "        int x2;\n" +
                    "        int x3;\n" +
                    "        int x4;\n" +
                    "        G g;\n" +
                    "        \n" +
                    "        g = new G();\n" +
                    "        \n" +
                    "        x1 = g.m1(51, 23);\n" +
                    "        x2 = g.m2(44,66);\n" +
                    "        x3 = g.m3(21, 7);\n" +
                    "        x4 = g.m4(34,35);\n" +
                    "        \n" +
                    "        System.out.println(x1);\n" +
                    "        System.out.println(x2);\n" +
                    "        System.out.println(x3);\n" +
                    "        System.out.println(x4);        \n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class G{\n" +
                    "int a;\n" +
                    "int b;\n" +
                    "\n" +
                    "public int m1(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x/y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "\n" +
                    "public int m2(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x-y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "public int m3(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x*y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "public int m4(int x, int y){\n" +
                    "\tint rt;\n" +
                    "\trt = x+y;\n" +
                    "\treturn rt;\n" +
                    "}\n" +
                    "}\n";

            String code7 = "package cs502;\n" +
                    "// while with if-else\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        H h;\n" +
                    "        int h1;\n" +
                    "   \n" +
                    "        h = new H();\n" +
                    "        h1 = h.m1(8);\n" +
                    "        System.out.println(h1);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class H {\n" +
                    "\n" +
                    "public int m1(int x){\n" +
                    "\tint i;\n" +
                    "\tint offset;\n" +
                    "\tint five;\n" +
                    "\tboolean cond;\n" +
                    "\tboolean check;\n" +
                    "\tint ltf;\n" +
                    "\tint gtef;\n" +
                    "\t\n" +
                    "\tltf = 1234;\n" +
                    "\tgtef = 5678;\n" +
                    "\tfive = 5;\n" +
                    "\ti=0;\n" +
                    "\toffset = 1;\n" +
                    "\tcond = i<x;\n" +
                    "\twhile(cond){\n" +
                    "\t\ti = i+offset;\n" +
                    "\t\tcheck = i<five;\n" +
                    "\t\tif(check){\n" +
                    "\t\tSystem.out.println(ltf);\n" +
                    "\t\t}else{\n" +
                    "\t\tSystem.out.println(gtef);\n" +
                    "\t\t}\n" +
                    "\t\t\n" +
                    "\t\tcond = i<x;\n" +
                    "\t}\n" +
                    "\treturn i;\n" +
                    "}\n" +
                    "\n" +
                    "}\n";

            String code8 = "//inheritance 1(hierarchial)\n" +
                    "package cs502;\n" +
                    "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        Rectangle r;\n" +
                    "        Triangle t;\n" +
                    "        int ra;\n" +
                    "        int ta;\n" +
                    "        int x;\n" +
                    "\n" +
                    "        r = new Rectangle();\n" +
                    "        t = new Triangle();\n" +
                    "\n" +
                    "        x = r.set_data(4,5);\n" +
                    "        ra = r.rect_area();\n" +
                    "        System.out.println(ra);\n" +
                    "\n" +
                    "        x = t.set_data(4,5);\n" +
                    "        ta = t.triangle_area();\n" +
                    "        System.out.println(ta);\n" +
                    "    }\n" +
                    "}\n" +
                    "class Shape\n" +
                    "{\n" +
                    "    int a;\n" +
                    "    int b;\n" +
                    "    public int set_data(int n,int m)\n" +
                    "    {\n" +
                    "        a=n;\n" +
                    "        b=m;\n" +
                    "        return a;\n" +
                    "    }\n" +
                    "}\n" +
                    "class Rectangle extends Shape\n" +
                    "{\n" +
                    "    public int rect_area()\n" +
                    "    {\n" +
                    "        int result;\n" +
                    "        result = a*b;\n" +
                    "        return result;\n" +
                    "    }\n" +
                    "}\n" +
                    "class Triangle extends Shape\n" +
                    "{\n" +
                    "    public int triangle_area()\n" +
                    "    {\n" +
                    "        int result;\n" +
                    "        int mul;\n" +
                    "        int half;\n" +
                    "        half = 2;\n" +
                    "        mul = a*b;\n" +
                    "        result = mul/half;\n" +
                    "        return result;\n" +
                    "    }\n" +
                    "}\n";

            String code9 = "//inheritance 2 (single level)\n" +
                    "package cs502;\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        Two t;\n" +
                    "        int x;\n" +
                    "        \n" +
                    "        t = new Two();\n" +
                    "\t\tx = t.m1();\n" +
                    "\t\tx = t.m2();\n" +
                    "\t\tx = t.m1();\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class One {\n" +
                    "\tint v1;\n" +
                    "\t\n" +
                    "    \tpublic int m1(){\n" +
                    "\t\t\tv1 = 1;\n" +
                    "\t\t\tSystem.out.println(v1);\n" +
                    "\t\t\treturn v1;\n" +
                    "    }\n" +
                    "}\n" +
                    " \n" +
                    "class Two extends One {\n" +
                    "\tint v2;\n" +
                    "\t\n" +
                    "    \tpublic int m2() { \n" +
                    "\t\t\tv2 = 2;\n" +
                    "\t\t\tSystem.out.println(v2); \n" +
                    "\t\t\treturn v2;\n" +
                    "    }\n" +
                    "}\n";

            String code10 = "//inheritance 2 (multi level)\n" +
                    "package cs502;\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        All all;\n" +
                    "        \n" +
                    "        int x;\n" +
                    "        int a;\n" +
                    "    \tint b;\n" +
                    "    \tint c;\n" +
                    "        \n" +
                    "        \n" +
                    "        all = new All();\n" +
                    "        a = all.m1();\n" +
                    "\t\tb = all.m4();\n" +
                    "\t\tc = all.m3();\n" +
                    "\t\tx = all.m411();\n" +
                    "\t\t\n" +
                    "  \t\tSystem.out.println(x); \n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class One {\n" +
                    "\tint v1;\n" +
                    "\t\n" +
                    "\tpublic int m1(){\n" +
                    "    \tv1 = 1;\n" +
                    "        System.out.println(v1);\n" +
                    "        return v1;\n" +
                    "    }\n" +
                    "}\n" +
                    " \n" +
                    "class Four extends One {\n" +
                    "\tint v2;\n" +
                    "\t\n" +
                    "\tpublic int m4() { \n" +
                    "\t\tv2 = 4;\n" +
                    "    \tSystem.out.println(v2); \n" +
                    "    \treturn v2;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class Three extends Four {\n" +
                    "\tint v3;\n" +
                    "\t\n" +
                    "\tpublic int m3() { \n" +
                    "\t\tv3 = 3;\n" +
                    "\t\tSystem.out.println(v3); \n" +
                    "\t\treturn v2;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class All extends Three {\n" +
                    "\tint all;\n" +
                    "\t\n" +
                    "    public int m411() { \n" +
                    "\t\tall = 411;\n" +
                    "\t\treturn all;\n" +
                    "    }\n" +
                    "    \n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n";

            String code14 = "//if while single inheritance\n" +
                    "package cs502;\n" +
                    "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        MTwo t;\n" +
                    "        int x;\n" +
                    "\n" +
                    "        t = new MTwo();\n" +
                    "        x = t.m1();\n" +
                    "        x = t.m2(1);\n" +
                    "        System.out.println(x);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class MOne {\n" +
                    "    int v1;\n" +
                    "\n" +
                    "    public int m1(){\n" +
                    "        v1 = 5;\n" +
                    "        System.out.println(v1);\n" +
                    "        return v1;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class MTwo extends MOne {\n" +
                    "    public int m2(int one) {\n" +
                    "        boolean check;\n" +
                    "        int sq;\n" +
                    "\n" +
                    "        sq = 1;\n" +
                    "        check = one <v1;\n" +
                    "        while(check){\n" +
                    "            v1 = v1 - one;\n" +
                    "            sq = sq * v1;\n" +
                    "            check = one < v1;\n" +
                    "\n" +
                    "        }\n" +
                    "        // System.out.println(v1);\n" +
                    "        return sq;\n" +
                    "    }\n" +
                    "}\n";

            String code15 = "//while multilevel inheritance\n" +
                    "package cs502;\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        NThree nt;\n" +
                    "\n" +
                    "        int a;\n" +
                    "    \tint b;\n" +
                    "    \tint c;\n" +
                    "        \n" +
                    "        \n" +
                    "        nt = new NThree();\n" +
                    "        a = nt.m1(2);\n" +
                    "  \t    System.out.println(a); \n" +
                    "\n" +
                    "        b = nt.m2(4);\n" +
                    "  \t    System.out.println(b); \n" +
                    "\n" +
                    "        c = nt.m3(6);\n" +
                    "  \t    System.out.println(c); \n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class NOne {\n" +
                    "\tint v1;\n" +
                    "\t\n" +
                    "    \tpublic int m1(int one){\n" +
                    "            boolean check;\n" +
                    "            int sq;\n" +
                    "            v1 = 3;\n" +
                    "            \n" +
                    "            sq = 1;\n" +
                    "            check = one < v1;\n" +
                    "            while(check){\n" +
                    "                v1 = v1 - one;\n" +
                    "                sq = sq * v1;\n" +
                    "                check = one < v1;\n" +
                    "                \n" +
                    "            }\n" +
                    "        return sq;\n" +
                    "    }\n" +
                    "}\n" +
                    " \n" +
                    "class NFour extends NOne {\n" +
                    "\tint v2;\n" +
                    "\t\n" +
                    "    \tpublic int m2(int one) { \n" +
                    "\t    \n" +
                    "            boolean check;\n" +
                    "            int sq;\n" +
                    "            v2 = 10;\n" +
                    "            \n" +
                    "            sq = 1;\n" +
                    "            check = v1 < v2;\n" +
                    "            while(check){\n" +
                    "                v2 = v2 - one;\n" +
                    "                sq = sq * v2;\n" +
                    "                check = v1 < v2;\n" +
                    "                \n" +
                    "            }\n" +
                    "        return sq;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class NThree extends NFour {\n" +
                    "\tint v3;\n" +
                    "\t\n" +
                    "    \tpublic int m3(int one) { \n" +
                    "\t    \n" +
                    "            boolean check;\n" +
                    "            int sq;\n" +
                    "            v3 = 20;\n" +
                    "\n" +
                    "            sq = 1;\n" +
                    "            check = v1 < v3;\n" +
                    "            while(check){\n" +
                    "                v3 = v3 - one;\n" +
                    "                sq = sq * v3;\n" +
                    "                check = v1 < v3;\n" +
                    "                \n" +
                    "            }\n" +
                    "        return sq;\n" +
                    "    }\n" +
                    "    \n" +
                    "}\n";

            String code16 = "//if while multiple\n" +
                    "package cs502;\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        ORectangle r;\n" +
                    "        OTriangle t;\n" +
                    "        int ra;\n" +
                    "        int ta;\n" +
                    "        int x;\n" +
                    "  \t\n" +
                    "  \tr = new ORectangle();\n" +
                    "  \tt = new OTriangle();\n" +
                    "  \t\n" +
                    "  \tx = r.set_data(4,15);\n" +
                    "  \tra = r.rect_area();\n" +
                    "  \tSystem.out.println(ra);\n" +
                    "\n" +
                    "    ra = r.rect_peri();\n" +
                    "  \tSystem.out.println(ra);\n" +
                    "  \t\n" +
                    "    x = t.set_data(4,10);\n" +
                    "  \tta = t.triangle_area();\n" +
                    "  \tSystem.out.println(ta);\n" +
                    "    }\n" +
                    "}\n" +
                    "class OShape\n" +
                    "{\n" +
                    "    int a;\n" +
                    "    int b;\n" +
                    "    public int set_data(int n,int m)\n" +
                    "    {\n" +
                    "        // System.out.println(n+\" \"+m);\n" +
                    "      a=n;\n" +
                    "      b=m;\n" +
                    "    //   System.out.println(a+\" \"+b);\n" +
                    "      return a;\n" +
                    "    }\n" +
                    "}\n" +
                    "class ORectangle extends OShape\n" +
                    "{\n" +
                    "    int sides;\n" +
                    "\n" +
                    "    public int rect_area()\n" +
                    "    {\n" +
                    "    \n" +
                    "        int result;\n" +
                    "        sides = 4;\n" +
                    "        result = a*b;\n" +
                    "        \n" +
                    "        return result;\n" +
                    "    }\n" +
                    "    public int rect_peri()\n" +
                    "    {\n" +
                    "    \n" +
                    "        int result;\n" +
                    "        boolean check;\n" +
                    "        int zero;\n" +
                    "        int one;\n" +
                    "        int two;\n" +
                    "\n" +
                    "        zero = 0;\n" +
                    "        one = 1;\n" +
                    "        two = 2;\n" +
                    "        result = 0;\n" +
                    "        check = zero < sides;\n" +
                    "        while(check){\n" +
                    "            check = two < sides;\n" +
                    "            if(check){\n" +
                    "                result = result + a;\n" +
                    "            }else{\n" +
                    "                result = result + b;\n" +
                    "            }\n" +
                    "            sides = sides - one;\n" +
                    "            check = zero < sides;\n" +
                    "        }\n" +
                    "        // System.out.println(a+\" \"+b);\n" +
                    "        result = result/two;\n" +
                    "        return result;\n" +
                    "    }\n" +
                    "}\n" +
                    "class OTriangle extends OShape\n" +
                    "{\n" +
                    "    public int triangle_area()\n" +
                    "    {\n" +
                    "      int result;\n" +
                    "      int mul;\n" +
                    "      int half;\n" +
                    "      half = 2;\n" +
                    "      mul = a*b;\n" +
                    "    //   System.out.println(a+\" \"+b);\n" +
                    "      result = mul/half;\n" +
                    "      return result;\n" +
                    "    }\n" +
                    "}\n";

            String code18 = "package cs502;\n" +
                    "// if-else\n" +
                    "public class Test {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        D b;\n" +
                    "        int x;\n" +
                    "        int y;\n" +
                    "        int w;\n" +
                    "        int p;\n" +
                    "        \n" +
                    "        b = new D();\n" +
                    "        w = b.m1(5);\n" +
                    "        System.out.println(w);\n" +
                    "        x = b.m2(4);\n" +
                    "        y = b.m2(6);\n" +
                    "        System.out.println(x);\n" +
                    "        System.out.println(y);\n" +
                    "        p = b.m3();\n" +
                    "        System.out.println(p);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "class D {\n" +
                    "    int x;\n" +
                    "    public int m1(int z) {\n" +
                    "    \tboolean y;\n" +
                    "        x = z;\n" +
                    "        y = true;\n" +
                    "        return 1;\n" +
                    "    }\n" +
                    "\n" +
                    "   public int m2(int z) {\n" +
                    "   \tboolean y;\n" +
                    "   \tboolean zx;\n" +
                    "   \tzx = z<x;\n" +
                    "        if(zx) {\n" +
                    "        y= true;\n" +
                    "        x = 1;}\n" +
                    "        else{ y=false;\n" +
                    "        x = 0;}\n" +
                    "        System.out.println(y);\n" +
                    "        \n" +
                    "        return 0;\n" +
                    "    }\n" +
                    "    public int m3() {\n" +
                    "        return x;\n" +
                    "    }\n" +
                    "}\n";

            //InputStream targetStream = new ByteArrayInputStream(code.getBytes());
            Node root = new TempoJavaParser(new ByteArrayInputStream(code18.getBytes())).Goal();
            //System.out.println("Program parsed successfully");
            root.accept(new GJNoArguDepthFirst()); // Your assignment part is invoked here.
        }
        catch (ParseException e) {
            System.out.println(e.toString());
        }
    }
}
