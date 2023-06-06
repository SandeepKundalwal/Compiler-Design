import syntaxtree.*;
import visitor.*;

import java.io.ByteArrayInputStream;
import java.util.*;

public class Main {
   public static void main(String [] args) {
      try {

         String code1 = "/*2*/\n" +
                 "class TC02 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        TestTC02 o;\n" +
                 "        int res;\n" +
                 "        o = new TestTC02();\n" +
                 "        res = o.foo();\n" +
                 "        System.out.println(res);\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class TestTC02 {\n" +
                 "    public int foo() {\n" +
                 "        int a;\n" +
                 "        int b;\n" +
                 "        int c;\n" +
                 "        int d;\n" +
                 "        int e;\n" +
                 "        int t;\n" +
                 "        a = 5;\n" +
                 "        b = 6;\n" +
                 "        c = a + b;\n" +
                 "        d = c + a;\n" +
                 "        e = a - c;\n" +
                 "        t = d - e;\n" +
                 "        return t;\n" +
                 "    }\n" +
                 "}";

         String code2 = "/*1*/\n" +
                 "class TC21 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "        Fac mtTmp2;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new Fac();\n" +
                 "        mtTmp3 = 10;\n" +
                 "        mtTmp1 = mtTmp2.ComputeFac(mtTmp3);\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class Fac {\n" +
                 "    public int ComputeFac(int num) {\n" +
                 "        int num_aux;\n" +
                 "        boolean mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        Fac mtTmp9;\n" +
                 "        int mtTmp10;\n" +
                 "        int mtTmp11;\n" +
                 "        int mtTmp8;\n" +
                 "        mtTmp5 = 0;\n" +
                 "        mtTmp4 = num <= mtTmp5;\n" +
                 "        if (mtTmp4) {\n" +
                 "            mtTmp6 = 1;\n" +
                 "            num_aux = mtTmp6;\n" +
                 "        } else {\n" +
                 "            mtTmp9 = new Fac();\n" +
                 "            mtTmp11 = 1;\n" +
                 "            mtTmp10 = num - mtTmp11;\n" +
                 "            mtTmp8 = mtTmp9.ComputeFac(mtTmp10);\n" +
                 "            mtTmp7 = num * mtTmp8;\n" +
                 "            num_aux = mtTmp7;\n" +
                 "        }\n" +
                 "        return num_aux;\n" +
                 "    }\n" +
                 "}\n";

         String code25 = "/*1*/\n" +
                 "class TC25 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int x;\n" +
                 "        TCH25 t1;\n" +
                 "        t1 = new TCH25();\n" +
                 "        x = t1.foo(); \n" +
                 "    }\n" +
                 "}\n" +
                 "class TCH25 {\n" +
                 "    public int foo() {\n" +
                 "        int x;\n" +
                 "        int p;\n" +
                 "        int q;\n" +
                 "        TCH25 refA;\n" +
                 "        TCH25 refB;\n" +
                 "        refA = new TCH25();\n" +
                 "        p =10;\n" +
                 "        q = 20;\n" +
                 "        x = refA.bar(p,q); \n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "\n" +
                 "    public int bar(int p1, int p2) {\n" +
                 "        p2 = p1;\n" +
                 "        return p2;\n" +
                 "    }\n" +
                 "}";

         String code24 = "/*2*/\n" +
                 "class TC24 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "        Fac mtTmp2;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new Fac();\n" +
                 "        mtTmp3 = 10;\n" +
                 "        mtTmp1 = mtTmp2.ComputeFac(mtTmp3);\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class Fac {\n" +
                 "    public int ComputeFac(int num) {\n" +
                 "        int num_aux;\n" +
                 "        boolean mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        Fac mtTmp9;\n" +
                 "        int mtTmp10;\n" +
                 "        int mtTmp11;\n" +
                 "        int mtTmp8;\n" +
                 "        int mtTmp13;\n" +
                 "        int mtTmp12;\t\t               \n" +
                 "        boolean var0;\n" +
                 "        int var1;\n" +
                 "        int var2;\n" +
                 "        int var3;\n" +
                 "        mtTmp5 = 0;\n" +
                 "        mtTmp13 = 10;\n" +
                 "        mtTmp10 = mtTmp10 - mtTmp5;\t\n" +
                 "        mtTmp12 = mtTmp12 + mtTmp5;\n" +
                 "        mtTmp4 = num <= mtTmp5;\n" +
                 "        if (mtTmp4) {\n" +
                 "            mtTmp6 = 1;\n" +
                 "            num_aux = mtTmp6;\n" +
                 "        } else {\n" +
                 "            mtTmp9 = this;\n" +
                 "            mtTmp11 = 1;\n" +
                 "            mtTmp10 = num - mtTmp11;\n" +
                 "            mtTmp8 = mtTmp9.ComputeFac(mtTmp10);\n" +
                 "            mtTmp7 = num * mtTmp8;\n" +
                 "            num_aux = mtTmp7;\n" +
                 "        }\n" +
                 "        mtTmp4 = false;\n" +
                 "        var1 = 10;\n" +
                 "        var0 = true;\n" +
                 "        var3 = 99;\n" +
                 "        while(var0) {\n" +
                 "            var2 = var1 * var1;\n" +
                 "            if(mtTmp4) {\n" +
                 "                while(mtTmp4) {\n" +
                 "                    System.out.println(var0);\n" +
                 "                }\n" +
                 "            }\n" +
                 "            else {\n" +
                 "                while(mtTmp4) {\n" +
                 "                    System.out.println(var0);\n" +
                 "                }\n" +
                 "            }\n" +
                 "            while (mtTmp4) {\n" +
                 "                if(mtTmp4) {\n" +
                 "                    System.out.println(var1);\n" +
                 "                    while (mtTmp4) {\n" +
                 "                        mtTmp8 = mtTmp13;\n" +
                 "                        mtTmp10 = 1;\n" +
                 "                        mtTmp7 = mtTmp10;\n" +
                 "                        if (mtTmp4) {\n" +
                 "                        } else {\n" +
                 "                            {\n" +
                 "                                mtTmp8 = mtTmp13;\n" +
                 "                            }\n" +
                 "                            if (mtTmp12) {\n" +
                 "                                mtTmp13 = mtTmp8;\n" +
                 "                            }\n" +
                 "                        }\n" +
                 "                    }\n" +
                 "                }\n" +
                 "            }\n" +
                 "            var1 = var1 * var3;\n" +
                 "\n" +
                 "        }\n" +
                 "        return num_aux;\n" +
                 "    }\n" +
                 "}\n";

         String code23 = "/*1*/\n" +
                 "class TC23 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp3 = 10;\n" +
                 "\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "\n" +
                 "        if (mtTmp1) {\n" +
                 "            mtTmp1 = mtTmp1 + mtTmp1;\n" +
                 "            if (mtTmp1) {\n" +
                 "                mtTmp3 = mtTmp1;\n" +
                 "                if (mtTmp1) {\n" +
                 "                }\n" +
                 "                else{\n" +
                 "                    while (mtTmp3){}\n" +
                 "                    if(mtTmp3){\n" +
                 "                        mtTmp1 = mtTmp3;\n" +
                 "                    }\n" +
                 "                    else{\n" +
                 "\n" +
                 "                    }\n" +
                 "                }\n" +
                 "            }\n" +
                 "        }\n" +
                 "\n" +
                 "        mtTmp1 = mtTmp1 + mtTmp3;\n" +
                 "    }\n" +
                 "}\n";

         String code22 = "/*1*/\n" +
                 "class TC22 {\n" +
                 "  public static void main(String[] args) {\n" +
                 "      TCH22 t1;\n" +
                 "      int x;\n" +
                 "      t1 = new TCH22();\n" +
                 "      x = t1.foo();\n" +
                 "  }\n" +
                 "}\n" +
                 "\n" +
                 "class TCH22 {\n" +
                 "  public int foo() {\n" +
                 "    int t2_1;\n" +
                 "    int t2_2;\n" +
                 "    int t2_3;\n" +
                 "    int t2_4;\n" +
                 "    int t2_5;\n" +
                 "    int t2_6;\n" +
                 "    int t2_7;\n" +
                 "    int t2_8;\n" +
                 "    int t2_9;\n" +
                 "    int t2_10;\n" +
                 "    int t2_11;\n" +
                 "    int t2_12;\n" +
                 "    int t2_13;\n" +
                 "    int t2_14;\n" +
                 "    int t2_15;\n" +
                 "    int t2_16;\n" +
                 "    int t2_17;\n" +
                 "    int t2_18;\n" +
                 "    int t2_19;\n" +
                 "    int t2_20;\n" +
                 "    int t2_21;\n" +
                 "    int t2_22;\n" +
                 "    int t2_23;\n" +
                 "    int t2_24;\n" +
                 "    // OR EXPRESSION\n" +
                 "    t2_1 = 10;\n" +
                 "    t2_2 = 10;\n" +
                 "    t2_3 = 10;\n" +
                 "    t2_1 = t2_2 || t2_3;\n" +
                 "    t2_4 = 10;\n" +
                 "    t2_5 = 10;\n" +
                 "    t2_6 = 10;\n" +
                 "    t2_4 = t2_5 && t2_6;\n" +
                 "    t2_7 = 10;\n" +
                 "    t2_8 = 10;\n" +
                 "    t2_9 = 10;\n" +
                 "    t2_7 = t2_8 <= t2_9;\n" +
                 "    t2_10 = 10;\n" +
                 "    t2_11 = 10;\n" +
                 "    t2_12 = 10;\n" +
                 "    t2_10 = t2_11 != t2_12;\n" +
                 "    // PLUS EXPRESSION\n" +
                 "    t2_13 = 10;\n" +
                 "    t2_14 = 10;\n" +
                 "    t2_15 = 10;\n" +
                 "    t2_13 = t2_14 + t2_15;\n" +
                 "    // MINUS EXPRESSION\n" +
                 "    t2_16 = 10;\n" +
                 "    t2_17 = 10;\n" +
                 "    t2_18 = 10;\n" +
                 "    t2_16 = t2_17 - t2_18;\n" +
                 "    t2_19 = 10;\n" +
                 "    t2_20 = 10;\n" +
                 "    t2_21 = 10;\n" +
                 "    t2_19 = t2_20 * t2_21;\n" +
                 "    // DIVIDE EXPRESSION\n" +
                 "    t2_22 = 10;\n" +
                 "    t2_23 = 10;\n" +
                 "    t2_24 = 10;\n" +
                 "    t2_22 = t2_23 / t2_24;\n" +
                 "    return t2_1;\n" +
                 " }\n" +
                 "}\n";

         Node root = new FriendTJ(new ByteArrayInputStream(code22.getBytes())).Goal();
         CFGGen cfgGen = new CFGGen();
         root.accept(cfgGen);

         ProgramCFG programCFG = cfgGen.getCFG();

         RunAnalysis ra = new RunAnalysis(programCFG);
         ra.startAnalysisBackward();

         // Assignment Starts here
         // Result Map contains a mapping from statements to live variables at that statement
         HashMap<Node, Set<String>> resultMap = ra.getResultMap();

         /**
          * Visitor to Construct SymbolTable and perform Graph Coloring Algorithm for Register Allocation
          */
         GraphColoring _GraphColoring = new GraphColoring(resultMap);
         root.accept(_GraphColoring);
         SymbolTable _SymbolTable = _GraphColoring.getSymbolTable();

         /**
          * Visitor to generate the code with register allocation
          */
         GJNoArguDepthFirst _GJNoArguDepthFirst = new GJNoArguDepthFirst(_SymbolTable);
         root.accept(_GJNoArguDepthFirst);
         StringBuilder _outputCode = _GJNoArguDepthFirst.getOutputCode();

         System.out.println(_outputCode.toString());
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
