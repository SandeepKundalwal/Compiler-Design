public class Helper {

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
}
