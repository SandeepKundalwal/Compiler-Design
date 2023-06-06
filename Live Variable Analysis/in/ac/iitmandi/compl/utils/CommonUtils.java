/**
 * 
 */
package in.ac.iitmandi.compl.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import in.ac.iitmandi.compl.datastructures.CFGNode;
import in.ac.iitmandi.compl.datastructures.NODETYPE;
import syntaxtree.MethodDeclaration;
import visitor.DotPrintVisitor;
/**
 * @author arjun
 *
 */
public class CommonUtils {
	
	private static final String DOUBLE_QOUTE = "\"";
	private static final String EDGE = " -> ";
	private static final String CLOSE_ATTRIB = ";\n";
	static DotPrintVisitor printVisitor = new DotPrintVisitor();
	static CommonUtils commonUtils = null;
	private Map<MethodDeclaration,CFGNode> methodCFGMap= null;
	
	/**
	 * Private Constructor for singleton implementation
	 */
	private CommonUtils() {
		
	}
	
	public static CommonUtils getInstance() {
		if(null==commonUtils) {
			commonUtils = new CommonUtils();
		}
		return commonUtils;
	}

	public static boolean isNotNull(String str) {
		return !(null==str || "".equalsIgnoreCase(str));
	}
	
	public static boolean isNotNull(StringBuffer str) {
		return !(null==str || "".equalsIgnoreCase(str.toString()));
	}
	
	public static boolean isNotNull(List<?> list) {
		return (null!=list && !list.isEmpty());
	}
	
	public static boolean isNotNull(Map<?, ?> map) {
		return (null!=map && !map.isEmpty());
	}
	
	public static boolean isNotNull(Set<?> set) {
		return (null!=set && !set.isEmpty());
	}
	
	public static boolean isNotNull(Vector<?> vector) {
		return (null!=vector && !vector.isEmpty());
	}
	
	public static CFGNode getMethodCFG(MethodDeclaration declStmt){
		if(isNotNull(getInstance().methodCFGMap) && null!=declStmt
				&& getInstance().methodCFGMap.containsKey(declStmt)) {
			return getInstance().methodCFGMap.get(declStmt);
		}
		return null;
	}
	
	public static void addToMethodCFGMap(MethodDeclaration declStmt,CFGNode cfgStartNode){
		if(null!=declStmt && null!=cfgStartNode) {
			if(null==getInstance().methodCFGMap) {
				getInstance().methodCFGMap = new HashMap<>();
			}
			getInstance().methodCFGMap.put(declStmt,cfgStartNode);
		}
	}
	
	public static void traverseCFG(CFGNode cfgStartNode) {
		if(null!=cfgStartNode) {
			List<CFGNode> traversedNodes = new ArrayList<>();
			if(cfgStartNode.getType()==NODETYPE.STARTNODE) {
				if(CommonUtils.isNotNull(cfgStartNode.getSuccessorNodes())) {
					for(CFGNode succNode : cfgStartNode.getSuccessorNodes()) {
						System.out.println("Start Node : -->"+succNode.getNode().toString());
					}
					traversedNodes.add(cfgStartNode);
					traverseIntermediateCFGNodes(cfgStartNode.getSuccessorNodes(),traversedNodes);
				}else {
					System.out.println("Start Node Not found.");
				}
			}
		}
	}

	private static void traverseIntermediateCFGNodes(List<CFGNode> nodeList, List<CFGNode> traversedNodes) {
		if(CommonUtils.isNotNull(nodeList)) {
			for(CFGNode cfgNode:nodeList) {
				if(cfgNode.getType()!=NODETYPE.ENDNODE && (CommonUtils.isNotNull(traversedNodes) && !traversedNodes.contains(cfgNode))) {
					traversedNodes.add(cfgNode);
					if(CommonUtils.isNotNull(cfgNode.getSuccessorNodes())) {
						for(CFGNode succNode : cfgNode.getSuccessorNodes()) {
							System.out.println();
							System.out.print("Node :"+cfgNode.getNode().toString()+" --> ");
							if(succNode.getType()!=NODETYPE.ENDNODE) {
								System.out.print("Node :"+succNode.getNode().toString()+" -- ");
								traverseIntermediateCFGNodes(cfgNode.getSuccessorNodes(),traversedNodes);
							} else {
								System.out.print("End Node --");
							}
						}
					}
				}else {
					System.out.println();
					System.out.print("End Node");
				}
			}
		}
	}
	
	private static String getNodeString(CFGNode startNode) {
		if(null!=startNode) {
			String nodeString = startNode.hashCode()+" : "+startNode.getNode().accept(printVisitor).toString();
			if(startNode.getType()==NODETYPE.RETURNNODE) {
				nodeString = startNode.hashCode()+" : return "+startNode.getNode().accept(printVisitor).toString()+";";
			}
			if(isNotNull(nodeString) && nodeString.contains("\"")) {
				nodeString = nodeString.replaceAll("\"", "\\\\\\\"");
			}
			return DOUBLE_QOUTE+nodeString+DOUBLE_QOUTE;
		}
		return "";
	}
	
	public static void createDotFile(String methodName, CFGNode startNode) {
		if(isNotNull(methodName)) {
			List<CFGNode> traversedNodes = new ArrayList<>();
			FileUtils.createDotFile(methodName);
			StringBuffer bufferObj = new StringBuffer("digraph G {\n");
			if(null!=startNode && startNode.getType()==NODETYPE.STARTNODE) {
				if(CommonUtils.isNotNull(startNode.getSuccessorNodes())) {
					for(CFGNode succNode : startNode.getSuccessorNodes()) {
						FileUtils.writeToBuffer(bufferObj,DOUBLE_QOUTE+"Start Node"+DOUBLE_QOUTE+EDGE+getNodeString(succNode)+CLOSE_ATTRIB);
					}
					traversedNodes.add(startNode);
					createDotForIntermediateNodes(bufferObj,startNode.getSuccessorNodes(),traversedNodes);
				}
				FileUtils.writeToBuffer(bufferObj,"}");
				FileUtils.writeDotFile(methodName,bufferObj.toString());
			}
		}
	}
	
	private static void createDotForIntermediateNodes(StringBuffer bufferObj, List<CFGNode> nodeList, List<CFGNode> traversedNodes) {
		if(CommonUtils.isNotNull(nodeList)) {
			for(CFGNode cfgNode:nodeList) {
				if(cfgNode.getType()!=NODETYPE.ENDNODE && (CommonUtils.isNotNull(traversedNodes) && !traversedNodes.contains(cfgNode))) {
					traversedNodes.add(cfgNode);
					if(CommonUtils.isNotNull(cfgNode.getSuccessorNodes())) {
						for(CFGNode succNode : cfgNode.getSuccessorNodes()) {
							if(succNode.getType()!=NODETYPE.ENDNODE) {
								FileUtils.writeToBuffer(bufferObj,getNodeString(cfgNode)+EDGE+getNodeString(succNode)+CLOSE_ATTRIB);
								createDotForIntermediateNodes(bufferObj,cfgNode.getSuccessorNodes(),traversedNodes);
							} else {
								FileUtils.writeToBuffer(bufferObj,getNodeString(cfgNode)+EDGE+DOUBLE_QOUTE+"End Node"+DOUBLE_QOUTE+CLOSE_ATTRIB);
							}
						}
					}
				}
			}
		}
	}
}
