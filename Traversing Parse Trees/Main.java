import model.Response;
import syntaxtree.*;
import visitor.*;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.lang.*;

public class Main {
   public static void main(String [] args) {
      try {
         String code = "class Test {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(10));\n" +
                 "    }\n" +
                 "}\n" +
                 "class A {\n" +
                 "    public int foo(int p) { //no of params = 1\n" +
                 "        int x; //local var1\n" +
                 "        double y; //local var2\n" +
                 "        boolean b; //local var3\n" +
                 "        int r; //local var4\n" +
                 "        x = 10;\n" +
                 "        y = 100.25;\n" +
                 "        b = true;\n" +
                 "        while (b) {\n" +
                 "            y = y + x; //binop1\n" +
                 "            if (y != 200) { //binop2\n" +
                 "                b = false;\n" +
                 "            }\n" +
                 "        }\n" +
                 "        r = this.bar();\n" +
                 "        return r;\n" +
                 "    }\n" +
                 "    public int bar() { //no of params = 0\n" +
                 "        int a; //local var1\n" +
                 "        int b; //local var2\n" +
                 "        int c; //local var3\n" +
                 "        int d; //local var4\n" +
                 "        a = 10;\n" +
                 "        b = 20;\n" +
                 "        c = a + b; //binop1\n" +
                 "        d = a - b; //binop2\n" +
                 "        if(d != (a * c)){ //binop3 //binop4\n" +
                 "            a = c * d; //binop5\n" +
                 "        }\n" +
                 "        return a;\n" +
                 "    }\n" +
                 "}\n" +
                 "class B extends A {}";

         String code1 = "class Test1 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(10));\n" +
                 "    }\n" +
                 "}\n" +
                 "class A {\n" +
                 "    public int foo(int p) { //no of params = 1\n" +
                 "        int x; //local var1\n" +
                 "        double y; //local var2\n" +
                 "        boolean b; //local var3\n" +
                 "        int r; //local var4\n" +
                 "        x = 10;\n" +
                 "        y = 100.25;\n" +
                 "        b = true;\n" +
                 "        while (b) {\n" +
                 "            y = y + x; //binop1\n" +
                 "            if (y != 200) { //binop2\n" +
                 "                b = false;\n" +
                 "            }\n" +
                 "        }\n" +
                 "        r = this.bar();\n" +
                 "        return r;\n" +
                 "    }\n" +
                 "    public int bar() { //no of params = 0\n" +
                 "        int a; //local var1\n" +
                 "        int b; //local var2\n" +
                 "        int c; //local var3\n" +
                 "        int d; //local var4\n" +
                 "        a = 10;\n" +
                 "        b = 20;\n" +
                 "        c = a + b; //binop1\n" +
                 "        d = a - b; //binop2\n" +
                 "        if(d != (a * c)){ //binop3 //binop4\n" +
                 "            a = c * d; //binop5\n" +
                 "        }\n" +
                 "        return a;\n" +
                 "    }\n" +
                 "}\n" +
                 "class B extends A {}";

         String code2 = "class Test2 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "    \t System.out.println(new AType().foo(args.length));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class AType {\n" +
                 "\tpublic boolean foo(int len) {//no of params = 1\n" +
                 "\t\tint [] arr; //local var1\n" +
                 "        int i; //local var2\n" +
                 "        int x; //local var3\n" +
                 "        i=0;\n" +
                 "        arr= new int[10];\n" +
                 "        while(i!=len){ //binop1\n" +
                 "            arr[i] = i+100; //binop2\n" +
                 "        }\n" +
                 "        i=0;\n" +
                 "        while(i!=len){ //binop3\n" +
                 "            x=arr[i];\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        return true;\n" +
                 "\t}\n" +
                 "}";

         String code3 = "class Test3 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo());\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    public int foo() { //no of params = 0\n" +
                 "        int x; //local var1\n" +
                 "        double y; //local var2\n" +
                 "        double z; //local var3 \n" +
                 "        y = 5;\n" +
                 "        x = 7 * 2; //binop 1\n" +
                 "        z = y + 3; //binop 2\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         String code4 = "class Test4 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(1+1,2+2)); //binop 1 //binop 2\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    public double foo(int p, int q) { //no of params = 2\n" +
                 "        int x;\n" +
                 "        int y;\n" +
                 "        x = 10; //local var1\n" +
                 "        y = 20; //local var2\n" +
                 "        if ((p+q) != (x+y)) { //binop 1 //binop 2 //binop 3\n" +
                 "            System.out.println((p+q)+(x+y)); //binop 4 //binop 5 //binop 6 \n" +
                 "        }\n" +
                 "        return 20.25;\n" +
                 "    }\n" +
                 "}\n";

         String code5 = "class Test5 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(true, false));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    public int foo(boolean p, boolean q) { //no of params = 2\n" +
                 "        int x; //local var1\n" +
                 "        int y; //local var2\n" +
                 "        boolean z; //local var3\n" +
                 "        boolean w; //local var4\n" +
                 "        x = 10;\n" +
                 "        y = 20;\n" +
                 "        if (p) {\n" +
                 "            z = ((25.02 + 78.2) != 100.99); //binop 1 //binop 2 \n" +
                 "            w = z && q; //binop 3\n" +
                 "        }\n" +
                 "        x = new B().bar(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class B extends A {\n" +
                 "    public int bar(int p) { //no of params = 1\n" +
                 "        int x; //local var1\n" +
                 "        int y; //local var2\n" +
                 "        y = 2;\n" +
                 "        x = p + y; //binop 1\n" +
                 "        p = this.foobar();\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "\n" +
                 "    public int foobar() { //no of params = 0\n" +
                 "        return 10;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class C extends A {\n" +
                 "    public int foobar() { //no of params = 0\n" +
                 "        return 20;\n" +
                 "    }\n" +
                 "}\n";

         String code6 = "class Test6 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(10, 20));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "\n" +
                 "    public int foo(int p, int q) { //no of params = 2\n" +
                 "        int[] x; //local var1\n" +
                 "        int i; //local var2\n" +
                 "        int n; //local var3\n" +
                 "        int z; //local var4\n" +
                 "        int sum; //local var5\n" +
                 "        boolean b; //local var6\n" +
                 "        b=true;\n" +
                 "        x = new int[p];\n" +
                 "        i = 0;\n" +
                 "        n = p - 1; //binop 1 \n" +
                 "        while (i == n) { //binop 2 \n" +
                 "            x[i] = i;\n" +
                 "        }\n" +
                 "        sum = 0;\n" +
                 "        while (!b) {\n" +
                 "            z = x[i];\n" +
                 "            sum = sum * z; //binop 3\n" +
                 "        }\n" +
                 "        return (new int[7*5]).length; //binop 4\n" +
                 "    }\n" +
                 "\n" +
                 "    public int[] booltest() { //no of params = 0\n" +
                 "        boolean b; //local var1\n" +
                 "        b = true;\n" +
                 "        b = !b;\n" +
                 "        b = b != !b; //binop 1\n" +
                 "        b = b && b; //binop 2\n" +
                 "        return new int[7*5]; //binop 3 \n" +
                 "    }\n" +
                 "}\n";

         String code7 = "class Test7 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A().foo(10,11));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    public int foo(int p, int q) { //no of params = 2\n" +
                 "        int[] x; //local var1\n" +
                 "        x = new int[(5+(7+9))]; //binop1 //binop2 \n" +
                 "        return ((x.length) + (x.length)); //binop3\n" +
                 "    }\n" +
                 "}";

         String code8 = "class Test8 {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "        System.out.println(new A1().foo(12.5));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A1 {\n" +
                 "    public double foo(double d) { //no of params = 1\n" +
                 "        return d+10.531556; //binop1\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A0 extends A1{\n" +
                 "    public double foo(double d) { //no of params = 1\n" +
                 "        return d+85; //binop1\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A10 extends A1{\n" +
                 "    public double foo(double d) { //no of params = 1\n" +
                 "        return d+2362; //binop1\n" +
                 "    }\n" +
                 "}";

         String code9 = "class ArrayTest {\n" +
                 "    public static void main(String[] args) { //no of params = 1\n" +
                 "    \t System.out.println(new AType().foo(args.length));\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class AType {\n" +
                 "\tpublic boolean foo(int len) {//no of params = 1\n" +
                 "\t\tint [] arr; //local var1\n" +
                 "        int i; //local var2\n" +
                 "        int x; //local var3\n" +
                 "        i=0;\n" +
                 "        arr= new int[10];\n" +
                 "        while(i!=len){ //binop1\n" +
                 "            arr[i] = i+100; //binop2\n" +
                 "        }\n" +
                 "        i=0;\n" +
                 "        while(i!=len){ //binop3\n" +
                 "            x=arr[i];\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        return true;\n" +
                 "\t}\n" +
                 "}";

         /**
          * To test the above codes
          */
         //Node root = new JTLang(new ByteArrayInputStream(code.getBytes())).Goal();
         Node root = new JTLang(System.in).Goal();
         System.out.println("Program parsed successfully");
         root.accept(new GJNoArguDepthFirst()); // Your assignment part is invoked here.
      }
      catch ( ParseException e) {
         System.out.println(e.toString());
      }
   }
} 
