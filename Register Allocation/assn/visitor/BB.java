package visitor;

import syntaxtree.*;

import java.io.FileWriter;
import java.util.*;

// A basic block contains a list of instructions
// and a list of incoming/outgoing edges to BB's

public class BB {
    public ArrayList<Instruction> instructions;
    public Set<BB> incomingEdges, outgoingEdges;
    static int id = 0;
    public String name;

    public BB() {
        instructions = new ArrayList<>();
        incomingEdges = new HashSet<>();
        outgoingEdges = new HashSet<>();
        name = "bb" + (id++);
        // Add a dummy instruction to start of every block
        instructions.add(new Instruction(null));
    }

    public void pushInstruction(Instruction i) {
        instructions.add(i);
    }

    public void addIncomingEdge(BB incomingEdge) {
        incomingEdges.add(incomingEdge);
    }

    public void addOutgoingEdge(BB outgoingEdge) {
        outgoingEdges.add(outgoingEdge);
    }

    Set<String> getInSet() {
        return instructions.get(0).getInSet();
    }

    Set<String> getOutSet() {
        return instructions.get(instructions.size() - 1).getOutSet();
    }

    public static void printBB(BB startNode) {
        Set<BB> visitedBBs = new HashSet<>();
        Stack<BB> worklist = new Stack<>();

        worklist.push(startNode);

        while (worklist.size() > 0) {
            BB currentBB = worklist.pop();
            visitedBBs.add(currentBB);

            System.out.print(currentBB.name);

            if (currentBB.incomingEdges.size() > 0) {
                System.out.print(" <- ");
            }

            for (BB bbIncoming : currentBB.incomingEdges) {
                System.out.print(bbIncoming.name + " ");
            }

            System.out.println();

            for (Instruction i : currentBB.instructions) {
                if (i.instructionNode == null) {
                    System.out.println("Dummy Inst");
                } else {
                    System.out.println(i.instructionNode);
                }
            }

            for (BB bbs : currentBB.outgoingEdges) {
                if (!visitedBBs.contains(bbs)) {
                    if (!worklist.contains(bbs)) {
                        worklist.push(bbs);
                    }
                }
            }

        }
    }

    public static String getExpressionString(Expression node) {
        switch (node.f0.which) {
            case 0: {
                OrExpression curr = (OrExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " || " + rBinop;
            }
            case 1: {
                AndExpression curr = (AndExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " && " + rBinop;
            }
            case 2: {
                CompareExpression curr = (CompareExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " <= " + rBinop;
            }
            case 3: {
                neqExpression curr = (neqExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " != " + rBinop;
            }
            case 4: {
                PlusExpression curr = (PlusExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " + " + rBinop;
            }
            case 5: {
                MinusExpression curr = (MinusExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " - " + rBinop;
            }
            case 6: {
                TimesExpression curr = (TimesExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " * " + rBinop;
            }
            case 7: {
                DivExpression curr = (DivExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + " / " + rBinop;
            }
            case 8: {
                ArrayLookup curr = (ArrayLookup) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                return  lBinop + "[" + rBinop + "]";
            }
            case 9: {
                ArrayLength curr = (ArrayLength) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                return  lBinop + ".length";
            }
            case 10: {
                MessageSend curr = (MessageSend) node.f0.choice;
                String obj = curr.f0.f0.tokenImage;
                String method = curr.f2.f0.tokenImage;
                StringBuilder sb = new StringBuilder();

                sb.append(obj + "." + method+"( ");

                if (curr.f4.present()) {
                    ArgList n = (ArgList) curr.f4.node;
                    sb.append(n.f0.f0.tokenImage + " ");
                    for (Enumeration<Node> e = n.f1.elements(); e.hasMoreElements(); ) {
                        ArgRest r = (ArgRest) e.nextElement();
                        sb.append(r.f1.f0.tokenImage + " ");
                    }
                }
                sb.append(")");
                return sb.toString();
            }
            case 11: {
                PrimaryExpression curr = (PrimaryExpression) node.f0.choice;
                switch (curr.f0.which) {
                    case 0:
                        return ((IntegerLiteral) curr.f0.choice).f0.tokenImage;
                    case 1:
                        return ((FloatLiteral) curr.f0.choice).f0.tokenImage;
                    case 2:
                        return ((TrueLiteral) curr.f0.choice).f0.tokenImage;
                    case 3:
                        return ((FalseLiteral) curr.f0.choice).f0.tokenImage;
                    case 4:
                        return ((Identifier) curr.f0.choice).f0.tokenImage;
                    case 5:
                        return ((ThisExpression) curr.f0.choice).f0.tokenImage;
                    case 6:
                        return "new int [" + ((ArrayAllocationExpression) curr.f0.choice).f3.f0.tokenImage + "]";
                    case 7:
                        return "new " + ((AllocationExpression) curr.f0.choice).f1.f0.tokenImage + "()";
                    case 8: {
                        return "new " + ((NotExpression) curr.f0.choice).f1.f0.tokenImage + "()";
                    }
                }
                break;
            }
        }
        return  "";
    }


    public static String getTypeString(Type t) {
        switch (t.f0.which) {
            case 0:
                return "int []";
            case 1:
                return "boolean";
            case 2:
                return "int";
            case 3:
                return "float";
            case 4:
                return ((Identifier)t.f0.choice).f0.tokenImage;
        }
        return "";
    }

    

    public static void printBBToStreamAnalysisResult(BB startNode, StringBuilder sb, HashMap<Node, String> resultMap) {
        Set<BB> visitedBBs = new HashSet<>();
        Stack<BB> worklist = new Stack<>();

        worklist.push(startNode);

        while (worklist.size() > 0) {
            BB currentBB = worklist.pop();
            visitedBBs.add(currentBB);

            if (currentBB.outgoingEdges.size() > 0) {
                sb.append( "\n\"" + currentBB.name + "\" -> ");
                int i = 0;
                for (BB bbOut : currentBB.outgoingEdges) {
                    sb.append( "\"" + bbOut.name + "\"");
                    if (++i != currentBB.outgoingEdges.size()) {
                        sb.append(",");
                    }
                }
                sb.append(";\n");
            }
            if (currentBB.outgoingEdges.size() == 0) {
                sb.append("\n" + currentBB.name + " [style=\"rounded,filled\", shape=\"box\", fillcolor=\"orange\", fontname=\"monospace\", xlabel=\"End\", label=\"");
            } else if (currentBB.instructions.size() == 1) {
                sb.append("\n" + currentBB.name + " [fillcolor=\"gray\", style=\"filled\", shape=\"doublecircle\", fontname=\"monospace\", label=\"");
            } else if (currentBB.instructions.size() == 2 && currentBB.instructions.get(1).instructionNode instanceof Identifier) {
                sb.append("\n" + currentBB.name + " [fillcolor=\"gray\", style=\"rounded,filled\", shape=\"diamond\", fontname=\"monospace\", label=\"");
            } else {
                sb.append("\n" + currentBB.name + " [fillcolor=\"white\", style=\"filled\", shape=\"box\", fontname=\"monospace\", xlabel=\"" + currentBB.name + "\", label=\"");
            }

            for (Instruction i : currentBB.instructions) {
                if (i.instructionNode != null) {
                    Node instruction = i.instructionNode;
                    sb.append("[" + resultMap.get(instruction) + "]\n");
                }
            }

            sb.append("\"];");

            for (BB bbs : currentBB.outgoingEdges) {
                if (!visitedBBs.contains(bbs)) {
                    if (!worklist.contains(bbs)) {
                        worklist.push(bbs);
                    }
                }
            }

        }
    }

    public static void printBBToStream(BB startNode, StringBuilder sb) {
        Set<BB> visitedBBs = new HashSet<>();
        Stack<BB> worklist = new Stack<>();

        worklist.push(startNode);

        while (worklist.size() > 0) {
            BB currentBB = worklist.pop();
            visitedBBs.add(currentBB);

            if (currentBB.outgoingEdges.size() > 0) {
                sb.append( "\n\"" + currentBB.name + "\" -> ");
                int i = 0;
                for (BB bbOut : currentBB.outgoingEdges) {
                    sb.append( "\"" + bbOut.name + "\"");
                    if (++i != currentBB.outgoingEdges.size()) {
                        sb.append(",");
                    }
                }
                sb.append(";\n");
            }
            if (currentBB.outgoingEdges.size() == 0) {
                sb.append("\n" + currentBB.name + " [style=\"rounded,filled\", shape=\"box\", fillcolor=\"orange\", fontname=\"monospace\", xlabel=\"End\", label=\"");
            } else if (currentBB.instructions.size() == 1) {
                sb.append("\n" + currentBB.name + " [fillcolor=\"gray\", style=\"filled\", shape=\"doublecircle\", fontname=\"monospace\", label=\"");
            } else if (currentBB.instructions.size() == 2 && currentBB.instructions.get(1).instructionNode instanceof Identifier) {
                sb.append("\n" + currentBB.name + " [fillcolor=\"gray\", style=\"rounded,filled\", shape=\"diamond\", fontname=\"monospace\", label=\"");
            } else {
                sb.append("\n" + currentBB.name + " [fillcolor=\"white\", style=\"filled\", shape=\"box\", fontname=\"monospace\", xlabel=\"" + currentBB.name + "\", label=\"");
            }

            for (Instruction i : currentBB.instructions) {
                if (i.instructionNode != null) {
                    Node instruction = i.instructionNode;
                    if (instruction instanceof VarDeclaration) {
                        VarDeclaration curr = (VarDeclaration) instruction;
                        sb.append(getTypeString(curr.f0) + " " + curr.f1.f0.tokenImage + ";\n");
                    } else if (instruction instanceof AssignmentStatement) {
                        AssignmentStatement curr = (AssignmentStatement) instruction;
                        String lSide = curr.f0.f0.tokenImage;
                        String rSide = getExpressionString(curr.f2);
                        sb.append(lSide + " = " + rSide + ";\n");

                    } else if (instruction instanceof LivenessQueryStatement) {
                        sb.append("/* PRINTLIVEVARIABLES */\n");
                    } else if (instruction instanceof MethodDeclaration) {
                        MethodDeclaration node = (MethodDeclaration) i.instructionNode;
                        sb.append("return " + node.f10.f0.tokenImage + ";\n");
                    } else if (instruction instanceof PrintStatement) {
                        PrintStatement node = (PrintStatement) i.instructionNode;
                        sb.append("System.out.println(" + node.f2.f0.tokenImage + ");\n");
                    } else if(i.instructionNode instanceof ArrayAssignmentStatement) {
                        ArrayAssignmentStatement node = (ArrayAssignmentStatement) i.instructionNode;
                        String lSide = node.f0.f0.tokenImage + "_" + node.f2.f0.tokenImage;
                        String offset = node.f2.f0.tokenImage;
                        String rSide = node.f5.f0.tokenImage;
                        sb.append(lSide + "[" + offset + "] = " + rSide + ";\n");
                    } else if(i.instructionNode instanceof FieldAssignmentStatement) {
                        FieldAssignmentStatement node = (FieldAssignmentStatement) i.instructionNode;
                        String lSide = node.f0.f0.tokenImage;
                        String field = node.f2.f0.tokenImage;
                        String rSide = node.f4.f0.tokenImage;
                        sb.append(lSide + "." + field + " = " + rSide + ";\n");
                    } else if (i.instructionNode instanceof IfthenStatement) {
                        sb.append(((IfthenStatement)i.instructionNode).f2.f0.tokenImage + "\n");
                    } else if (i.instructionNode instanceof IfthenElseStatement) {
                        sb.append(((IfthenElseStatement)i.instructionNode).f2.f0.tokenImage + "\n");
                    } else if (i.instructionNode instanceof WhileStatement) {
                        sb.append(((WhileStatement)i.instructionNode).f2.f0.tokenImage + "\n");
                    } else {
                        throw new Error("Unhandled instruction in Basic Block: " + i.instructionNode);
                    }

                }
            }

            sb.append("\"];");

            for (BB bbs : currentBB.outgoingEdges) {
                if (!visitedBBs.contains(bbs)) {
                    if (!worklist.contains(bbs)) {
                        worklist.push(bbs);
                    }
                }
            }

        }
    }

    // A basic block with no outgoing edges is the end BB
    public static BB getEndBB(BB start) {
        Set<BB> visitedBBs = new HashSet<>();
        Stack<BB> worklist = new Stack<>();

        worklist.push(start);

        while (worklist.size() > 0) {
            BB currentBB = worklist.pop();
            visitedBBs.add(currentBB);

            if (currentBB.outgoingEdges.size() == 0) return currentBB;

            for (BB bbs : currentBB.outgoingEdges) {
                if (!visitedBBs.contains(bbs)) {
                    if (!worklist.contains(bbs)) {
                        worklist.push(bbs);
                    }
                }
            }

        }
        return null;
    }

    public static void printBBDOTAnalysisResult(ProgramCFG programCFG, HashMap<Node, String> resultMap) {
        StringBuilder sbMain = new StringBuilder();
        String mName = programCFG.mainMethod;
        BB mBB = programCFG.methodBBSet.get(mName);
        sbMain.append("digraph {\n");
        sbMain.append("rankdir=TB");
        printBBToStreamAnalysisResult(mBB, sbMain, resultMap);
        sbMain.append("\n}");
        try {
            FileWriter myWriter = new FileWriter(mName + "_Liveness.DOT");
            myWriter.write(sbMain.toString());
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (String className : programCFG.classMethodList.keySet()) {
            Set<String> methodList = programCFG.classMethodList.get(className);
            StringBuilder sb = new StringBuilder();
            for (String methodName : methodList) {
                BB currentMethodBB = programCFG.methodBBSet.get(methodName);
                sb.append("digraph {\n");
                sb.append("rankdir=TB");
                printBBToStreamAnalysisResult(currentMethodBB, sb, resultMap);
                sb.append("\n}");
                try {
                    FileWriter myWriter = new FileWriter(methodName + "_Liveness.DOT");
                    myWriter.write(sb.toString());
                    myWriter.close();
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }


    public static void printBBDOT(ProgramCFG programCFG) {
        StringBuilder sbMain = new StringBuilder();
        String mName = programCFG.mainMethod;
        BB mBB = programCFG.methodBBSet.get(mName);
        sbMain.append("digraph {\n");
        sbMain.append("rankdir=TB");
        printBBToStream(mBB, sbMain);
        sbMain.append("\n}");
        try {
            FileWriter myWriter = new FileWriter(mName + ".DOT");
            myWriter.write(sbMain.toString());
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (String className : programCFG.classMethodList.keySet()) {
            Set<String> methodList = programCFG.classMethodList.get(className);
            StringBuilder sb = new StringBuilder();
            for (String methodName : methodList) {
                BB currentMethodBB = programCFG.methodBBSet.get(methodName);
                sb.append("digraph {\n");
                sb.append("rankdir=TB");
                printBBToStream(currentMethodBB, sb);
                sb.append("\n}");
                try {
                    FileWriter myWriter = new FileWriter(methodName + ".DOT");
                    myWriter.write(sb.toString());
                    myWriter.close();
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }
}
