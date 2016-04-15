package com.xiaoyunchengzhu.xmlconvertobject.model;

import java.util.List;

/**
 * Created by zhangshiyu on 2016/4/13.
 */
public class Person {

    private String name;
    private String age;
    private List<DataUrlBean> list;

    public List<DataUrlBean> getList() {
        return list;
    }

    public void setList(List<DataUrlBean> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
