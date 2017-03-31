package com.xiaoyunchengzhu;

import javax.lang.model.type.TypeMirror;

/**
 * Created by zhangshiyu on 2017/3/31.
 */

public class Xmlbean {
    private TypeMirror fieldtype;
    private String fildName;
    private String value;



    public TypeMirror getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(TypeMirror fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getFildName() {
        return fildName;
    }

    public void setFildName(String fildName) {
        this.fildName = fildName;
    }

    public String getId() {
        return value;
    }

    public void setId(String id) {
        this.value = id;
    }
}
