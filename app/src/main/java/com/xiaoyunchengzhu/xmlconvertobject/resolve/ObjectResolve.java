package com.xiaoyunchengzhu.xmlconvertobject.resolve;

import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigConverter;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigResourcesSection;
import com.xiaoyunchengzhu.xmlconvertobject.Configuration.ConfigUtil;
import com.xiaoyunchengzhu.xmlconvertobject.exception.IdRepeat;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ObjectResolve extends ConfigConverter {


    public ObjectResolve(ConfigResourcesSection configResourcesSection) {
        super(configResourcesSection);
    }

    @Override
    public Object solve(Node urlNode) {
        Object o=null;
        try {

            if (urlNode.getAttributes().getNamedItem("ref")==null){
            Node aClass1 = urlNode.getAttributes().getNamedItem("class");
            if (aClass1!=null) {
                String className = aClass1.getNodeValue();

                Class<?> aClass = Class.forName(className);
                o = aClass.newInstance();
                Node ref = urlNode.getAttributes().getNamedItem("ref");

                for (int i = 0; i < urlNode.getAttributes().getLength(); i++) {
                    String nodeName = urlNode.getAttributes().item(i).getNodeName();
                    if (!nodeName.equals("id") && !nodeName.equals("class")) {
                        Field declaredField = aClass.getDeclaredField(nodeName);
                        declaredField.setAccessible(true);
                        String value = urlNode.getAttributes().item(i).getNodeValue();
                        if (!value.startsWith("@")) {
                            ConfigUtil.setFiled(declaredField, o, value);
                        } else {
                            declaredField.set(o, convert(ConfigUtil.getId(value), ConfigUtil.getType(value)));
                        }
                    }
                }
                NodeList childNodes = urlNode.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    if (childNodes.item(i) instanceof Element) {
                        if (childNodes.item(i).getNodeName().equals("property")) {

                            Node propertyName = childNodes.item(i).getAttributes().getNamedItem("name");
                            Field declaredField = aClass.getDeclaredField(propertyName.getNodeValue());
                            declaredField.setAccessible(true);
                            PropertyResolve propertyresolve =  new PropertyResolve(configResourcesSection, declaredField, o);
                            propertyresolve.convert(childNodes.item(i));
//                        declaredField.set(o, propertyresolve.solve(childNodes.item(i)));
                        }
                    }
                }
            }

            }else {
                Node ref = urlNode.getAttributes().getNamedItem("ref");
                o = convert(ConfigUtil.getId(ref.getNodeValue()), ConfigUtil.getType(ref.getNodeValue()));

            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            try {
                throw  new IdRepeat("解析失败");
            } catch (IdRepeat idRepeat) {
                idRepeat.printStackTrace();
            }
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        }
        return  o;
    }

}
