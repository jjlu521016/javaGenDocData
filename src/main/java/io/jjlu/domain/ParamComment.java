package io.jjlu.domain;

/**
 * @author lujie
 * @since 20191226
 */
public class ParamComment {

    private String fieldName;

    private String filedDesc;

    private String filedType;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFiledDesc() {
        return filedDesc;
    }

    public void setFiledDesc(String filedDesc) {
        this.filedDesc = filedDesc;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }
}
