package visitor;

import java.util.*;


public class SymbolTable {
    Helper helper = new Helper();
    LinkedHashMap<String, ClassTable> table;

    public SymbolTable(){
        table = new LinkedHashMap<>();
    }

    public void classDeclaration(String className, String parentClass){
        if(!table.containsKey(className)){
            ClassTable classTable = new ClassTable(className, parentClass);
            table.put(className, classTable);
        } else {
            System.out.println("Class " + className + " has already been declared.");
        }
    }

    public void methodDeclaration(String className, String methodName, String type){
        if(!table.get(className).classMethods.containsKey(methodName)){
            MethodTable methodTable = new MethodTable(methodName, type);
            table.get(className).classMethods.put(methodName, methodTable);
        } else {
            System.out.println("Method " + methodName + " has already been declared in class " + className);
        }
    }

    public int getTotalSizeofClassFields(String className){
        int totalSize = 0;
        while(className != null){
            for(Map.Entry<String, String> classField : table.get(className).classFields.entrySet()){
                totalSize += 4;
            }
            className = table.get(className).parentClassName;
        }
        return totalSize;
    }

    public LinkedHashMap<String, String> getAllocMethodTableWithOverriding(Stack<String> allInheritedClassesIncludingThis){
        LinkedHashMap<String, String> vTable = new LinkedHashMap<>();       //key -> methodname ; value -> newMethodName
        while (!allInheritedClassesIncludingThis.isEmpty()) {
            String className = allInheritedClassesIncludingThis.pop();
            for(Map.Entry<String, MethodTable> classMethods : table.get(className).classMethods.entrySet()){
                if(!vTable.containsKey(classMethods.getValue().methodName)){
                    vTable.put(classMethods.getValue().methodName, className + "_" + classMethods.getValue().methodName);
                } else {
                    vTable.replace(classMethods.getValue().methodName, className + "_" + classMethods.getValue().methodName);
                }
            }
        }
        return vTable;
    }

    public String getDeclarationClassOfField(String className, String variableName){
        if(className != null && !helper.isStringInt(variableName) && table.get(className).classFields.containsKey(variableName)){
            return className;
        } else if (className != null){
            className = table.get(className).parentClassName;
            return getDeclarationClassOfField(className, variableName);
        }
        return null;
    }

    public String getDeclarationClassOfMethod(String className, String methodName){
        if(className != null && table.get(className).classMethods.containsKey(methodName)){
            return className;
        } else {
            className = table.get(className).parentClassName;
            return getDeclarationClassOfMethod(className, methodName);
        }
    }

    /**
     * Still have to HANDLE the "class" return type
     * @param className
     * @param methodName
     * @param variable
     * @return
     */
    public String getType(String className, String methodName, String variable){
        if(className != null && methodName != null && !helper.isStringInt(variable) && variable != "incompatibleType" && table.get(className).classMethods.get(methodName).methodVars.containsKey(variable)){
            return table.get(className).classMethods.get(methodName).methodVars.get(variable);
        } else if(className != null && methodName != null && !helper.isStringInt(variable) && variable != "incompatibleType" && getDeclarationClassOfField(className, variable) != null){
            className = getDeclarationClassOfField(className, variable);
            return table.get(className).classFields.get(variable);
        } else if(variable.equals("int") || variable.equals("boolean") || variable.equals("float") || variable.equals("incompatibleType")){
            return variable;
        } else if(helper.isStringInt(variable)){
            return "int";
        } else if(isClass(variable)){
            return variable;
        } else if(variable.equals("false") || variable.equals("true")){
            return "boolean";
        } else if(helper.isStringFloat(variable)){
            return "float";
        }
        return null;
    }

    public boolean isClass(String expr){
        return expr != null ? table.containsKey(expr) : false;
    }

}


class ClassTable{
    String className;
    String parentClassName;
    LinkedHashMap<String, String> classFields;
    LinkedHashMap<String, MethodTable> classMethods;

    public ClassTable(String className, String parentClassName){
        this.className = className;
        this.parentClassName = parentClassName;
        classFields = new LinkedHashMap<>();
        classMethods = new LinkedHashMap<>();
    }
}

class MethodTable{
    String methodName;
    String methodType;
    List<String> args;                          //to store the parameters of the method
    LinkedHashMap<String, String> methodVars;   //to store all the variables of a method with their types

    public MethodTable(String methodName, String methodType){
        this.methodName = methodName;
        this.methodType = methodType;
        args = new ArrayList<>();
        methodVars = new LinkedHashMap<>();
    }
}
