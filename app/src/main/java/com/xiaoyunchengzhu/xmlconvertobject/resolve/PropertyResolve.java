package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigUtil;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import java.lang.reflect.Field;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class PropertyResolve extends ConfigConverter {
    private Field field=null;
    private Object object;
    public PropertyResolve(ConfigResourcesSection configResourcesSection,Field field,Object object) {
        super(configResourcesSection);
        this.field=field;
        this.object=object;
    }

    @Override
    public Object solve(Node urlNode) {
        NamedNodeMap attributes = urlNode.getAttributes();
        if (attributes!=null) {
            Node valuenode = attributes.getNamedItem("value");
             if (valuenode!=null) {
                 String value=valuenode.getNodeValue();
                     if (value.startsWith("@")) {
                         Object gaol = convert(ConfigUtil.getId(value), ConfigUtil.getType(value));
                         try {
                             field.set(object, gaol);
                         } catch (IllegalAccessException e) {
                             e.printStackTrace();
                         }
                     } else {
                         try {
                             ConfigUtil.setFiled(field, object, value);
                         } catch (IllegalAccessException e) {
                             e.printStackTrace();
                         }
                     }
             }else {
                 int length = urlNode.getChildNodes().getLength();

                 if (length>1) {
                     for (int i = 0; i < urlNode.getChildNodes().getLength(); i++) {
                         if (urlNode.getChildNodes().item(i) instanceof Element) {
                             Node firstChild = urlNode.getChildNodes().item(i);
                             Object gaol = convert(firstChild, firstChild.getNodeName());
                             try {
                                 field.set(object, gaol);
                             } catch (IllegalAccessException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
                 }else {
                     try {
                         field.set(object,((Element)urlNode).getTextContent().trim());
                     } catch (IllegalAccessException e) {
                         e.printStackTrace();
                     }
                 }

             }
        }

        return null;
    }


}
