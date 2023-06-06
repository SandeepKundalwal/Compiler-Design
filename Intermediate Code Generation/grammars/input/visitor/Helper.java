package visitor;

public class Helper {

    public static int variableIndex = 1;
    public static int fieldIndex = 1;

    public String getNewVariable(){
        return ("t" + variableIndex);
    }

    public void incrementVariableIndex(){
        variableIndex++;
    }

    public void resetVariableIndex(){
        variableIndex = 1;
    }

    public String getNewField(){
        return ("t" + variableIndex++);
    }

    public void resetFieldIndex(){
        variableIndex = 1;
    }

    public boolean isIntOrFloat(String expr){
        if(expr.equals("int") || expr.equals("float")){
            return true;
        }
        return false;
    }

    public boolean isStringInt(String expr){
        try{
            Integer.parseInt(expr);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public boolean isStringFloat(String expr){
        try{
            Float.parseFloat(expr);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Helps to avoid using {@code @SuppressWarnings({"unchecked"})} when casting to a generic type.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T uncheckedCast(Object obj) {
        return (T) obj;
    }
}
