package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by zhangshiyu on 2016/11/4.
 */

public class KeyResolve extends ConfigConverter {
    public KeyResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Object solve(Node urlNode) {
        Object object=null;
        if (urlNode instanceof Element){
            Element element= (Element) urlNode;
            object=element.getTextContent().trim();
        }
        if (object==null) {
            NodeList childNodes = urlNode.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    object = convert(item);
                }
            }
        }
        return object;
    }
}
