package com.xiaoyunchengzhu.xmlconvertobject.Configuration;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshiyu on 2016/4/13.
 */
public class ListConverter extends ConfigConverter  {


    private String node;
    public ListConverter(String node)
    {
        this.node=node;
    }
    @Override
    public Object convert( String id) {
        List<Object> l=null;
        Element rootElement = urlResourcesSection.getRootElement();
        Element itemList = (Element)rootElement.getElementsByTagName(node).item(0);
        NodeList li = itemList.getElementsByTagName("li");
        for (int i=0;i<li.getLength();i++)
        {
            Node id1 = li.item(i).getAttributes().getNamedItem("Id");
            if (id1!=null) {
                if (id1.getNodeValue().equals(id) && li.item(i).getAttributes().getNamedItem("Type") == null) {
                    Element item1 = (Element) li.item(i);
                    NodeList add = item1.getElementsByTagName("add");
                    NodeList string = item1.getElementsByTagName("string");
                    l = new ArrayList<>();
                    if (add.getLength() > 0) {

                        for (int j = 0; j < add.getLength(); j++) {
                            l.add(urlResourcesSection.getValueFromUrlNode(add.item(j), this));
                        }
                    } else if (string.getLength() > 0) {
                        for (int j = 0; j < string.getLength(); j++) {

                            l.add(string.item(j).getTextContent());
                        }
                    }

                }
            }
        }
        return l;
    }


}
