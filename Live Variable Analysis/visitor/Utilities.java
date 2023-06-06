package visitor;

import java.util.HashSet;
import java.util.TreeSet;

public class Utilities {

    public TreeSet<String> _AddCurrentINSETIntoResult(TreeSet<String> _INSET){
        TreeSet<String> _inset = new TreeSet<>();
        for(String _in : _INSET){
            _inset.add(_in);
        }
        return  _inset;
    }

    public String generateRegex(String expression){
        String regex = new String();
        if(expression.contains("+")){
            regex = "\\+";
        } else if(expression.contains("&&")){
            regex = "\\&\\&";
        } else if (expression.contains("||")){
            regex = "\\|\\|";
        } else if (expression.contains("<=")){
            regex = "<=";
        } else if (expression.contains("!=")){
            regex = "!=";
        } else if (expression.contains("-")){
            regex = "\\-";
        } else if (expression.contains("*")){
            regex = "\\*";
        } else if (expression.contains("/")){
            regex = "\\/";
        }
        return regex;
    }

    public boolean isBinop(String expression){
        if(expression.contains("+")){
            return true;
        } else if(expression.contains("&&")){
            return true;
        } else if (expression.contains("||")){
            return true;
        } else if (expression.contains("<=")){
            return true;
        } else if (expression.contains("!=")){
            return true;
        } else if (expression.contains("-")){
            return true;
        } else if (expression.contains("*")){
            return true;
        } else if (expression.contains("/")){
            return true;
        }
        return false;
    }

    public boolean isArrayLookUp(String expression){
        return expression.contains("[") && expression.contains("]");
    }

    public boolean isArrayLength(String expression){
        return expression.contains(".length");
    }

    public boolean isMessageSend(String expression){
        return (expression.contains(".") && expression.contains("(") && expression.contains(")"));
    }

    public boolean isIntegerLiteral(String expression){
        try{
            Integer.parseInt(expression);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isFloatLiteral(String expression){
        try{
            Float.parseFloat(expression);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isTrueLiteral(String expression){
        return expression.contains("true");
    }

    public boolean isFalseLiteral(String expression){
        return expression.contains("false");
    }

    public boolean isIdentifier(String identifier, HashSet<String> _VariableSet){
        return _VariableSet.contains(identifier);
    }

    public boolean isThis(String expression){
        return expression.contains("this");
    }

    public boolean isArrayAllocation(String expression){
        return expression.contains("new int");
    }

    public boolean isAllocationExpression(String expression){
        return expression.contains("new");
    }

    public boolean isNotExpression(String expression){
        return expression.contains("!");
    }
}
