package visitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class InterferenceGraph {
    LinkedHashMap<String, GraphClassInfo> _graphTable;

    public InterferenceGraph(){
        this._graphTable = new LinkedHashMap<>();
    }

    /**
     * Class Declaration for the Interference Graph
     */
    public void GraphClassDeclaration(String className){
        if(!_graphTable.containsKey(className)){
            _graphTable.put(className, new GraphClassInfo(className));
        }
    }

    /**
     * Method Declaration for the Interference Graph
     */
    public void GraphMethodDeclaration(String className, String methodName){
        if(_graphTable.containsKey(className)){
            if(!_graphTable.get(className)._MethodTable.containsKey(methodName)){
                _graphTable.get(className)._MethodTable.put(methodName, new GraphMethodInfo(methodName));
            }
        }
    }

    /**
     * Add vertices to the Interference Graph
     */
    public void AddVertices(String className, String methodName, String vertex){
        if(_graphTable.containsKey(className) && _graphTable.get(className)._MethodTable.containsKey(methodName)){
            if(!_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(vertex)){
                _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.put(vertex, new ArrayList<>());
            }
        }
    }

    /**
     * Adding edges to the Interference Graph
     */
    public void AddEdge(String className, String methodName, String src, String des){
        if(_graphTable.containsKey(className) && _graphTable.get(className)._MethodTable.containsKey(methodName)){
            if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(src)){
                _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(src).add(new GraphNode(src, des));
            }

            if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(des)){
                _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(des).add(new GraphNode(des, src));
            }
        }
    }

    /**
     * Looks fishy as of now ----> CHECK IT OUT PROPERLY
     */
    public Boolean EdgeExists(String className, String methodName, String src, String des){
        if(_graphTable.containsKey(className) && _graphTable.get(className)._MethodTable.containsKey(methodName)){
            if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(src)){
                if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(src).size() != 0){
                    for(GraphNode _graphNode : _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(src)){
                        if(src.equals(_graphNode.src) && des.equals(_graphNode.des)){
                            return true;
                        }
                    }
                }
            }

            if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(des)){
                if(_graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(des).size() != 0){
                    for(GraphNode _graphNode : _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.get(des)){
                        if(des.equals(_graphNode.src) && src.equals(_graphNode.des)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the vertex exists in the Interference Graph
     */
    public Boolean vertexExists(String className, String methodName, String vertex){
        return _graphTable.get(className)._MethodTable.get(methodName)._interferenceGraph.containsKey(vertex);
    }
}

/**
 * Graph node that contains the edge from source to destination and the color of the source vertex
 */
class GraphNode{
    String src;
    String des;

    public GraphNode(String src, String des){
        this.src = src;
        this.des = des;
    }
}

/**
 * Class that contains all the methods that a class have
 */
class GraphClassInfo{
    String _className;
    LinkedHashMap<String, GraphMethodInfo> _MethodTable;

    public GraphClassInfo(String _className){
        this._className = _className;
        this._MethodTable = new LinkedHashMap<String, GraphMethodInfo>();
    }
}

/**
 * Class that contains the Interference Graph for each method as we are doing Intra_Method Register Allocation
 */
class GraphMethodInfo{
    String _methodName;
    LinkedHashMap<String, ArrayList<GraphNode>> _interferenceGraph;

    public GraphMethodInfo(String _methodName){
        this._methodName = _methodName;
        this._interferenceGraph = new LinkedHashMap<>();
    }
}
