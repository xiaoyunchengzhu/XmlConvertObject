package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import android.util.Log;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;
import com.xiaoyunchengzhu.xmlconvertobject.exception.IdRepeat;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ConfigResolve extends ConfigConverter {

    public ConfigResourcesSection urlResourcesSection=null;

    public ConfigResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
        urlResourcesSection=configResourcesSection;
    }


    public Object convert(String id) {
        Object object=null;
        Node node=urlResourcesSection.getRootElement();
        node.getNodeName();

        NodeList childNodes = node.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
           String nodename= item.getNodeName();
            Log.i("value",nodename);
            NamedNodeMap attributes = item.getAttributes();
            if (attributes!=null){
            Node id1 = item.getAttributes().getNamedItem("id");
            if (id1!=null) {
                String nodeValue = id1.getNodeValue();
                Log.i("value",nodename+nodeValue);
                if (nodeValue.equals(id)) {
                    if (object == null) {
                        object = convert(id, item.getNodeName());
                    } else {
                        try {
                            throw new IdRepeat("id重复，未知查找。");
                        } catch (IdRepeat idRepeat) {
                            idRepeat.printStackTrace();
                        }
                    }
                }
            }
            }
        }
        return object;
    }

    @Override
    public Object solve(Node urlNode) {

        return new Object();
    }
}
