package com.xiaoyunchengzhu.xmlconvertobject.Configuration;



import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by zhangshiyu on 2016/4/13.
 * converter abstract class.
 */
public  abstract class ConfigConverter implements Resolve{

        public    ConfigResourcesSection configResourcesSection;
       public ConfigConverter(ConfigResourcesSection configResourcesSection){
           this.configResourcesSection=configResourcesSection;
       }



    public Object convert(String id){
        Node nodeById = ConfigUtil.getNodeById(id,configResourcesSection);
            return convert(id,nodeById.getNodeName());
    }
    public Object convert(Node node,String type){
       return ConfigUtil.convert(node,type,configResourcesSection);
    }

    public Object  convert(String id,String type){
       return ConfigUtil.convert(ConfigUtil.getNodeById(id,configResourcesSection),type,configResourcesSection);
    }
    public Object convert(Node urlNode){
        Object object=null;
        boolean isnull=false;
        NodeList childNodes = urlNode.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if (item!=null) {
                if (item.getNodeName().equals("null")) {
                    isnull=true;
                }
            }
        }
        if (!isnull) {
            if (urlNode.getAttributes().getNamedItem("ref") == null) {
                object = solve(urlNode);
            } else {
                Node ref = urlNode.getAttributes().getNamedItem("ref");
                object = convert(ConfigUtil.getId(ref.getNodeValue()), ConfigUtil.getType(ref.getNodeValue()));

            }
            return object;
        }else {
            return null;
        }
    }

}
