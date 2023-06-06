package visitor;

import in.ac.iitmandi.compl.datastructures.CFGNode;
import in.ac.iitmandi.compl.datastructures.NODETYPE;
import in.ac.iitmandi.compl.utils.CommonUtils;
import syntaxtree.Node;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import static in.ac.iitmandi.compl.utils.CommonUtils.*;

public class LiveAnalysis {
    TreeSet<String> inSet;
    TreeSet<String> outSet;

    public LiveAnalysis(){
        this.inSet = new TreeSet<>();
        this.outSet = new TreeSet<>();
    }

    public void clearSets(){
        this.inSet.clear();
        this.outSet.clear();
    }

//    public void traverseCFG(CFGNode startNode, List<CFGNode> traversedNodes, LinkedHashMap<String, List<CFGNode>> nodeMap){
//        if(null != startNode){
//            if(startNode.getType() == NODETYPE.STARTNODE){
//                if(isNotNull(startNode.getSuccessorNodes())){
//                    for(CFGNode successorNode : startNode.getSuccessorNodes()){
//
//                        traversedNodes.add(startNode);
//                        traverseIntermediateCFGNodes(startNode.getSuccessorNodes(), traversedNodes);
//                    }
//                } else {
//                    System.out.println("Start Node not found.");
//                }
//            }
//        }
//    }
//
//    public void traverseIntermediateCFGNodes(List<CFGNode> nodeList, List<CFGNode> traversedNodes){
//        if(isNotNull(nodeList)){
//            for(CFGNode node : nodeList){
//                if(node.getType() != NODETYPE.ENDNODE && (isNotNull(traversedNodes) && !traversedNodes.contains(node))){
//                    traversedNodes.add(node);
//                    if(isNotNull(node.getSuccessorNodes())){
//                        for(CFGNode successorNode : node.getSuccessorNodes()){
//                            System.out.println("Here:");
//                            System.out.println("Node :"+successorNode.getNode().toString()+" --> ");
//                            if(successorNode.getType() != NODETYPE.ENDNODE){
//                                String nodeString = getNodeString(successorNode);
//                                System.out.print("Node :"+successorNode.getNode().toString()+" -- ");
//                                traverseIntermediateCFGNodes(successorNode.getSuccessorNodes(), traversedNodes);
//                            }
//
////                            if(node.getType() == NODETYPE.QUERYNODE){
////                                System.out.println("Query Found");
////                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public String getNodeString(CFGNode _node){
//
//    }
}


