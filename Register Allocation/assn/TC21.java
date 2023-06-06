/*1*/
class TC21 {
    public static void main(String[] a) {
        Fac mtTmp2;
        int mtTmp3;
        int mtTmp1;
        mtTmp2 = new Fac();
        mtTmp3 = 10;
        mtTmp1 = mtTmp2.ComputeFac(mtTmp3);
        System.out.println(mtTmp1);
    }
}
class Fac {
    public int ComputeFac(int num) {
        int num_aux;
        boolean mtTmp4;
        int mtTmp5;
        int mtTmp6;
        int mtTmp7;
        Fac mtTmp9;
        int mtTmp10;
        int mtTmp11;
        int mtTmp8;
        mtTmp5 = 0;
        mtTmp4 = num <= mtTmp5;
        if (mtTmp4) {
            mtTmp6 = 1;
            num_aux = mtTmp6;
        } else {
            mtTmp9 = new Fac();
            mtTmp11 = 1;
            mtTmp10 = num - mtTmp11;
            mtTmp8 = mtTmp9.ComputeFac(mtTmp10);
            mtTmp7 = num * mtTmp8;
            num_aux = mtTmp7;
        }
        return num_aux;
    }
}
