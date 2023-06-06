package visitor;

import java.util.LinkedHashMap;

public class SymbolTable {
    LinkedHashMap<String, ClassInfo> table;

    public SymbolTable(){
        table = new LinkedHashMap<String, ClassInfo>();
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
    public void MethodDeclaration(String className, String methodName, String methodType){
        if(!table.get(className)._MethodTable.containsKey(methodName)){
            table.get(className)._MethodTable.put(methodName, new MethodInfo(methodName, methodType));
        } else {
            System.out.println("Error: Multiple " + methodName + " declared in Class " + className + ".");
        }
    }

    /**
     * Adds arguments of the method in Symbol Table
     */
    public void AddMethodArgs(String className, String methodName, String argType, String argName, Boolean isArgs){
        if(table.containsKey(className) && table.get(className)._MethodTable.containsKey(methodName)){
            table.get(className)._MethodTable.get(methodName)
                    ._VariableTable.put(argName, new VariableInfo(argType, argName, isArgs));
        } else {
            System.out.println("Error: Class-> " + className + " or Method-> " + methodName + " is not declared.");
        }
    }

    /**
     * Adds variables of the methods in Symbol Table
     */
    public void AddMethodVariables(String className, String methodName, String varType, String varName, Boolean isArgs){
        if(table.containsKey(className) && table.get(className)._MethodTable.containsKey(methodName)){
            if(table.get(className)._MethodTable.get(methodName)._VariableTable.containsKey(varName)){
                System.out.println("Error: Multiple declaration of variable " + varName + ".");
            } else {
                table.get(className)._MethodTable.get(methodName)
                        ._VariableTable.put(varName, new VariableInfo(varType, varName, isArgs));
            }
        } else {
            System.out.println("Error: Class-> " + className + " or Method-> " + methodName + " is not declared.");
        }
    }

    /**
     * Adds fields declared in the class in Symbol Table
     */
    public void AddClassFields(String className, String fieldType, String fieldName){
        if(table.containsKey(className)){
            if(table.containsKey(fieldName)){
                System.out.println("Error: Multiple declaration of field " + fieldName + ".");
            } else {
                table.get(className)._FieldTable.put(fieldName, fieldType);
            }
        } else {
            System.out.println("Error: Class-> " + className + " is not declared");
        }
    }

    /**
     * Determines the return type of the method
     */
    public Boolean IsMethodReturnTypeSame(String className, String methodName, String variableName){
        if(table.containsKey(className) && table.get(className)._MethodTable.containsKey(methodName)
                && (table.get(className)._FieldTable.containsKey(variableName) || table.get(className)._MethodTable.get(methodName)._VariableTable.containsKey(variableName))){
            return true;
        } else {
            System.out.println("Error: Return type mismatch for method: " + methodName + ".");
            return false;
        }
    }

    /**
     * Returns the mapping of the variable
     */
    public String getRHSVariableMapping(String className, String methodName, String variableName){
        if(table.containsKey(className) && table.get(className)._MethodTable.containsKey(methodName)
                && table.get(className)._MethodTable.get(methodName)._VariableTable.containsKey(variableName)){
            if(table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._isArgs){
                return variableName;
            } else if(table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._isSpilled){
                String variableType = table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._variableType;
                String variableIndex = table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._mapping;
                return "((" + variableType + ") load(" + variableIndex + "))";
            } else {
                String variableType = table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._variableType;
                String variableMapping = table.get(className)._MethodTable.get(methodName)._VariableTable.get(variableName)._mapping;
                return "((" + variableType + ") " + variableMapping + ")";
            }
        }
        return null;
    }

    public String getLHSVariableMapping(String className, String methodName, String LHS, String RHS){
        if(table.containsKey(className) && table.get(className)._MethodTable.containsKey(methodName)
                && table.get(className)._MethodTable.get(methodName)._VariableTable.containsKey(LHS)){
            if(table.get(className)._MethodTable.get(methodName)._VariableTable.get(LHS)._isArgs){
                return LHS + " = " + RHS;
            } else if(table.get(className)._MethodTable.get(methodName)._VariableTable.get(LHS)._isSpilled){
                String variableIndex = table.get(className)._MethodTable.get(methodName)._VariableTable.get(LHS)._mapping;
                return "store(" + variableIndex + ", " + RHS + ")";
            } else {
                String variableMapping = table.get(className)._MethodTable.get(methodName)._VariableTable.get(LHS)._mapping;
                return variableMapping + " = " + RHS;
            }
        }
        return null;
    }

    public Integer getSpillCount(String className, String methodName){
        return table.get(className)._MethodTable.get(methodName)._spillCount;
    }

    public Boolean containsVariable(String className, String methodName, String variableName){
        return (table.get(className)._MethodTable.get(methodName)._VariableTable.containsKey(variableName));
    }
}

class ClassInfo{
    String _className;
    String _parentClassName;

    /**
     * Key -> FieldName
     * Value -> FieldType
     */
    LinkedHashMap<String, String> _FieldTable;

    /**
     * Key -> MethodName
     * Value -> MethodInfo
     */
    LinkedHashMap<String, MethodInfo> _MethodTable;

    public ClassInfo(String _className, String _parentClassName){
        this._className = _className;
        this._parentClassName = _parentClassName;
        this._FieldTable = new LinkedHashMap<>();
        this._MethodTable = new LinkedHashMap<String, MethodInfo>();
    }
}

class MethodInfo{
    String _methodName;
    String _methodType;
    /**
     * Stores the number of spill for the method
     */
    int _spillCount;
    /**
     * Key -> Variable Name
     * Value -> Variable Info
     */
    LinkedHashMap<String, VariableInfo> _VariableTable;

    public MethodInfo(String _methodName, String _methodType){
        this._methodName = _methodName;
        this._methodType = _methodType;
        this._VariableTable = new LinkedHashMap<String, VariableInfo>();
    }
}

class VariableInfo{
    String _variableName;
    String _variableType;
    /**
     * whether the variable is argument
    */
    boolean _isArgs;
    /**
     * whether the variable is spilled onto memory
    */
    boolean _isSpilled;
    /**
     * Mapping of the variable
    */
    String _mapping;

    public VariableInfo(String _variableType, String _variableName, Boolean _isArgs){
        this._variableType = _variableType;
        this._variableName = _variableName;
        this._isArgs = _isArgs;
    }

}
