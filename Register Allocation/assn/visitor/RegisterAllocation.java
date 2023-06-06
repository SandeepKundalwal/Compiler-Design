package visitor;

import java.util.*;

public class RegisterAllocation {
    Utilities _Utility;

    public RegisterAllocation(){
        _Utility = new Utilities();
    }


    /**
     * Initialization phase of the Register allocation
     * @param _RegisterLimit -> Number of available register
     * @param _InterferenceGraph -> self-explanatory name
     * @param _SymbolTable -> Holds information related to class, methods, variables etc
    */
    public void init(Integer _RegisterLimit, InterferenceGraph _InterferenceGraph, SymbolTable _SymbolTable){
        if(_InterferenceGraph != null && _InterferenceGraph._graphTable != null){
            /**
             * Traversing every class of the program
             */
            for(Map.Entry<String, GraphClassInfo> _graphClass : _InterferenceGraph._graphTable.entrySet()){
                /**
                 * Traversing all the methods that belongs to a particular class
                 */
                for(Map.Entry<String, GraphMethodInfo> _graphMethod : _graphClass.getValue()._MethodTable.entrySet()){
                    String className = _graphClass.getValue()._className;
                    String methodName = _graphMethod.getValue()._methodName;
                    _SymbolTable.table.get(className)._MethodTable.get(methodName)._spillCount = runColoringAlgorithm(_RegisterLimit, className, methodName, _graphMethod.getValue()._interferenceGraph, _SymbolTable);
                }
            }
        }
    }


    /**
     * Run the Graph Coloring Algorithm to assign a non-conflicting register to every local variable of a particular
     * method
     */
    public Integer runColoringAlgorithm(Integer _RegisterLimit, String className, String methodName, LinkedHashMap<String, ArrayList<GraphNode>> _interferenceGraph, SymbolTable _SymbolTable){
        Integer _spillCount = 0;
        Boolean alreadyCloned = false;
        Integer sameVertexNumberAsPreviousIteration = 0;
        /**
         * Original Interference Graph to preserve the neighbours of each vertex as we remove nodes and all its edges
         * from the Interference Graph as per Coloring Algorithm
         */
        LinkedHashMap<String, ArrayList<GraphNode>> _originalInterferenceGraph = new LinkedHashMap<>();
        while(!_interferenceGraph.isEmpty()){
            if(!alreadyCloned){
                _Utility.cloneLinkedHashMap(_interferenceGraph, _originalInterferenceGraph);
                alreadyCloned = true;
            }

            if(_interferenceGraph.size() == sameVertexNumberAsPreviousIteration){
                return spillEverything(_interferenceGraph, _spillCount, _SymbolTable, className, methodName);
            }

            sameVertexNumberAsPreviousIteration = _interferenceGraph.size();
            for(Map.Entry<String, ArrayList<GraphNode>> _graph : _originalInterferenceGraph.entrySet()){
                String _vertex = _graph.getKey();
                if(_interferenceGraph.containsKey(_vertex)){
                    Integer _noOfVertexEdges = _interferenceGraph.get(_vertex).size();
                    if(_noOfVertexEdges <= _RegisterLimit){
                        Boolean _coloringSuccessful = ColorIt(_RegisterLimit, _SymbolTable, className, methodName, _vertex, _originalInterferenceGraph);
                        /**
                         * If coloring of the vertex is successful, we have allocated a register to the variable/vertex
                         * otherwise, we spill the variable/vertex
                        */
                        if(_coloringSuccessful){
                            /**
                             * Remove all the edges of the vertex from the Interference Graph
                            */
                            removeAllEdges(_vertex, _interferenceGraph);
                        } else {
                            assignSpillIndex(_SymbolTable, className, methodName, _vertex, _spillCount);
                            removeAllEdges(_vertex, _interferenceGraph);
                            _spillCount++;
                        }
                    }
                }

            }
        }
        return _spillCount;
    }

    public Integer spillEverything(LinkedHashMap<String, ArrayList<GraphNode>> _interferenceGraph, Integer _spillCount, SymbolTable _SymbolTable, String className, String methodName){
        for(Map.Entry<String, ArrayList<GraphNode>> _graph : _interferenceGraph.entrySet()){
            assignSpillIndex(_SymbolTable, className, methodName, _graph.getKey(), _spillCount);
            _spillCount++;
        }
        return _spillCount;
    }

    /**
     * Removes all the edges of the vertex from the Interference Graph
     */
    public void removeAllEdges(String _vertex, LinkedHashMap<String, ArrayList<GraphNode>> _interferenceGraph){
        /**
         * Keeping track of all the neighbours of the vertex as the Interference Graph is undirected,
         * so we need to remove the edge from every neighbour to the vertex.
         */
        ArrayList<String> _neighbourVertices = new ArrayList<>();
        for(GraphNode _node : _interferenceGraph.get(_vertex)){
            _neighbourVertices.add(_node.des);
        }
        _interferenceGraph.remove(_vertex);
        /**
         * Since Interference Graph is Undirected, we need to remove the edge from the destination vertices too
        */
        for(String _neighbourVertex : _neighbourVertices){
            _interferenceGraph.get(_neighbourVertex).removeIf(_graphNode -> _graphNode.des.equals(_vertex));
        }
    }


    /**
     * Assign the spill index to the spilled variable so that we can perform LOAD/STORE operations
     * on memory appropriately
     */
    public void assignRegisterIndex(SymbolTable _SymbolTable, String className, String methodName, String _vertex, String _assignedRegister){
        _SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(_vertex)._isSpilled = false;
        _SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(_vertex)._mapping = _assignedRegister;
    }


    /**
     * Assign the spill index to the spilled variable so that we can perform LOAD/STORE operations
     * on memory appropriately
     */
    public void assignSpillIndex(SymbolTable _SymbolTable, String className, String methodName, String _vertex, Integer _spillIndex){
        _SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(_vertex)._isSpilled = true;
        _SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(_vertex)._mapping = Integer.toString(_spillIndex);
    }


    /**
     * Color the vertex with non-conflicting color from its neighbour
     * @param _RegisterLimit -> Number of available registers
     * @param _SymbolTable -> Contains information related to the classes, methods, variables etc.
     * @param className -> current name of the class
     * @param methodName -> current method in which register allocation is being done
     * @param vertex -> the vertex which we need to color uniquely; otherwise, spill it
     * @param _originalInterferenceGraph -> original interference graph
     * @return :- Boolean Type -> whether the coloring of the vertex is successful or we spilled it
     */
    public Boolean ColorIt(Integer _RegisterLimit, SymbolTable _SymbolTable, String className, String methodName, String vertex, LinkedHashMap<String, ArrayList<GraphNode>> _originalInterferenceGraph){
        Integer _registerIndex = 0;

        /**
         * colors used by the neighbouring vertices
         */
        Set<String> colorsUsed = new HashSet<>();
        for(GraphNode _graphNode : _originalInterferenceGraph.get(vertex)){
            String color = getColor(_SymbolTable, className, methodName, _graphNode.des);
            if(color != null){
                colorsUsed.add(color);
            }
        }
        /**
         * Trying to assign available colors to the vertex
         */
        while(_registerIndex < _RegisterLimit){
            String maybeRegister = "R" + _registerIndex;
            if(!colorsUsed.contains(maybeRegister)){
                /**
                 * non-conflicting color found, assign it and return true as we succeeded in assigning
                 * a unique color to the vertex
                 */
                assignRegisterIndex(_SymbolTable, className, methodName, vertex, maybeRegister);
                return true;
            }
            _registerIndex++;
        }
        return false;
    }


    /**
     * Get the color that is assigned to a particular variable, if no color is yet assigned -> return null
     */
    public String getColor(SymbolTable _SymbolTable, String className, String methodName, String vertex){
        if(!_SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(vertex)._isSpilled){
            return _SymbolTable.table.get(className)._MethodTable.get(methodName)._VariableTable.get(vertex)._mapping;
        }
        return null;
    }
}

