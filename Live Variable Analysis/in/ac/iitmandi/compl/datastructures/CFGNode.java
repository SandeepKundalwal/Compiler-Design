/**
 * 
 */
package in.ac.iitmandi.compl.datastructures;

import java.util.ArrayList;
import java.util.List;

import syntaxtree.Node;

/**
 * @author arjun
 *
 */
public class CFGNode {

	private int _line;
	private Node node;
	private NODETYPE type;
	private List<CFGNode> predecessorNodes;
	private List<CFGNode> successorNodes;
	
	
	/**
	 * 
	 */
	public CFGNode(NODETYPE type, int _line) {
		this._line = _line;
		this.type=type;
	}

	/**
	 * @param statement constructor
	 */
	public CFGNode(Node node, int _line) {
		this.node = node;
		this._line = _line;
		this.type = NODETYPE.INTERMEDIATENODE;
	}
	
	/**
	 * @param node
	 * @param type
	 */
	public CFGNode(Node node, int _line, NODETYPE type) {
		this._line = _line;
		this.node = node;
		this.type = type;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the predecessorNodes
	 */
	public List<CFGNode> getPredecessorNodes() {
		return predecessorNodes;
	}
//	/**
//	 * @param predecessorNodes the predecessorNodes to set
//	 */
//	private void setPredecessorNodes(List<CFGNode> predecessorNodes) {
//		this.predecessorNodes = predecessorNodes;
//	}
	/**
	 * @return the successorNodes
	 */
	public List<CFGNode> getSuccessorNodes() {
		return successorNodes;
	}
//	/**
//	 * @param successorNodes the successorNodes to set
//	 */
//	private void setSuccessorNodes(List<CFGNode> successorNodes) {
//		this.successorNodes = successorNodes;
//	}
	
	
	
	public void addSuccessorNode(CFGNode cfgNode) {
		if(null!=cfgNode) {
			if(null==this.getSuccessorNodes()) {
				this.successorNodes = new ArrayList<>();
			}
			this.successorNodes.add(cfgNode);
		}
	}
	
	/**
	 * @return the type
	 */
	public NODETYPE getType() {
		return type;
	}

	/**
	 *
	 */
	public int getLine(){
		return _line;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(NODETYPE type) {
		this.type = type;
	}

	public void addPredecessorNode(CFGNode cfgNode) {
		if(null!=cfgNode) {
			if(null==this.getPredecessorNodes()) {
				this.predecessorNodes = new ArrayList<>();
			}
			this.predecessorNodes.add(cfgNode);
		}
	}
}
