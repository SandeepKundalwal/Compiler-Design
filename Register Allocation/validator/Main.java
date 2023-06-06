import syntaxtree.*;
import visitor.*;

import java.io.ByteArrayInputStream;

public class Main {
   public static void main(String [] args) {
      try {
         String code1 = "/*2*/\n" +
                 "import static a5.Memory.*;\n" +
                 "class TC02 {\n" +
                 "public static void main(String[] args) {\n" +
                 "Object R0;\n" +
                 "Object R1;\n" +
                 "alloca(0);\n" +
                 "R0 = new TestTC02();\n" +
                 "R0 = ((TestTC02) R0).foo();\n" +
                 "System.out.println(((int) R0));\n" +
                 "}\n" +
                 "}\n" +
                 "\n" +
                 "class TestTC02 {\n" +
                 "public int foo() {\n" +
                 "Object R0;\n" +
                 "Object R1;\n" +
                 "alloca(1);\n" +
                 "store(0, 5);\n" +
                 "R0 = 6;\n" +
                 "R0 = ((int) load(0)) + ((int) R0);\n" +
                 "R1 = ((int) R0) + ((int) load(0));\n" +
                 "R0 = ((int) load(0)) - ((int) R0);\n" +
                 "R0 = ((int) R1) - ((int) R0);\n" +
                 "return ((int) R0);\n" +
                 "}\n" +
                 "}";

         String code2 = "/*1*/\n" +
                 "import static a5.Memory.*;\n" +
                 "class TC21 {\n" +
                 "public static void main(String[] a) {\n" +
                 "Object R0;\n" +
                 "alloca(1);\n" +
                 "R0 = new Fac();\n" +
                 "store(0, 10);\n" +
                 "R0 = ((Fac) R0).ComputeFac(((int) load(0)));\n" +
                 "System.out.println(((int) R0));\n" +
                 "}\n" +
                 "}\n" +
                 "\n" +
                 "class Fac {\n" +
                 "public int ComputeFac(int num) {\n" +
                 "Object R0;\n" +
                 "alloca(1);\n" +
                 "R0 = 0;\n" +
                 "R0 = num <= ((int) R0);\n" +
                 "if(((boolean) R0)) {\n" +
                 "R0 = 1;\n" +
                 "R0 = ((int) R0);\n" +
                 "} else{\n" +
                 "store(0, new Fac());\n" +
                 "R0 = 1;\n" +
                 "R0 = num - ((int) R0);\n" +
                 "R0 = ((Fac) load(0)).ComputeFac(((int) R0));\n" +
                 "R0 = num * ((int) R0);\n" +
                 "R0 = ((int) R0);\n" +
                 "}\n" +
                 "return ((int) R0);\n" +
                 "}\n" +
                 "}";

         String code25 = "/*1*/\n" +
                 "import static a5.Memory.*;\n" +
                 "class TC25 {\n" +
                 "public static void main(String[] args) {\n" +
                 "Object R0;\n" +
                 "alloca(0);\n" +
                 "R0 = new TCH25();\n" +
                 "R0 = ((TCH25) R0).foo();\n" +
                 "}\n" +
                 "}\n" +
                 "\n" +
                 "class TCH25 {\n" +
                 "public int foo() {\n" +
                 "Object R0;\n" +
                 "alloca(3);\n" +
                 "store(2, new TCH25());\n" +
                 "store(0, 10);\n" +
                 "store(1, 20);\n" +
                 "R0 = ((TCH25) load(2)).bar(((int) load(0)),((int) load(1)));\n" +
                 "System.out.println(((int) R0));\n" +
                 "return ((int) R0);\n" +
                 "}\n" +
                 "public int bar(int p1, int p2) {\n" +
                 "Object R0;\n" +
                 "alloca(0);\n" +
                 "p2 = p1;\n" +
                 "return p2;\n" +
                 "}\n" +
                 "}";
         Node root = new FriendTJMem(new ByteArrayInputStream(code25.getBytes())).Goal();
         Validator v = new Validator();
         root.accept(v);
         System.out.println("Spills: " + v.totalSpills);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
