package com.xiaoyunchengzhu.xmlconvertobject.model;

/**
 * Created by zhangshiyu on 2016/4/14.
 */
public class DataUrlBean {
    private String name;//"urllist"
    private String url;//="http://www.baidutest.com"
    private String expires;//="300"
    private String type;//="get"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
