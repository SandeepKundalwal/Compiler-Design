package visitor;

import java.util.Map;
import java.util.TreeMap;

public class SymbolTable {
    Map<String, ClassInfo> table;

    public SymbolTable(){
        table = new TreeMap<>();
    }

    /**
     * Creates a new class in Symbol Table
     */
    public void ClassDeclaration(String className, String parentClassName){
        if(!table.containsKey(className)){
            table.put(className, new ClassInfo(className, parentClassName));
        } else {
            System.out.println("Error: Multiple Class " + className + " declared.");
        }
    }

    /**
     * Creates a method table in the Symbol Table for the corresponding class
     */
    public void MethodDeclaration(String className, String methodName){
        if(!table.get(className)._MethodTable.containsKey(methodName)){
            table.get(className)._MethodTable.put(methodName, new MethodInfo(methodName));
        } else {
            System.out.println("Error: Multiple " + methodName + " declared in Class " + className + ".");
        }
    }

    public void incrementParamsCount(String className, String methodName){
        table.get(className)._MethodTable.get(methodName)._countParams++;
    }

    public void incrementVariableCount(String className, String methodName){
        table.get(className)._MethodTable.get(methodName)._countVariables++;
    }

    public void incrementBinopCount(String className, String methodName){
        table.get(className)._MethodTable.get(methodName)._countBinop++;
    }
}

class ClassInfo{
    String _className;
    String _parentClassName;
    /**
     * Key -> MethodName
     * Value -> MethodInfo
     */
    Map<String, MethodInfo> _MethodTable;

    public ClassInfo(String _className, String _parentClassName){
        this._className = _className;
        this._parentClassName = _parentClassName;
        this._MethodTable = new TreeMap<String, MethodInfo>();
    }
}

class MethodInfo{
    String _methodName;
    /**
     * Stores the number of spill for the method
     */
    int _countParams;
    int _countVariables;
    int _countBinop;


    public MethodInfo(String _methodName){
        this._methodName = _methodName;
        this._countParams = 0;
        this._countVariables = 0;
        this._countBinop = 0;
    }
}
