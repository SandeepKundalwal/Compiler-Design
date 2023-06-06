package visitor;

import syntaxtree.*;

public class Validator extends GJNoArguDepthFirst<Integer> {
    int registerLimit = 0;
    int allocatedRegisters = 0;
    public int totalSpills = 0;
    /**
    * f0 -> <REGLIMIT>
    * f1 -> <TOP_IMPORT>
    * f2 -> MainClass()
    * f3 -> ( TypeDeclaration() )*
    * f4 -> <EOF>
    */
    public Integer visit(Goal n) {
        registerLimit = Integer.parseInt(n.f0.tokenImage.substring(2, n.f0.tokenImage.length() - 2));
        Integer _ret=null;
        n.f0.accept(this);
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        n.f4.accept(this);
        return _ret;
    }

    /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> ( VarDeclaration() )*
    * f15 -> <ALLOCA>
    * f16 -> ( Statement() )*
    * f17 -> "}"
    * f18 -> "}"
    */
    public Integer visit(MainClass n) {
        allocatedRegisters = 0;
        n.f14.accept(this);
        
        if (allocatedRegisters > registerLimit) {
            System.err.println("Allocated more registers than limit!");
        }

        totalSpills += Integer.parseInt(n.f15.tokenImage.substring(7, n.f15.tokenImage.length() - 2));
        
        Integer _ret=null;
        return _ret;
    }

    /**
    * f0 -> <REGISTER_OBJECT>
    * f1 -> Identifier()
    * f2 -> ";"
    */
    public Integer visit(VarDeclaration n) {
        Integer _ret=null;
        allocatedRegisters++;
        return _ret;
    }

    /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> <ALLOCA>
    * f9 -> ( Statement() )*
    * f10 -> "return"
    * f11 -> ( Identifier() | RegisterLoad() | MemoryLoad() )
    * f12 -> ";"
    * f13 -> "}"
    */
    public Integer visit(MethodDeclaration n) {
        allocatedRegisters = 0;
        n.f7.accept(this);
        
        if (allocatedRegisters > registerLimit) {
            System.err.println("Allocated more registers than limit!");
        }

        totalSpills += Integer.parseInt(n.f8.tokenImage.substring(7, n.f8.tokenImage.length() - 2));
               
        Integer _ret=null;  
        return _ret;
    }
    
}
