package io.jjlu.domain;

import java.util.List;

/**
 * @author lujie
 * @since 20191226
 */
public class ClassComment {

    private String className;

    private String classDesc;

    private List<MethodComment> methodCommentList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public List<MethodComment> getMethodCommentList() {
        return methodCommentList;
    }

    public void setMethodCommentList(List<MethodComment> methodCommentList) {
        this.methodCommentList = methodCommentList;
    }
}
