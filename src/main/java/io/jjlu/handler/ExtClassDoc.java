package io.jjlu.handler;

import com.alibaba.fastjson.JSONObject;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import io.jjlu.domain.ClassComment;
import io.jjlu.domain.MethodComment;
import io.jjlu.domain.ParamComment;
import io.jjlu.util.ValidateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jjlu521016
 * @since 20191226
 */
public class ExtClassDoc {

    final ClassDoc classDoc;

    public ExtClassDoc(ClassDoc classDoc) {
        this.classDoc = ValidateUtils.checkNotNull(classDoc, "classDoc is null");
    }

    public ClassComment getClassComment() {
        ClassComment classComment = assembleClassComment(classDoc);
        List<MethodComment> list = new ArrayList<>(classDoc.methods().length);
        for (MethodDoc method : classDoc.methods()) {
            list.add(assembleMethodComment(method));
        }
        classComment.setMethodCommentList(list);
        System.out.println("获取到的doc信息:" + JSONObject.toJSONString(classComment, true));
        return classComment;
    }

    /**
     * 输出格式化的注释信息
     *
     * @param doc {@link com.sun.tools.javadoc.ClassDocImpl}实例
     * @return
     */
    public final ClassComment assembleClassComment(ClassDoc doc) {

        ValidateUtils.checkNotNull(doc, "doc is null");
        ClassComment classComment = new ClassComment();
        classComment.setClassName(doc.qualifiedName());
        classComment.setClassDesc(doc.commentText());
        return classComment;
    }

    /**
     * 返回方法相关的注释对象
     *
     * @param doc {@link com.sun.tools.javadoc.MethodDocImpl}实例
     * @return
     */
    public final MethodComment assembleMethodComment(MethodDoc doc) {
        ValidateUtils.checkNotNull(doc, "doc is null");

        Parameter[] parameters = doc.parameters();
        Map<String, String> paramMap = new HashMap(doc.parameters().length);
        for (Parameter param : parameters) {
            paramMap.put(param.name(), param.type().qualifiedTypeName());
        }
        MethodComment methodComment = new MethodComment();
        methodComment.setMethodName(doc.name());
        methodComment.setMethodDesc(doc.commentText());
        List<ParamComment> paramCommentList = new ArrayList<>(doc.tags().length);
        for (Tag tag : doc.tags()) {
            if (tag instanceof ParamTag) {
                ParamComment paramComment = assembleParamComment(paramMap, (ParamTag) tag);
                paramCommentList.add(paramComment);
            } else if (tag.name().equals("@return")) {
                //返回参数
                methodComment.setReturnDesc(tag.text());
            }
        }
        methodComment.setReturnType(doc.returnType().qualifiedTypeName());
        List<String> genTypeNameList = getGenTypeNames(doc.returnType());
        methodComment.setReturnParamList(genTypeNameList);
        methodComment.setParamCommentList(paramCommentList);
        return methodComment;
    }

    /**
     * 组装参数相关
     *
     * @param paramMap <paramName,paramClass>对象
     * @param paramTag 参数Tag
     * @return 参数注释对象
     */
    private ParamComment assembleParamComment(Map<String, String> paramMap, ParamTag paramTag) {
        String paramType = paramMap.get(paramTag.parameterName());
        ParamComment paramComment = new ParamComment();
        paramComment.setFieldName(paramTag.parameterName());
        paramComment.setFiledDesc(paramTag.parameterComment());
        paramComment.setFiledType(paramType);
        return paramComment;
    }

    /**
     * 获取泛型 对象类名
     *
     * @param type type
     * @return 对象类型List
     */
    private List<String> getGenTypeNames(Type type) {
        if (type instanceof ParameterizedType) {
            List<String> ret = new ArrayList<>();
            ParameterizedType pt = type.asParameterizedType();
            Type[] typeArgs = pt.typeArguments();
            for (Type t : typeArgs) {
                ret.add(t.qualifiedTypeName());
            }
            return ret;
        }
        return null;
    }

}
