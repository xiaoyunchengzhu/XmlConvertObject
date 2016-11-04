package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ShortResolve  extends ConfigConverter  {
    public ShortResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Short solve(Node urlNode) {
        return Short.parseShort(((Element)urlNode).getTextContent().trim());
    }
}
