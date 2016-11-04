package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigUtil;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class StringResolve  extends ConfigConverter  {


    public StringResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }


    @Override
    public String solve(Node urlNode) {
        if (urlNode.getAttributes().getNamedItem("ref")==null){
           if (ConfigUtil.isNull(urlNode)){

             return null;

           }else {

             return ((Element)urlNode).getTextContent().trim();
          }
        }else {
            Node ref = urlNode.getAttributes().getNamedItem("ref");
            return (String) convert(ConfigUtil.getId(ref.getNodeValue()), ConfigUtil.getType(ref.getNodeValue()));

        }
    }
}
