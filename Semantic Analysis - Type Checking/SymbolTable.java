import java.util.*;

public class SymbolTable {
    Helper helper = new Helper();
    LinkedHashMap<String, ClassTable> table;

    public SymbolTable(){
        table = new LinkedHashMap<String, ClassTable>();
    }

    public void classDeclaration(String className, String parentClass){
        if(table.containsKey(className)){
            //System.out.println("Class " + className + " has already been declared.");
        }
        ClassTable classTable = new ClassTable(className, parentClass);
        table.put(className, classTable);
    }

    public void methodDeclaration(String className, String methodName, String type){
        if(table.get(className).methods.containsKey(methodName)){
            System.out.println("Method " + methodName + " has already been declared in class " + className);
        }
        MethodTable methodTable = new MethodTable(methodName, type);
        table.get(className).methods.put(methodName, methodTable);
    }

    public boolean isExtendable(String leftClass, String rightClass){
        String parentClass = null;
        if (!leftClass.equals(rightClass)) {
            if(isExtends(rightClass)){
                parentClass = table.get(rightClass).parentClassName;
                return isExtendable(leftClass, parentClass);
            } else {
                return false;
            }
        }
        return true;
    }

    public String getFinalParentClass(String className){
        if(table.get(className).parentClassName != null){
            className = table.get(className).parentClassName;
            return getFinalParentClass(className);
        }
        return className;
    }

    public String getDeclarationClassOfMethod(String className, String methodName){
        String parentClassName = null;
        if(isExtends(className)){
            parentClassName = table.get(className).parentClassName;
            if(table.get(parentClassName).methods.containsKey(methodName)){
                return parentClassName;
            } else {
                return getDeclarationClassOfMethod(parentClassName, methodName);
            }
        }
        return parentClassName;
    }

    public boolean classMethod(String className, String methodName){
        if(isClass(className) && table.get(className).methods.containsKey(methodName)){
            return true;
        }
        return false;
    }

    public String getType(String className, String methodName, String expr){
        if(className != null && methodName != null && expr != "incompatibleType" && table.get(className).methods.get(methodName).vars.containsKey(expr)){
            return table.get(className).methods.get(methodName).vars.get(expr);
        } else if(expr.equals("int") || expr.equals("boolean") || expr.equals("float") || expr.equals("incompatibleType")){
            return expr;
        } else if(helper.isStringInt(expr)){
            return "int";
        } else if(isClass(expr)){
            return expr;
        } else if(expr.equals("false") || expr.equals("true")){
            return "boolean";
        } else if(helper.isStringFloat(expr)){
            return "float";
        }
        return null;
    }

    public boolean isClass(String expr){
        return expr != null ? table.containsKey(expr) : false;
    }

    public boolean isExtends(String className){
        if(isClass(className)){
            return isClass(table.get(className).parentClassName);
        }
        return false;
    }

    public boolean sameTypes(String left, String right){
        if(left.equals(right)){
            return true;
        } else if(isExtends(right)){
            return sameTypes(left, table.get(right).parentClassName);
        }
        return false;
    }
}


class ClassTable{
    String className;
    String parentClassName;
    LinkedHashMap<String, MethodTable> methods;

    public ClassTable(String className, String parentClassName){
        this.className = className;
        this.parentClassName = parentClassName;
        methods = new LinkedHashMap<>();
    }
}


class MethodTable{
    String methodName;
    String methodType;
    List<String> args;                              //to store the parameters of the method
    LinkedHashMap<String, String> vars;             //to store all the variables of a method with their types

    public MethodTable(String methodName, String methodType){
        this.methodName = methodName;
        this.methodType = methodType;
        args = new ArrayList<>();
        vars = new LinkedHashMap<>();
    }
}
