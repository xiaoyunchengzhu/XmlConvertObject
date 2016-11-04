package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.Resolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ByteResolve  extends ConfigConverter {
    public ByteResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Byte solve(Node urlNode) {
        return Byte.parseByte(((Element)urlNode).getTextContent().trim());
    }
}
