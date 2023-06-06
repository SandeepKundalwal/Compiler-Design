
import java.io.ByteArrayInputStream;

import syntaxtree.*;
import visitor.*;

public class Main {
   public static void main(String [] args) {
      try {

         /**
          * Test Case 25 ---> PASSED
          * OUTPUT : p,q,refA
          * OUTPUT : p1
          * OUTPUT : p2
          */
         String code25 = "class TC25 {\n" +
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
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        x = refA.bar(p,q); \n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "\n" +
                 "    public int bar(int p1, int p2) {\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        p2 = p1;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        return p2;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 24 ---> PASSED
          * OUTPUT : mtTmp12,mtTmp13,mtTmp4,num_aux,var0,var1,var3
          */
         String code24 = "class TC24 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "        Fac mtTmp2;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new Fac();\n" +
                 "        mtTmp3 = 10;\n" +
                 "        /* PRINTLIVEVARIABLES */         \n" +
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
                 "            /* PRINTLIVEVARIABLES */     \n" +
                 "        }\n" +
                 "        return num_aux;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * TestCase 23 ---> FAILED CAUSE OF THE WAY I TRAVERSE THE CFG
          * NEED TO WORK ON IT
          *
          */
         String code23 = "class TC23 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "    }\n" +
                 "}\n" +
                 "class TC23_1 {\n" +
                 "    public int foo(int a, int b, int c) {\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp3 = 10;\n" +
                 "\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "\n" +
                 "        if (mtTmp1) {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            mtTmp1 = mtTmp1 + mtTmp1;\n" +
                 "            if (mtTmp1) {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                mtTmp3 = mtTmp1;\n" +
                 "                if (mtTmp1) {\n" +
                 "                    /* PRINTLIVEVARIABLES */\n" +
                 "                }\n" +
                 "                else{\n" +
                 "                    while (mtTmp3){}\n" +
                 "                    if(mtTmp3){\n" +
                 "                        /* PRINTLIVEVARIABLES */\n" +
                 "                        mtTmp1 = mtTmp3;\n" +
                 "                    }\n" +
                 "                    else{\n" +
                 "\n" +
                 "                    }\n" +
                 "                }\n" +
                 "            }\n" +
                 "        }\n" +
                 "        mtTmp1 = mtTmp1 + mtTmp3;\n" +
                 "        return mtTmp1;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * TestCase 22 ---> PASSED
          * OUTPUT : t2_2,t2_3
          * OUTPUT : t2_1,t2_5,t2_6
          * OUTPUT : t2_1,t2_8,t2_9
          * OUTPUT : t2_1,t2_11,t2_12
          * OUTPUT : t2_1,t2_14,t2_15
          * OUTPUT : t2_1,t2_17,t2_18
          * OUTPUT : t2_1,t2_20,t2_21
          * OUTPUT : t2_1,t2_23,t2_24
          * OUTPUT : t2_1
          */
         String code22 = "class TC22 {\n" +
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
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_1 = t2_2 || t2_3;\n" +
                 "    // AND EXPRESSIO\n" +
                 "    t2_4 = 10;\n" +
                 "    t2_5 = 10;\n" +
                 "    t2_6 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_4 = t2_5 && t2_6;\n" +
                 "    // COMPARE EXPRESSION\n" +
                 "    t2_7 = 10;\n" +
                 "    t2_8 = 10;\n" +
                 "    t2_9 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_7 = t2_8 <= t2_9;\n" +
                 "    // NEQ EXPRESSION\n" +
                 "    t2_10 = 10;\n" +
                 "    t2_11 = 10;\n" +
                 "    t2_12 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_10 = t2_11 != t2_12;\n" +
                 "    // PLUS EXPRESSION\n" +
                 "    t2_13 = 10;\n" +
                 "    t2_14 = 10;\n" +
                 "    t2_15 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_13 = t2_14 + t2_15;\n" +
                 "    // MINUS EXPRESSION\n" +
                 "    t2_16 = 10;\n" +
                 "    t2_17 = 10;\n" +
                 "    t2_18 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */  \n" +
                 "    t2_16 = t2_17 - t2_18;\n" +
                 "    // MULTIPLY EXPRESSION\n" +
                 "    t2_19 = 10;\n" +
                 "    t2_20 = 10;\n" +
                 "    t2_21 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */ \n" +
                 "    t2_19 = t2_20 * t2_21;\n" +
                 "    // DIVIDE EXPRESSION\n" +
                 "    t2_22 = 10;\n" +
                 "    t2_23 = 10;\n" +
                 "    t2_24 = 10;\n" +
                 "    /* PRINTLIVEVARIABLES */\n" +
                 "    t2_22 = t2_23 / t2_24;\n" +
                 "    /* PRINTLIVEVARIABLES */\n" +
                 "    return t2_1;\n" +
                 " }\n" +
                 "}\n";

         /**
          * Test Case 21 ---> Passed
          * OUTPUT : mtTmp5,num,this
          * OUTPUT : num_aux
          * OUTPUT : num_aux
          * OUTPUT : num_aux
          */
         String code21 = "class TC21 {\n" +
                 "    public static void main(String[] a) {\n" +
                 "        Fac mtTmp2;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new Fac();\n" +
                 "        mtTmp3 = 10;\n" +
                 "        /* PRINTLIVEVARIABLES */                     // OUTPUT : mtTmp2,mtTmp3\n" +
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
                 "        int mtTmp12;\t\t                     // Needs to be removed in A5\n" +
                 "        mtTmp5 = 0;\n" +
                 "        mtTmp13 = 10;\n" +
                 "        mtTmp10 = mtTmp13 - mtTmp5;\t\t     // Needs to be removed in A5\n" +
                 "        mtTmp12 = mtTmp13 + mtTmp5;\t\t     // Needs to be removed in A5\n" +
                 "        /* PRINTLIVEVARIABLES */                     // OUTPUT : mtTmp5,num\n" +
                 "        mtTmp4 = num <= mtTmp5;\n" +
                 "        if (mtTmp4) {\n" +
                 "            mtTmp6 = 1;\n" +
                 "            num_aux = mtTmp6;\n" +
                 "            /* PRINTLIVEVARIABLES */                 // OUTPUT : num_aux\n" +
                 "        } else {\n" +
                 "            mtTmp9 = this;\n" +
                 "            mtTmp11 = 1;\n" +
                 "            mtTmp10 = num - mtTmp11;\n" +
                 "            mtTmp8 = mtTmp9.ComputeFac(mtTmp10);\n" +
                 "            mtTmp7 = num * mtTmp8;\n" +
                 "            num_aux = mtTmp7;\n" +
                 "            /* PRINTLIVEVARIABLES */               // OUTPUT : num_aux\n" +
                 "        }\n" +
                 "        /* PRINTLIVEVARIABLES */                   // OUTPUT : num_aux\n" +
                 "        return num_aux;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 20 ---> PASSED
          * OUTPUT : b1,f1,i1
          * OUTPUT : b1,i1
          */
         String code20 ="class TC20 {\n" +
                 "  public static void main(String[] args) {\n" +
                 "      TCH20 t1;\n" +
                 "      int x;\n" +
                 "      t1 = new TCH20();\n" +
                 "      x = t1.foo();\n" +
                 "  }\n" +
                 "}\n" +
                 "\n" +
                 "class TCH20 {\n" +
                 "    public int foo() {\n" +
                 "        int i1;\n" +
                 "        float f1;\n" +
                 "        boolean b1;\n" +
                 "        TC20 t1;\n" +
                 "        i1 = 10;\n" +
                 "        f1 = 20;\n" +
                 "        b1 = false;\n" +
                 "        t1 = new TC20();\n" +
                 "        /* PRINTLIVEVARIABLES */ \n" +
                 "        i1 = f1 + i1;\n" +
                 "        t1 = new TC20();\n" +
                 "        /* PRINTLIVEVARIABLES */ \n" +
                 "        b1 = !b1;\n" +
                 "        return i1;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 19 ---> PASSED
          * MESSAGESEND
          * OUTPUT : tmp1,tmp2
          * OUTPUT : tmp2
          */
         String code19 = "class TC19 {\n" +
                 "  public static void main (String[] args) {\n" +
                 "    TCH19 t1;\n" +
                 "    int x;\n" +
                 "    t1 = new TCH19();\n" +
                 "    x = t1.foo();\n" +
                 "  }\n" +
                 "}\n" +
                 "class TCH19 {\n" +
                 "    int f;\n" +
                 "    public int foo() {\n" +
                 "        TCH19 tmp1;\n" +
                 "        int tmp2;\n" +
                 "        tmp1 = new TCH19();\n" +
                 "        tmp2 =10;\n" +
                 "        /* PRINTLIVEVARIABLES */  \n" +
                 "        tmp1.f = tmp2;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        return tmp2;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n";
         /**
          * Test Case 18 ---> FAILED PARTIALLY
          * OUTPUT : arg1,arg2,arg3,arg4,obj
          */
         String code18 = "class TC18 {\n" +
                 "  public static void main(String[] args) {\n" +
                 "    int arg1;\n" +
                 "    int arg2;\n" +
                 "    int arg3;\n" +
                 "    int arg4;\n" +
                 "    int result;\n" +
                 "    TCH18 obj;\n" +
                 "    obj = new TCH18();\n" +
                 "    arg1 = 1;\n" +
                 "    arg2 = 2;\n" +
                 "    arg3 = 3;\n" +
                 "    arg4 = 4;\n" +
                 "    /* PRINTLIVEVARIABLES */\n" +
                 "    result = obj.callee(arg1, arg2, arg3, arg4);\n" +
                 " }\n" +
                 "}\n" +
                 "class TCH18 {\n" +
                 "  public int foo() {\n" +
                 "    int arg1;\n" +
                 "    int arg2;\n" +
                 "    int arg3;\n" +
                 "    int arg4;\n" +
                 "    int result;\n" +
                 "    TCH18 obj;\n" +
                 "    obj = new TCH18();\n" +
                 "    arg1 = 1;\n" +
                 "    arg2 = 2;\n" +
                 "    arg3 = 3;\n" +
                 "    arg4 = 4;\n" +
                 "    /* PRINTLIVEVARIABLES */\n" +
                 "    result = obj.callee(arg1, arg2, arg3, arg4);\n" +
                 "    return arg1;\n" +
                 "  }\n" +
                 "  public int callee(int a, int b, int c, int d) {\n" +
                 "    return d;\n" +
                 "  }\n" +
                 "}\n" +
                 "\n";

         /**
          * Test Case 17 ---> PASSED
          * ARRAY LOOKUP
          * OUTPUT : arr_1,arr_3
          * OUTPUT : arr_2
          */
         String code17 = "class TC17 {\n" +
                 "  public static void main(String[] args) {\n" +
                 "    TC17 t1;\n" +
                 "    int x;\n" +
                 "    t1 = new TCH17();\n" +
                 "    x = t1.foo();\n" +
                 "  }\n" +
                 "}\n" +
                 "class TCH17 {\n" +
                 "  public int foo() {\n" +
                 "        int[] arr_1;\n" +
                 "        int arr_2;\n" +
                 "        int arr_3;\n" +
                 "        int tmp;\n" +
                 "        tmp =10;\n" +
                 "        arr_1 = new int[tmp];\n" +
                 "        arr_3 = 0;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        arr_2 = arr_1[arr_3];\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        return arr_2;\n" +
                 "  }\n" +
                 "}\n";
         /**
          * Test Case 16 ---> PASSED
          * OUTPUT : mtTmp10,mtTmp23,mtTmp24
          * OUTPUT : mtTmp23,mtTmp24
          * OUTPUT : mtTmp24
          */
         String code16 = "class TC16 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC16 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC16();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC16 {\n" +
                 "    public int start() {\n" +
                 "        int a;\n" +
                 "        int b;\n" +
                 "        int c;\n" +
                 "        int d;\n" +
                 "        int x;\n" +
                 "        int y;\n" +
                 "        int z;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        int mtTmp9;\n" +
                 "        boolean mtTmp10;\n" +
                 "        boolean mtTmp11;\n" +
                 "        int mtTmp12;\n" +
                 "        int mtTmp13;\n" +
                 "        int mtTmp14;\n" +
                 "        int mtTmp15;\n" +
                 "        int mtTmp16;\n" +
                 "        int mtTmp17;\n" +
                 "        boolean mtTmp18;\n" +
                 "        int mtTmp19;\n" +
                 "        int mtTmp20;\n" +
                 "        int mtTmp21;\n" +
                 "        int mtTmp22;\n" +
                 "        int mtTmp23;\n" +
                 "        int mtTmp24;\n" +
                 "        mtTmp3 = 1;\n" +
                 "        a = mtTmp3;\n" +
                 "        mtTmp4 = 2;\n" +
                 "        b = mtTmp4;\n" +
                 "        mtTmp5 = 3;\n" +
                 "        c = mtTmp5;\n" +
                 "        mtTmp6 = 4;\n" +
                 "        d = mtTmp6;\n" +
                 "        mtTmp7 = 10;\n" +
                 "        x = mtTmp7;\n" +
                 "        mtTmp8 = 20;\n" +
                 "        y = mtTmp8;\n" +
                 "        mtTmp9 = 30;\n" +
                 "        z = mtTmp9;\n" +
                 "        mtTmp14 = c + d;\n" +
                 "        mtTmp13 = b * mtTmp14;\n" +
                 "        mtTmp12 = a + mtTmp13;\n" +
                 "        mtTmp17 = z;\n" +
                 "        mtTmp16 = y + mtTmp17;\n" +
                 "        mtTmp15 = x - mtTmp16;\n" +
                 "        mtTmp11 = mtTmp12 <= mtTmp15;\n" +
                 "        mtTmp19 = 2;\n" +
                 "        mtTmp18 = a <= mtTmp19;\n" +
                 "        mtTmp10 = mtTmp11 && mtTmp18;\n" +
                 "        mtTmp23 = 1;\n" +
                 "        mtTmp24 = 0;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        if(mtTmp10) {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            mtTmp20 = mtTmp23;\n" +
                 "            System.out.println(mtTmp20);\n" +
                 "        } else {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            mtTmp21 = mtTmp24;\n" +
                 "            System.out.println(mtTmp21);\n" +
                 "        }\n" +
                 "        mtTmp22 = mtTmp24;\n" +
                 "        return mtTmp22;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 15 ---> PASSED :)
          * INTERESTING TEST CASE
          * OUTPUT : mtTmp6,mtTmp8,obj,x
          * OUTPUT : mtTmp6,mtTmp7,obj
          * OUTPUT : mtTmp6,obj
          * OUTPUT : thisVar,x
          */
         String code15 = "class TC15 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC15 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC15();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC15 {\n" +
                 "    public int start() {\n" +
                 "        int x;\n" +
                 "        LOC2TC15 obj;\n" +
                 "        LOC2TC15 mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        int mtTmp5;\n" +
                 "        mtTmp3 = new LOC2TC15();\n" +
                 "        obj = mtTmp3;\n" +
                 "        mtTmp4 = 10;\n" +
                 "        x = mtTmp4;\n" +
                 "        mtTmp6 = 2;\n" +
                 "        mtTmp8 = 3;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp7 = mtTmp8 + x;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp7 = obj.setB(mtTmp7);\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp5 = obj.m(mtTmp6);\n" +
                 "        return mtTmp5;\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOC2TC15 {\n" +
                 "    int b;\n" +
                 "    public int setB(int x) {\n" +
                 "        LOC2TC15 thisVar;\n" +
                 "        thisVar = this;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        thisVar.b = x;\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "    public int m(int a) {\n" +
                 "        int c;\n" +
                 "        int mtTmp9;\n" +
                 "        int mtTmp10;\n" +
                 "        mtTmp9 = a + b;\n" +
                 "        c = mtTmp9;\n" +
                 "        System.out.println(c);\n" +
                 "        mtTmp10 = 0;\n" +
                 "        return mtTmp10;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 14 ---> PASSED
          * OUTPUT : mtTmp4
          * OUTPUT : mtTmp8
          */
         String code14 = "class TC14 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC14 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC14();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC14 {\n" +
                 "    public int start() {\n" +
                 "        int mtTmp3;\n" +
                 "        LOCTC14 mtTmp5;\n" +
                 "        int[] mtTmp4;\n" +
                 "        int mtTmp6;\n" +
                 "        mtTmp5 = this;\n" +
                 "        mtTmp4 = mtTmp5.foo();\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp3 = mtTmp4.length;\n" +
                 "        System.out.println(mtTmp3);\n" +
                 "        mtTmp6 = 0;\n" +
                 "        return mtTmp6;\n" +
                 "    }\n" +
                 "    public int[] foo() {\n" +
                 "        int mtTmp8;\n" +
                 "        int[] mtTmp7;\n" +
                 "        mtTmp8 = 5;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp7 = new int[mtTmp8];\n" +
                 "        return mtTmp7;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 13 ---> PASSED
          * OUTPUT : mtTmp5
          */
         String code13 = "class TC13 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC13 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC13();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC13 {\n" +
                 "    public int start() {\n" +
                 "        boolean  x;\n" +
                 "        boolean mtTmp3;\n" +
                 "        boolean mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        mtTmp3 = true;\n" +
                 "        x = mtTmp3;\n" +
                 "        mtTmp4 = x;\n" +
                 "        if(mtTmp4) {\n" +
                 "            mtTmp5 = 1;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            System.out.println(mtTmp5);\n" +
                 "        } else {\n" +
                 "            mtTmp6 = 2;\n" +
                 "            System.out.println(mtTmp6);\n" +
                 "        }\n" +
                 "        mtTmp7 = 0;\n" +
                 "        return mtTmp7;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 12 ---> PASSED
          * OUTPUT : mtTmp6,mtTmp7,obj
          */
         String code12 = "class TC12 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC12 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC12();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC12 {\n" +
                 "    public int start() {\n" +
                 "        int x;\n" +
                 "        LOC2TC12 obj;\n" +
                 "        LOC2TC12 mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        int mtTmp5;\n" +
                 "        mtTmp3 = new LOC2TC12();\n" +
                 "        obj = mtTmp3;\n" +
                 "        mtTmp4 = 10;\n" +
                 "        x = mtTmp4;\n" +
                 "        mtTmp6 = 2;\n" +
                 "        mtTmp8 = 3;\n" +
                 "        mtTmp7 = mtTmp8 + x;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp5 = obj.meth1(mtTmp6,mtTmp7);\n" +
                 "        return mtTmp5;\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOC2TC12 {\n" +
                 "    public int meth1(int a, int b) {\n" +
                 "        int c;\n" +
                 "        int mtTmp9;\n" +
                 "        int mtTmp10;\n" +
                 "        mtTmp9 = a + b;\n" +
                 "        c = mtTmp9;\n" +
                 "        System.out.println(c);\n" +
                 "        mtTmp10 = 0;\n" +
                 "        return mtTmp10;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 11 ---> PASSED
          * OUTPUT : mtTmp2
          */
         String code11 = "class TC11 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC11 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC11();\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC11 {\n" +
                 "    public int foo() {\n" +
                 "        LOCTC11 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC11();\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "        return mtTmp1;\n" +
                 "    }\n" +
                 "    public int start() {\n" +
                 "        int a;\n" +
                 "        int b;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        mtTmp3 = 10;\n" +
                 "        a = mtTmp3;\n" +
                 "        mtTmp4 = 20;\n" +
                 "        b = mtTmp4;\n" +
                 "        mtTmp7 = a - b;\n" +
                 "        mtTmp6 = b * mtTmp7;\n" +
                 "        mtTmp5 = a + mtTmp6;\n" +
                 "        System.out.println(mtTmp5);\n" +
                 "        mtTmp8 = 0;\n" +
                 "        return mtTmp8;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 10 ---> PASSED
          * OUTPUT : a
          * OUTPUT : a,mtTmp7
          * OUTPUT : mtTmp6
          * OUTPUT : a,mtTmp4
          */
         String code10 = "class TC10 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC10 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC10();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC10 {\n" +
                 "    public int start() {\n" +
                 "        int a;\n" +
                 "        int mtTmp3;\n" +
                 "        boolean mtTmp4;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        mtTmp3 = 0;\n" +
                 "        a = mtTmp3;\n" +
                 "        mtTmp5 = 5;\n" +
                 "        mtTmp4 = a <= mtTmp5;\n" +
                 "        while(mtTmp4) {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            System.out.println(a);\n" +
                 "            mtTmp7 = 1;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            mtTmp6 = a + mtTmp7;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            a = mtTmp6;\n" +
                 "            mtTmp5 = 5;\n" +
                 "            mtTmp4 = a <= mtTmp5;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "        }\n" +
                 "        mtTmp8 = 0;\n" +
                 "        return mtTmp8;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 9 ---> PASSED
          * OUTPUT : a,b
          */
         String code9 = "class TC09 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC09 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC09();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC09 {\n" +
                 "    public int start() {\n" +
                 "        int a;\n" +
                 "        int b;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        boolean mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        mtTmp3 = 10;\n" +
                 "        a = mtTmp3;\n" +
                 "        mtTmp4 = 20;\n" +
                 "        b = mtTmp4;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp5 = a <= b;\n" +
                 "        if(mtTmp5) {\n" +
                 "            System.out.println(b);\n" +
                 "        } else {\n" +
                 "            System.out.println(a);\n" +
                 "        }\n" +
                 "        mtTmp6 = 0;\n" +
                 "        return mtTmp6;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 8 ---> PASSED
          * OUTPUT : mtTmp4
          * OUTPUT : arr,mtTmp8
          */
         String code8 = "class TC08 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "        LOCTC08 mtTmp2;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new LOCTC08();\n" +
                 "        mtTmp1 = mtTmp2.start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "class LOCTC08 {\n" +
                 "    public int start() {\n" +
                 "        int[] arr;\n" +
                 "        int mtTmp4;\n" +
                 "        int[] mtTmp3;\n" +
                 "        int mtTmp5;\n" +
                 "        int mtTmp6;\n" +
                 "        int mtTmp7;\n" +
                 "        int mtTmp8;\n" +
                 "        mtTmp4 = 5;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp3 = new int[mtTmp4];\n" +
                 "        arr = mtTmp3;\n" +
                 "        mtTmp5 = 2;\n" +
                 "        mtTmp6 = 10;\n" +
                 "        arr[mtTmp5] = mtTmp6;\n" +
                 "        mtTmp8 = 2;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        mtTmp7 = arr[mtTmp8];\n" +
                 "        return mtTmp7;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 7  ---> PASSED
          * OUTPUT : b,d,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT : b,c,o1,o2
          * OUTPUT :
          * OUTPUT : m
          */
         String code7 = "class TC07 {\n" +
                 "    public static void main(String[] a){\n" +
                 "        TC07_1 mtTmp2;\n" +
                 "        int mtTmp3;\n" +
                 "        int mtTmp4;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp2 = new TC07_1();\n" +
                 "        mtTmp1 = mtTmp2.Start();\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class TC07_1 {\n" +
                 "    public int Start() {\n" +
                 "        A o1;\n" +
                 "        A o2;\n" +
                 "        A o3;\n" +
                 "        int m;\n" +
                 "        boolean b;\n" +
                 "        boolean c;\n" +
                 "        boolean d;\n" +
                 "        int n;\n" +
                 "        int r;\n" +
                 "        m = 5;\n" +
                 "        n = 10;\n" +
                 "        r = 20;\n" +
                 "        o1 = new A();\n" +
                 "        o2 = new A();\n" +
                 "        b = m <= n;\n" +
                 "        d = r <= n;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        c = b && d;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        while (c) {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            if(b) {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                o3 = o1;\n" +
                 "            }\n" +
                 "            else {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                o3 = o2;\n" +
                 "            }\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            c = c && b;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "        }\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        m = 20;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        return m;\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class A {\n" +
                 "    int num;\n" +
                 "}\n";

         /**
          * Test Case 6 ---> PASSED
          * OUTPUT : f1,x
          * OUTPUT : x
          * OUTPUT : f1,x
          */
         String code6 = "class TC06 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int a;\n" +
                 "    }\n" +
                 "}\n" +
                 "class TC06_1 {\n" +
                 "    public int Start() {\n" +
                 "        int x;\n" +
                 "        boolean f1;\n" +
                 "        TC06 refA;\n" +
                 "        TC06 refB;\n" +
                 "        x = 10;\n" +
                 "        f1 = true;\n" +
                 "        refA = new TC06();\n" +
                 "        refB = refA;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        while(f1) {\n" +
                 "            refA = new TC06();\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            f1 = false;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "        }\n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 5 ---> PASSED
          * OUTPUT : f1,temp,x
          * OUTPUT : x
          * OUTPUT : temp,x
          * OUTPUT : f1,temp,x
          * OUTPUT : temp,x
          * OUTPUT : x
          */
         String code5 = "class TC05 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int x;\n" +
                 "    }\n" +
                 "}\n" +
                 "class TC05_1 {\n" +
                 "    public int Start() {\n" +
                 "        int x;\n" +
                 "        boolean f1;\n" +
                 "        TC05 refA;\n" +
                 "        TC05 refB;\n" +
                 "        TC05 temp;\n" +
                 "        x = 10;\n" +
                 "        f1 = true;\n" +
                 "        temp = new TC05();\n" +
                 "        refA = new TC05();\n" +
                 "        refB = new TC05();\n" +
                 "\n" +
                 "        if (f1) {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            if(f1) {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                refA = new TC05();\n" +
                 "            }\n" +
                 "            else {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                refB = temp;\n" +
                 "            }\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        else {\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            if(f1) {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                refA = temp;\n" +
                 "            }\n" +
                 "            else {\n" +
                 "                /* PRINTLIVEVARIABLES */\n" +
                 "                refB = new TC05();\n" +
                 "            }\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}";

         /**
          * Test Case 4 ---> PASSED
          * OUTPUT : refA
          * OUTPUT :
          */
         String code4 = "class TC04 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int x;\n" +
                 "        HTC04 refA;\n" +
                 "        refA = new HTC04();\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        x = refA.foo(refA); \n" +
                 "    }\n" +
                 "}\n" +
                 "class HTC04 {\n" +
                 "    public int bar() {\n" +
                 "        int x;\n" +
                 "        HTC04 refA;\n" +
                 "        refA = new HTC04();\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        x = refA.foo(refA);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "    public int foo(HTC04 refA) {\n" +
                 "        int x;\n" +
                 "        HTC04 refB;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        x = 10;\n" +
                 "        refB = new HTC04();\n" +
                 "        refA = new HTC04();\n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 3 ---> PASSED
          * OUTPUT : x
          * OUTPUT : x
          * OUTPUT : x
          */
         String code3 = "class TC03 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int x;\n" +
                 "        boolean f1;\n" +
                 "        TC03 refA;\n" +
                 "        TC03 refB;\n" +
                 "        x = 10;\n" +
                 "    }\n" +
                 "}\n" +
                 "class TC03_1 {\n" +
                 "    public int foo() {\n" +
                 "        int x;\n" +
                 "        boolean f1;\n" +
                 "        TC03 refA;\n" +
                 "        TC03 refB;\n" +
                 "        x = 10;\n" +
                 "        f1 = true;\n" +
                 "        refA = new TC03();\n" +
                 "        refB = refA;\n" +
                 "\n" +
                 "        if(f1) {\n" +
                 "            refB = refA;\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        else {\n" +
                 "            refB = new TC03();\n" +
                 "            /* PRINTLIVEVARIABLES */\n" +
                 "            System.out.println(x);\n" +
                 "        }\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 2 ---> PASSED
          * OUTPUT : x
          */
         String code2 = "class TC02 {\n" +
                 "    public static void main (String [] args) {\n" +
                 "        int x;\n" +
                 "        TC02 refA;\n" +
                 "        TC02 refB;\n" +
                 "        x = 10;\n" +
                 "        refA = new TC02();\n" +
                 "        refB = refA;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        System.out.println(x);\n" +
                 "    }\n" +
                 "}\n" +
                 "class TC02_1 {\n" +
                 "    public int foo(){\n" +
                 "        int x;\n" +
                 "        TC02 refA;\n" +
                 "        TC02 refB;\n" +
                 "        x = 10;\n" +
                 "        refA = new TC02();\n" +
                 "        refB = refA;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        System.out.println(x);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Test Case 1 ---> PASSED
          * OUTPUT : mtTmp1,x
          */
         String code1 = "class TC01 {\n" +
                 "    public static void main(String[] args) {\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class TC01_1 {\n" +
                 "    public int foo() {\n" +
                 "        int x;\n" +
                 "        int mtTmp1;\n" +
                 "        mtTmp1 = 5;\n" +
                 "        /* PRINTLIVEVARIABLES */\n" +
                 "        System.out.println(mtTmp1);\n" +
                 "        return x;\n" +
                 "    }\n" +
                 "}\n";

         /**
          * Initial Test Case ---> PASSED
          * OUTPUT : num,ptTemp2,this
          * OUTPUT : num_a
          * OUTPUT : num_a
          * OUTPUT : num_a
          */
         String code = "class Test1 {\n" +
                 "public static void main(String[] arg) {\n" +
                 "Foobar obj;\n" +
                 "int ret;\n" +
                 "int m_arg;\n" +
                 "arg=10;\n" +
                 "obj=new Foobar();\n" +
                 "ret = obj.Compute(m_arg);\n" +
                 "}\n" +
                 "}\n" +
                 "class Foobar {\n" +
                 "public int Compute(int num) {\n" +
                 "int num_a;\n" +
                 "boolean ptTemp1;\n" +
                 "int ptTemp2;\n" +
                 "int ptTemp3;\n" +
                 "int ptTemp4;\n" +
                 "int ptTemp5;\n" +
                 "Foobar ptTemp6;\n" +
                 "int ptTemp7;\n" +
                 "int ptTemp8;\n" +
                 "int ptTemp9;\n" +
                 "int ptTemp10;\n" +

                 "ptTemp2 = 0;\n" +
                 "ptTemp10 = 10;\n" +
                 "ptTemp7 = ptTemp10 - ptTemp2;\n" +
                 "ptTemp9 = ptTemp10 + ptTemp2;\n" +
                 "/* PRINTLIVEVARIABLES */ // OUTPUT : num,ptTemp2\n" +
                 "ptTemp1 = num <= ptTemp2;\n" +
                 "if (ptTemp1) {\n" +
                 "ptTemp3 = 1;\n" +
                 "num_a = ptTemp3;\n" +
                 "/* PRINTLIVEVARIABLES */ // OUTPUT : num_a\n" +
                 "} else {\n" +
                 "ptTemp6 = this;\n" +
                 "ptTemp8 = 1;\n" +
                 "ptTemp7 = num - ptTemp8;\n" +
                 "ptTemp5 = ptTemp6.Compute(ptTemp7);\n" +
                 "ptTemp4 = num * ptTemp5;\n" +
                 "num_a = ptTemp4;\n" +
                 "/* PRINTLIVEVARIABLES */ // OUTPUT : num_a\n" +
                 "}\n" +
                 "/* PRINTLIVEVARIABLES */ // OUTPUT : num_a\n" +
                 "return num_a; } }";

         String code30 = "class A4T00 {\n" +
                 "    public static void main(String[] arg) {\n" +
                 "    Foobar obj;\n" +
                 "    int ret;\n" +
                 "    int m_arg;\n" +
                 "    m_arg=10;\n" +
                 "    obj=new Foobar();\n" +
                 "    ret = obj.Compute(m_arg);\n" +
                 "    }\n" +
                 "}\n" +
                 "\n" +
                 "class Foobar {\n" +
                 "    public int Compute(int num) {\n" +
                 "    int num_a;\n" +
                 "    boolean ptTemp1;\n" +
                 "    int ptTemp2;\n" +
                 "    int ptTemp3;\n" +
                 "    int ptTemp4;\n" +
                 "    int ptTemp5;\n" +
                 "    Foobar ptTemp6;\n" +
                 "    int ptTemp7;\n" +
                 "    int ptTemp8;\n" +
                 "    int ptTemp9;\n" +
                 "    int ptTemp10;\n" +
                 "    ptTemp2 = 0;\n" +
                 "    ptTemp10 = 10;\n" +
                 "    ptTemp7 = ptTemp10 - ptTemp2;\n" +
                 "    ptTemp9 = ptTemp10 + ptTemp2;\n" +
                 "    /* PRINTLIVEVARIABLES */ // num,ptTemp2,this\n" +
                 "    ptTemp1 = num <= ptTemp2;\n" +
                 "    if (ptTemp1) {\n" +
                 "    ptTemp3 = 1;\n" +
                 "    num_a = ptTemp3;\n" +
                 "    /* PRINTLIVEVARIABLES */ // num_a\n" +
                 "    } else {\n" +
                 "    ptTemp6 = this;\n" +
                 "    ptTemp8 = 1;\n" +
                 "    ptTemp7 = num - ptTemp8;\n" +
                 "    ptTemp5 = ptTemp6.Compute(ptTemp7);\n" +
                 "    ptTemp4 = num * ptTemp5;\n" +
                 "    num_a = ptTemp4;\n" +
                 "    /* PRINTLIVEVARIABLES */ // num_a\n" +
                 "    }\n" +
                 "    /* PRINTLIVEVARIABLES */ // num_a\n" +
                 "    return num_a; \n" +
                 "    } \n" +
                 "}\n";

         Node root = new FriendsTJ(new ByteArrayInputStream(code.getBytes())).Goal();
         CFGVisitor cfgVisitor = new CFGVisitor();
         root.accept(cfgVisitor, null);
//         DemoVisitor demoVisitor = new DemoVisitor(); //This is a demo visitor. You need to construct a similar visitor for this assignment. You may also see CFGVisitor class for more info.
//         root.accept(demoVisitor);
         root.accept(new GJDepthFirst<>(), null);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 
