package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

/**
 * Created by zhangshiyu on 2016/4/13.
 * manage converter'object,xml to object.
 */
public class ConfigManger {

    private ConfigConverter configConverter;
    public  String listNode="List";
    public  String objectNode="Object";
    public  String stringNode="String";

    public Object convertList(String id)
    {
        configConverter=new ListConverter(listNode);
        return  configConverter.convert(id);
    }
    public Object convertObject(String id)
    {
        configConverter=new ObjectConverter(objectNode);
        return configConverter.convert(id);
    }
    public Object convertString(String id)
    {
        configConverter=new StringConverter(stringNode);
        return configConverter.convert(id);
    }
    protected Object convert(String id,String type)
    {
        Object o=null;
          if (type.equals(listNode))
          {
              o= convertList(id);
          }else if (type.equals(objectNode))
          {
              o= convertObject(id);
          }else if (type.equals(stringNode))
          {
              o=convertString(id);
          }
        return o;

    }

}
