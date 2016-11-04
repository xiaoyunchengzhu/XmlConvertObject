package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class MapResolve extends ConfigConverter {

    public MapResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Map<Object, Object> solve(Node urlNode) {
        Map<Object,Object> map=null;
        NodeList childNodes = urlNode.getChildNodes();
        if (childNodes.getLength()>1){
            map=new HashMap<>();
        }
        for (int i=0;i<childNodes.getLength();i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName.equals("entity")) {

                Node key1 = item.getAttributes().getNamedItem("key");
                Object valuemap = null, keymap = null;
                if (key1!=null) {


                    String key = key1.getNodeValue();
                    Node value1 = item.getAttributes().getNamedItem("value");
                    if (value1!=null) {
                        String value = value1.getNodeValue();
                        if (value.startsWith("@")) {
                            valuemap = convert(ConfigUtil.getId(value), ConfigUtil.getType(value));
                        } else {
                            valuemap = value;
                        }
                    }else {
                        valuemap=((Element)urlNode).getTextContent().trim();
                    }
                    if (key.startsWith("@")) {
                        keymap = convert(ConfigUtil.getId(key), ConfigUtil.getType(key));
                    } else {
                        keymap = key;
                    }
                    map.put(keymap, valuemap);
                }else {

                    for (int j=0;j<item.getChildNodes().getLength();j++){
                        if (item instanceof Element){
                            Node item1 = item.getChildNodes().item(j);
                            if (item1!=null){
                                if (item1.getNodeName().equals("key")){

                                    KeyResolve keyResolve=new KeyResolve(configResourcesSection);
                                     keymap=keyResolve.convert(item1);
                                }else if (item1.getNodeName().equals("value")){
                                    ValueResolve valueResolve=new ValueResolve(configResourcesSection);
                                    valuemap=valueResolve.convert(item1);
                                }
                            }
                        }

                    }
                    if (map!=null) {
                        map.put(keymap, valuemap);
                    }
                }

            }
        }
        return map;
    }
}
