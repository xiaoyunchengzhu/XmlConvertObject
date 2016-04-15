package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by zhangshiyu on 2016/4/13.
 */
public class ObjectConverter extends ConfigConverter  {


    private String node;
    public ObjectConverter(String node)
    {
        this.node=node;
    }
    @Override
    public Object convert(String id) {

        Object o=null;
        Element rootElement = urlResourcesSection.getRootElement();
        Element element = (Element)rootElement.getElementsByTagName(node).item(0);
        NodeList add = element.getElementsByTagName("add");
        for (int i=0;i<add.getLength();i++)
        {
            Node id1 = add.item(i).getAttributes().getNamedItem("Id");
            if (id1!=null) {
                if (id1.getNodeValue().equals(id) && add.item(i).getAttributes().getNamedItem("Type") == null) {
                    o = urlResourcesSection.getValueFromUrlNode(add.item(i), this);
                }
            }
        }
        return o;
    }


}
