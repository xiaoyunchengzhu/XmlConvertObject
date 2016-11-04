package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import com.xiaoyunchengzhu.xmlconvertobject.resolve.ConfigResolve;

import java.io.File;

/**
 * Created by zhangshiyu on 2016/4/13.
 * manage converter'object,xml to object.
 */
public class ConfigManger {

    private ConfigConverter configConverter;
    private ConfigResourcesSection configResourcesSection;
    public ConfigManger(){
        this.configResourcesSection=new ConfigResourcesSection();
        configConverter=new ConfigResolve(configResourcesSection);
    }
    public ConfigManger(File file){
        this.configResourcesSection=new ConfigResourcesSection(file);
        configConverter=new ConfigResolve(configResourcesSection);
    }

    public<T> T convert(String id){
        return (T) configConverter.convert(id);
    }



}
