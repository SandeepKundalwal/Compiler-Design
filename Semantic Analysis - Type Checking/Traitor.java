public class Traitor {
    int assignmentErrors;
    int binaryOperationErrors;
    int controlErrors;
    int functionErrors;

    public Traitor(){
        this.assignmentErrors = 0;
        this.binaryOperationErrors = 0;
        this.controlErrors = 0;
        this.functionErrors = 0;
    }

    public void incrementAssignmentErrorCounter(){
        this.assignmentErrors++;
    }

    public void incrementBinaryOerationErrorCounter(){
        this.binaryOperationErrors++;
    }

    public void incrementControlErrorCounter(){
        this.controlErrors++;
    }

    public void incrementFunctionErrorCounter(){
        this.functionErrors++;
    }

}
