package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

/**
 * Created by zhangshiyu on 2016/4/13.
 * converter abstract class.
 */
public abstract class ConfigConverter<T> {

    ConfigResourcesSection urlResourcesSection=new ConfigResourcesSection(true);
    abstract T convert(String id);
    public Object  convert(String id,String type){
        ConfigManger configManger=new ConfigManger();

        return configManger.convert(id,type);
    }
}
