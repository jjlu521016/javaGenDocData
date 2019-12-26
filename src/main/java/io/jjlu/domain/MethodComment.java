package io.jjlu.domain;

import java.util.List;

/**
 * @author lujie
 * @since 20191226
 */
public class MethodComment {

    private String methodName;

    private String methodDesc;

    private String returnType;

    private String returnDesc;

    private List<String> returnParamList;

    private List<ParamComment> paramCommentList;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public List<ParamComment> getParamCommentList() {
        return paramCommentList;
    }

    public void setParamCommentList(List<ParamComment> paramCommentList) {
        this.paramCommentList = paramCommentList;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<String> getReturnParamList() {
        return returnParamList;
    }

    public void setReturnParamList(List<String> returnParamList) {
        this.returnParamList = returnParamList;
    }
}
