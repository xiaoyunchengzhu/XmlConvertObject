package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigUtil;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ListResolve extends ConfigConverter {


    public ListResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public List<Object> solve(Node urlNode) {
        if (urlNode.getAttributes().getNamedItem("ref")==null){
        NodeList childNodes = ((Element) urlNode).getChildNodes();
        List<Object> list=new ArrayList<>();
        for (int i=0;i<childNodes.getLength();i++){

            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Object convert = convert(item, item.getNodeName());
                list.add(convert);
            }
        }
        return list;
        }else {
            Node ref = urlNode.getAttributes().getNamedItem("ref");
            return (List<Object>) convert(ConfigUtil.getId(ref.getNodeValue()), ConfigUtil.getType(ref.getNodeValue()));

        }
    }
}
