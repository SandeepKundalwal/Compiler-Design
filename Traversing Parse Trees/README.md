# CS502 Compiler Design - Assignment 1 : How you doin'?
### Objective : Use javaCC and JTB to extend a programming langauge and traverse over parse trees.

### Detailed Specification
A grammar `JT.jj` is provided which models a Java-like Object-Oriented Programming Language, consisting of classes, objects, integer variables and arrays, while loops, etc. Your task is to
expand the `.jj` file to include few more language features, as listed below.
- Types: A double precision floating-point type: double
- Algebraic operations: Addition (+) and Multiplication (*)
- Logical operations: AND (&&) and NEQ (!=)
- Object-oriented: inheritance (extends)
- Array-related: array-lookup (Identifier[Identifier]), array-length (Identifier.length)

Once you have completed the language `JTLang`, you need to generate a parser, and write a visitor to
print the following: For each class, for each method, print (in lexicographic order):
- Classname : ParentClassname
- Classname.methodname
- No. of parameters
- No. of local variables excluding parameters
- No. of binary operations done

### Public Testcase
```
class Test {
  public static void main(String[] args) { //no of params = 1
    System.out.println(new A().foo(10));
  }
}
class A {
  public int foo(int p) { //no of params = 1
    int x; //local var1
    double y; //local var2
    boolean b; //local var3
    int r; //local var4
    x = 10;
    y = 100.25;
    b = true;
    while (b) {
      y = y + x; //binop1
      if (y != 200) { //binop2
        b = false;
      }
    }
    r = this.bar();
    return r;
  }
  
  public int bar() { //no of params = 0
    int a; //local var1
    int b; //local var2
    int c; //local var3
    int d; //local var4
    a = 10;
    b = 20;
    c = a + b; //binop1
    d = a - b; //binop2
    if(d != (a * c)){ //binop3 //binop4
      a = c * d; //binop5
    }
    return a;
  }
}

class B extends A {}
```

*Output:*
```
Class A :
Method A.bar 0 4 5
Method A.foo 1 4 2
Class B : A
Class Test :
Method Test.main 1 0 0
```




