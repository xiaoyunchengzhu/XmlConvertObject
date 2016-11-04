package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;
import com.xiaoyunchengzhu.xmlconvertobject.exception.IdRepeat;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class CharResolve  extends ConfigConverter {
    public CharResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Character solve(Node urlNode) {
        Character character=null;
        String nodeValue = ((Element)urlNode).getTextContent().trim();

        if (nodeValue.length()==1){
            character=nodeValue.charAt(0);
        }else {
            try {
                throw new IdRepeat("非char类型，解析出错");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return character;
    }
}
