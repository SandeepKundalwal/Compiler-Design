package visitor;

import syntaxtree.*;

import java.util.*;

public class RunAnalysis {
    ProgramCFG cfg;
    HashMap<Node, Set<String>> resultMap;

    public RunAnalysis(ProgramCFG cfg) {
        this.cfg = cfg;
        resultMap = new HashMap<>();
    }

    public HashMap<Node, Set<String>> getResultMap() {
        return resultMap;
    }

    public void visitBackward(BB startBB) {

        Set<BB> visitedBBs = new HashSet<>();
        Stack<BB> worklist = new Stack<>();

        worklist.push(startBB);

        boolean trigger = false;

        Set<String> prevTemp;

        while (worklist.size() > 0) {
            BB currentBB = worklist.pop();
            visitedBBs.add(currentBB);

            prevTemp = new HashSet<>();

//            System.out.println("Working on: " + currentBB.name);

            for (BB b : currentBB.outgoingEdges) {
//                printSet(b.name + " Outgoing Edges: ", b.getInSet());
                prevTemp.addAll(b.getInSet());
            }

            // Visit instructions in the reverse order in the basic block
            for (int i = currentBB.instructions.size() - 1 ; i >= 0 ; i--) {
                Instruction inst = currentBB.instructions.get(i);

                // Previous analysis result
                Set<String> prevInSet = new HashSet<>(inst.getInSet());
                inst.outSet = prevTemp;

                // New analysis result
                inst.inSet = analyse(inst);
                prevTemp = inst.inSet;

                // If Previous and New result are not the same, then
                // set the trigger, trigger runs the IDFA again.
                // It is rerun, until no changes occur.
                if (!prevInSet.equals(inst.inSet)) {
                    trigger = true;
                }
            }

            for (BB bbs : currentBB.incomingEdges) {
                if (!visitedBBs.contains(bbs)) {
                    if (!worklist.contains(bbs)) {
                        worklist.push(bbs);
                    }
                }
            }

        }

        if (trigger) visitBackward(startBB);
    }

    public void startAnalysisBackward() {
        BB mainMethod = cfg.methodBBSetLast.get(cfg.mainMethod);
        visitBackward(mainMethod);

        for (String className : cfg.classMethodList.keySet()) {
            Set<String> methodList = cfg.classMethodList.get(className);
            for (String methodName : methodList) {
                BB currentMethodBB = cfg.methodBBSetLast.get(methodName);

                visitBackward(currentMethodBB);
            }
        }
    }

    public static void printSet(String prefix, Set<String> set) {
        System.out.print(prefix + " [ ");
        for (String s : set) {
            System.out.print(s + " ");
        }
        System.out.println(" ]");
    }

    void handleExpression(Expression node, Set<String> result) {
        switch (node.f0.which) {
            case 0: {
                OrExpression curr = (OrExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 1: {
                AndExpression curr = (AndExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 2: {
                CompareExpression curr = (CompareExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 3: {
                neqExpression curr = (neqExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 4: {
                PlusExpression curr = (PlusExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 5: {
                MinusExpression curr = (MinusExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 6: {
                TimesExpression curr = (TimesExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 7: {
                DivExpression curr = (DivExpression) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 8: {
                ArrayLookup curr = (ArrayLookup) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                String rBinop = curr.f2.f0.tokenImage;
                result.add(lBinop);
                result.add(rBinop);
                break;
            }
            case 9: {
                ArrayLength curr = (ArrayLength) node.f0.choice;
                String lBinop = curr.f0.f0.tokenImage;
                result.add(lBinop);
                break;
            }
            case 10: {
                MessageSend curr = (MessageSend) node.f0.choice;
                String obj = curr.f0.f0.tokenImage;
                result.add(obj);
                if (curr.f4.present()) {
                    ArgList n = (ArgList) curr.f4.node;
                    result.add(n.f0.f0.tokenImage);
                    for (Enumeration<Node> e = n.f1.elements(); e.hasMoreElements(); ) {
                        ArgRest r = (ArgRest) e.nextElement();
                        result.add(r.f1.f0.tokenImage);
                    }
                }
                break;
            }
            case 11: {
                PrimaryExpression curr = (PrimaryExpression) node.f0.choice;
                switch (curr.f0.which) {
                    // skip case 0, 1, 2, 3, 7
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 7:
                        break;
                    case 4: {
                        Identifier i = (Identifier) curr.f0.choice;
                        result.add(i.f0.tokenImage);
                        break;
                    }
                    case 5: {
//                        result.add("this");
                        break;
                    }
                    case 6: {
                        ArrayAllocationExpression a = (ArrayAllocationExpression) curr.f0.choice;
                        result.add(a.f3.f0.tokenImage);
                        break;
                    }
                    case 8: {
                        NotExpression n = (NotExpression) curr.f0.choice;
                        result.add(n.f1.f0.tokenImage);
                        break;
                    }

                }
                break;
            }
            default:
                System.err.println("Unhandled Expression node: " + node.f0.choice);
        }
    }

    public Set<String> analyse(Instruction i) {
        Set<String> result = new HashSet<>(i.getOutSet());

        boolean validNode = true;

        if (i.instructionNode instanceof MethodDeclaration) { // return statement
            MethodDeclaration node = (MethodDeclaration) i.instructionNode;
            result.add(node.f10.f0.tokenImage);
        } else if (i.instructionNode instanceof PrintStatement) {
            PrintStatement node = (PrintStatement) i.instructionNode;
            result.add(node.f2.f0.tokenImage);
        } else if (i.instructionNode instanceof VarDeclaration) {
            VarDeclaration node = (VarDeclaration) i.instructionNode;
            if (result.contains(node.f1.f0.tokenImage)) {
                result.remove(node.f1.f0.tokenImage);
            }
        } else if (i.instructionNode instanceof AssignmentStatement) {
            AssignmentStatement node = (AssignmentStatement) i.instructionNode;
            String lSide = node.f0.f0.tokenImage;
            if (result.contains(lSide)) {
                result.remove(lSide);
            }
            Expression rSide = node.f2;
            handleExpression(rSide, result);
        } else if(i.instructionNode instanceof ArrayAssignmentStatement) {
            ArrayAssignmentStatement node = (ArrayAssignmentStatement) i.instructionNode;
            String lSide = node.f0.f0.tokenImage;
            String offset = node.f2.f0.tokenImage;
            String rSide = node.f5.f0.tokenImage;

            result.add(lSide);
            result.add(offset);
            result.add(rSide);
        } else if(i.instructionNode instanceof FieldAssignmentStatement) {
            FieldAssignmentStatement node = (FieldAssignmentStatement) i.instructionNode;
            String lSide = node.f0.f0.tokenImage;
            String rSide = node.f4.f0.tokenImage;
            result.add(lSide);
            result.add(rSide);
        } else if (i.instructionNode instanceof IfthenStatement) {
            result.add(((IfthenStatement)i.instructionNode).f2.f0.tokenImage);
        } else if (i.instructionNode instanceof IfthenElseStatement) {
            result.add(((IfthenElseStatement)i.instructionNode).f2.f0.tokenImage);
        } else if (i.instructionNode instanceof WhileStatement) {
            result.add(((WhileStatement)i.instructionNode).f2.f0.tokenImage);
        } else if (i.instructionNode instanceof LivenessQueryStatement) {
            // validNode = true;
        } else { // in case of an empty basic block
            validNode = false;
        }

        if (validNode) {
            // Store the result for each node
            // StringBuilder sb = new StringBuilder();
            // List<String> aList = new ArrayList<String>(result);
            // Collections.sort(aList);
            // int iter = 0;
            // for (String s : aList) {
            //     sb.append(s);
            //     iter++;
            //     if (iter != result.size()) {
            //         sb.append(",");
            //     }
            // }
            // System.out.println("Liveness at node [" + i.instructionNode + "]: " + sb.toString());
            resultMap.put(i.instructionNode, result);
        }



        return result;
    }
}
