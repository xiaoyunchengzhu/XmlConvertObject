package com.xiaoyunchengzhu.xmlconvertobject.model;

/**
 * Created by zhangshiyu on 2016/4/13.
 */
public class Response {


    private String error;
    private String success;
    private String type;
    private DataUrlBean dataUrlBean;

    public DataUrlBean getDataUrlBean() {
        return dataUrlBean;
    }

    public void setDataUrlBean(DataUrlBean dataUrlBean) {
        this.dataUrlBean = dataUrlBean;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
