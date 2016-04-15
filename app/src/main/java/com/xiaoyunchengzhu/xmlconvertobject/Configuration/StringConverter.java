package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by zhangshiyu on 2016/4/14.
 */
public class StringConverter extends ConfigConverter{

    private String node;
    public StringConverter(String node)
    {
        this.node=node;
    }
    @Override
    Object convert(String id) {
        Object o=null;
        Element rootElement = urlResourcesSection.getRootElement();
        Element element = (Element)rootElement.getElementsByTagName(node).item(0);
        NodeList add = element.getElementsByTagName("string");
        for (int i=0;i<add.getLength();i++)
        {
            if (add.item(i).getAttributes().getNamedItem("Id").getNodeValue().equals(id))
            {
                   Element element1= (Element) add.item(i);
                o=element1.getTextContent();
            }
        }
        return o;
    }
}
