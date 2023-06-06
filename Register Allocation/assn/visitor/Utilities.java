package visitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import static visitor.Constants.*;

public class Utilities {

    /**
     * Checks whether the given value is of Integer type
     */
    public Boolean isStringInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Checks whether the given value is of Float type
     */
    public Boolean isStringFloat(String value){
        try{
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public void cloneLinkedHashMap(LinkedHashMap<String, ArrayList<GraphNode>> _interferenceGraph, LinkedHashMap<String, ArrayList<GraphNode>> _originalInterferenceGraph){
        for(Map.Entry<String, ArrayList<GraphNode>> _graphIterator : _interferenceGraph.entrySet()){
            ArrayList<GraphNode> _neighbourNodes = new ArrayList<>();
            for(GraphNode _graphNode : _graphIterator.getValue()){
                String src = _graphNode.src;
                String des = _graphNode.des;
                _neighbourNodes.add(new GraphNode(src, des));
            }
            _originalInterferenceGraph.put(_graphIterator.getKey(), _neighbourNodes);
        }
    }

    public StringBuilder generateObjectR(Integer _RegisterLimit){
        Integer _registerIndex = 0;
        StringBuilder _objectR = new StringBuilder();
        while(_registerIndex < _RegisterLimit){
            _objectR.append("Object R").append(_registerIndex).append(SEMICOLON).append(NEWLINE);
            _registerIndex++;
        }
        return _objectR;
    }

    public Boolean isLiteral(String _identifier){
        if(isStringInteger(_identifier) || isStringFloat(_identifier) || _identifier.equals(TRUE) || _identifier.equals(FALSE)){
            return true;
        }
        return false;
    }

    /**
     * Handles the Identifier
     * @param _identifier
     * @param className
     * @param methodName
     * @param _SymbolTable
     * @return
     */
    public StringBuilder handleIdentifier(String _identifier, String className, String methodName, SymbolTable _SymbolTable){
        StringBuilder _handleIdentifierSB = new StringBuilder();
        if(isLiteral(_identifier)){
            _handleIdentifierSB.append(_identifier);
        } else{
            String _mapping = _SymbolTable.getRHSVariableMapping(className, methodName, _identifier);
            _handleIdentifierSB.append(_mapping);
        }
        return _handleIdentifierSB;
    }

    /**
     * Handles Binary Operations
     * @param LHS
     * @param _operator
     * @param RHS
     * @param className
     * @param methodName
     * @param _SymbolTable
     * @return
     */
    public StringBuilder handleExpression(String LHS, String _operator, String RHS, String className, String methodName, SymbolTable _SymbolTable){
        StringBuilder _handleExpressionSB = new StringBuilder();
        _handleExpressionSB.append(handleIdentifier(LHS, className, methodName, _SymbolTable));
        _handleExpressionSB.append(SPACE).append(_operator).append(SPACE);
        _handleExpressionSB.append(handleIdentifier(RHS, className, methodName, _SymbolTable));
        return _handleExpressionSB;
    }
}
