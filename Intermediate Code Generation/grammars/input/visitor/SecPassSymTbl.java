package visitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class SecPassSymTbl {
    LinkedHashMap<String, ClassInfo> table;

    public SecPassSymTbl(){
        table = new LinkedHashMap<String, ClassInfo>();
    }


    public Stack<String> getAllParents(String className){
        Stack<String> allParentsofClass = new Stack<>();
        while(className != null){
            allParentsofClass.push(className);
            className = table.get(className).parentClassName;
        }
        return allParentsofClass;
    }



    public String getDeclarationClassOfVariable(String className, String methodName, String variableName){
        if(className != "null" && methodName != "" && table.get(className).methodMapping.get(methodName).variableMappings.containsKey(variableName)){
            return className;
        } else if(className != "" && table.get(className).classMapping.containsKey(variableName)){
            return className;
        } else {
            className = table.get(className).parentClassName;
            return getDeclarationClassOfVariable(className, "", variableName);
        }
    }


    /**
     * return "method" -> check
     * @param className
     * @param methodName
     * @param variableName
     * @return
     */
    public String isVariableDeclaredInClassOrMethod(String className, String methodName, String variableName){
        if(className != null && methodName != null && table.get(className).methodMapping.get(methodName).variableMappings.containsKey(variableName)){
            return "method";
        } else if(className != null && methodName != null && table.get(className).classMapping.containsKey(variableName)){
            return className;
        } else if(className != null && table.get(className).parentClassName != null){
            String parentClassName = table.get(className).parentClassName;
            return isVariableDeclaredInClassOrMethod(parentClassName, null, variableName);
        } else {
            System.out.println("Undeclared Variable");
        }
        return "";
    }


    public void classDeclaration(String className, String parentClassName){
        if(!table.containsKey(className)){
            ClassInfo classInfo = new ClassInfo(className, parentClassName);
            table.put(className, classInfo);
        } else {
            System.out.println("Class " + className + " has already been declared.");
        }
    }

    public void methodDeclaration(String className, String methodName, String mappedMethodName){
        if(!table.get(className).methodMapping.containsKey(methodName)){
            MethodInfo methodInfo = new MethodInfo( methodName, mappedMethodName);
            table.get(className).methodMapping.put(methodName, methodInfo);
        } else {
            System.out.println("Method " + methodName + " has already been declared in class " + className);
        }

    }
}

class ClassInfo{
    String className;
    String parentClassName;
    LinkedHashMap<String, SecPassClassFieldInfo> classMapping;  //key -> field name ; value -> field info
    LinkedHashMap<String, MethodInfo> methodMapping;    //key -> method name ; value -> methodinfo

    public ClassInfo(String className, String parentClassName){
        this.className = className;
        this.parentClassName = parentClassName;
        this.classMapping = new LinkedHashMap<>();
        this.methodMapping = new LinkedHashMap<>();
    }
}

class SecPassClassFieldInfo{
    String fieldType;
    String mappedToFieldName;
    boolean isObjectCreated;
    ArrayList<String> linkedFields;
    //LinkedHashMap<String, String> mappedFieldName;    //key -> new field name ; value -> original variable

    public SecPassClassFieldInfo(String fieldType, String mappedToFieldName){
        this.fieldType = fieldType;
        this.mappedToFieldName = mappedToFieldName;
        this.isObjectCreated = false;
        linkedFields = new ArrayList<>();
        //this.mappedFieldName = new LinkedHashMap<>();
    }
}

class MethodInfo{
    String methodName;
    String mappedMethodName;
    LinkedHashMap<String, String> fieldsUsedInRHSThisMethod;   //key -> field name ; value -> class it belongs to
    LinkedHashMap<String, String> fieldsUsedInLHSThisMethod;     //key -> linked method name ; value -> original method name
    LinkedHashMap<String, Mappings> variableMappings;

    public MethodInfo(String methodName, String mappedMethodName){
        this.methodName = methodName;
        this.mappedMethodName = mappedMethodName;
        this.fieldsUsedInRHSThisMethod = new LinkedHashMap<>();
        this.fieldsUsedInLHSThisMethod = new LinkedHashMap<>();
        variableMappings = new LinkedHashMap<>();
    }
}

class Mappings{
    String varType;
    boolean isClassVariable;
    boolean isObjectCreated;
    //LinkedHashMap<String, String> mappedVariableName;       //key -> mapped variable name ; value -> original variable name
    ArrayList<String> linkedVariables;

    public Mappings( String varType, boolean isClassVariable, boolean isObjectCreated){
        this.varType = varType;
        this.isClassVariable = isClassVariable;
        this.isObjectCreated = isObjectCreated;
        linkedVariables = new ArrayList<>();
    }
}
