class ArrayTest {
    public static void main(String[] args) { //no of params = 1
    	 System.out.println(new AType().foo(args.length));
    }
}

class AType {
	public boolean foo(int len) {//no of params = 1
		int [] arr; //local var1
        int i; //local var2
        int x; //local var
        i=0;
        arr= new int[10];
        while(i!=len){ //binop1
            arr[i] = i+100; //binop2
        }
        i=0;
        while(i!=len){ //binop3
            x = arr[i];
            System.out.println(x);
        }
        return true;
	}
}