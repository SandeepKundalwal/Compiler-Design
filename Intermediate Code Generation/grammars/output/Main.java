import syntaxtree.*;
import visitor.*;

import java.io.ByteArrayInputStream;

public class Main {
   public static void main(String [] args) {
      try {
         String code = "package cs502;\n" +
                 "\n" +
                 "import static cs502.memmgr.MemMgr.*;\n" +
                 "\n" +
                 "public class Main {\n" +
                 "public static void main(String[] args) {\n" +
                 "Object o1;\n" +
                 "Object o2;\n" +
                 "Object t;\n" +
                 "Object vTablePtr;\n" +
                 "String fnName;\n" +
                 "int x;\n" +
                 "o1 = alloc(8);\n" +
                 "store(o1, 4, 0);\n" +
                 "o2 = alloc(8);\n" +
                 "store(o2, 0, \"MOne_m1\");\n" +
                 "store(o2, 4, \"MTwo_m2\");\n" +
                 "store(o1, 0, o2);\n" +
                 "t = o1;\n" +
                 "vTablePtr = load(t, 0);\n" +
                 "fnName = (String) load(vTablePtr, 0);\n" +
                 "x = (Integer) callFunc(fnName, t);\n" +
                 "vTablePtr = load(t, 0);\n" +
                 "fnName = (String) load(vTablePtr, 4);\n" +
                 "x = (Integer) callFunc(fnName, t, 1);\n" +
                 "System.out.println(x);\n" +
                 "}\n" +
                 "public static int MOne_m1(Object mthis) {\n" +
                 "store(mthis, 4, 5);\n" +
                 "System.out.println((Integer) load(mthis, 4));\n" +
                 "int t1;\n" +
                 "t1 = (Integer) load(mthis, 4);\n" +
                 "return t1;\n" +
                 "}\n" +
                 "public static int MTwo_m2(Object mthis, int one) {\n" +
                 "int t1;\n" +
                 "int t2;\n" +
                 "boolean t3;\n" +
                 "int t4;\n" +
                 "t4 = 1;\n" +
                 "t1 = (Integer) load(mthis, 4);\n" +
                 "t3 = one < t1;\n" +
                 "while(t3){\n" +
                 "t1 = (Integer) load(mthis, 4);\n" +
                 "v1 = t1 - one;\n" +
                 "store(mthis, 4, t2);\n" +
                 "t1 = (Integer) load(mthis, 4);\n" +
                 "t4 = t4 * t1;\n" +
                 "t1 = (Integer) load(mthis, 4);\n" +
                 "t3 = one < t1;\n" +
                 "}\n" +
                 "return t4;\n" +
                 "}\n" +
                 "}\n";
         Node root = new TempNoJavaParser(new ByteArrayInputStream(code.getBytes())).Goal();
         System.out.println("Program parsed successfully");
         root.accept(new GJNoArguDepthFirst()); // Your assignment part is invoked here.
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 
