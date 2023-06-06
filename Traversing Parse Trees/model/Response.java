package model;

public class Response {

    private String className;
    private String methodName;
    private Integer formalParameterCount;
    private Integer localVarCount;
    private Integer binaryOprCount;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getFormalParameterCount() {
        return formalParameterCount;
    }

    public void setFormalParameterCount(Integer formalParameterCount) {
        this.formalParameterCount = formalParameterCount;
    }

    public Integer getLocalVarCount() {
        return localVarCount;
    }

    public void setLocalVarCount(Integer localVarCount) {
        this.localVarCount = localVarCount;
    }

    public Integer getBinaryOprCount() {
        return binaryOprCount;
    }

    public void setBinaryOprCount(Integer binaryOprCount) {
        this.binaryOprCount = binaryOprCount;
    }
}
