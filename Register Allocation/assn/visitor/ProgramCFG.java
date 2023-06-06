package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ProgramCFG {
    public String mainClass;
    public String mainMethod;
    public ArrayList<String> otherClasses;

    public String currentClass;
    public String currentMethod;
    public BB currentBB;

    public HashMap<String, Set<String>> classMethodList; // map from class to the methods

    public HashMap<String, BB> methodBBSet; // map from method to the starting BB
    public HashMap<String, BB> methodBBSetLast; // map from method to the starting BB

    public HashMap<String, MethodMetadata> methodMeta; // This will be used for recreating the method later

    public ProgramCFG() {
        methodBBSet = new HashMap<>();
        methodBBSetLast = new HashMap<>();
        methodMeta = new HashMap<>();

        currentClass = null;
        currentMethod = null;
        currentBB = null;

        mainClass = null;
        otherClasses = new ArrayList<>();

        classMethodList = new HashMap<>();
    }

    public void setMainClass(String className) {
        mainClass = className;
        currentClass = className;
    }

    public void setActiveClass(String className) {
        currentClass = className;
        otherClasses.add(className);
        classMethodList.put(currentClass, new HashSet<>());
    }

    public void setCurrentMethod(String methodName) {
        currentMethod = getMethodName(currentClass, methodName);
        (classMethodList.get(currentClass)).add(currentMethod);
    }

    public void setMainMethod(String methodName) {
        mainMethod = getMethodName(currentClass, methodName);
        currentMethod = mainMethod;
    }

    public void setStartBB(BB bb) {
        methodBBSet.put(currentMethod, bb);
    }

    public void setCurrentBB(BB bb) {
        currentBB = bb;
    }

    public void addInstruction(Instruction i) {
        currentBB.pushInstruction(i);
    }


    public void addEdgeFromTo(BB from, BB to) {
        from.addOutgoingEdge(to);
        to.addIncomingEdge(from);
    }

    public static String getMethodName(String className, String methodName) {
        return className + "_" + methodName;
    }
}