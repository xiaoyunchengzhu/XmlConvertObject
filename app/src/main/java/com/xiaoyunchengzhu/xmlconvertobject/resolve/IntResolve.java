package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class IntResolve  extends ConfigConverter {
    public IntResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Integer solve(Node urlNode) {
        return Integer.parseInt(((Element)urlNode).getTextContent().trim());
    }
}
