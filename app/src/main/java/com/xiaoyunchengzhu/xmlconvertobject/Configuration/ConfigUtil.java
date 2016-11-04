package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import android.util.Log;

import com.xiaoyunchengzhu.xmlconvertobject.exception.IdRepeat;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.BooleanResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.ByteResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.CharResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.DoubleResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.FloatResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.IntResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.ListResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.LongResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.MapResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.ObjectResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.PropertyResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.ShortResolve;
import com.xiaoyunchengzhu.xmlconvertobject.resolve.StringResolve;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class ConfigUtil {

    /**
     *
     * @param value example @Object/id
     * @return   Type
     */
    public static String getType(String value)
    {

        return value.substring(1,value.indexOf("/"));
    }

    /**
     *
     * @param value example @String/id
     * @return Id
     */
    public static String getId(String value)
    {
        return value.substring(value.indexOf("/")+1,value.length());
    }

    /**
     *
     * @param node
     * @return isnull
     */
    public static boolean isNull(Node node) {
        if (node.getChildNodes().getLength() == 1) {
            if (node.getFirstChild().getNodeName().equals("null")) {
                return true;
            }
            return false;
        }else {
            return false;
        }
    }

    /**
     *
     * @param declaredField
     * @param o
     * @param value 基本常量。
     * @throws IllegalAccessException
     */
    public static void setFiled(Field declaredField,Object o,String value) throws IllegalAccessException {
        String type = declaredField.getType().toString();

        if (type.endsWith("int")) {
            declaredField.setInt(o, Integer.valueOf(value));
        } else if (type.endsWith("double")) {
            declaredField.setDouble(o, Double.valueOf(value));
        } else if (type.endsWith("char")) {
            declaredField.setChar(o, value.charAt(0));
        } else if (type.endsWith("byte")) {
            declaredField.setByte(o, Byte.valueOf(value));
        } else if (type.endsWith("boolean")) {
            declaredField.setBoolean(o, Boolean.valueOf(value));
        } else if (type.endsWith("float")) {
            declaredField.setFloat(o, Float.valueOf(value));
        } else if (type.endsWith("long")) {
            declaredField.setLong(o, Long.valueOf(value));
        } else if (type.endsWith("short")) {
            declaredField.setShort(o, Short.valueOf(value));
        } else {

            declaredField.set(o, value);
        }
    }

    /**
     *
     * @param node
     * @param type
     * @param configResourcesSection
     * @return
     */
    public static Object convert(Node node,String type,ConfigResourcesSection configResourcesSection){
        Object object=null;
         ConfigConverter resolve=null;
        if (type.equals("string")){
            StringResolve stringResolve=new StringResolve(configResourcesSection);
            resolve= stringResolve;
//            object=   stringResolve.solve(node);
        }else if (type.equals("object")){
            ObjectResolve objectResolve=new ObjectResolve(configResourcesSection);
            resolve= objectResolve;
//            object=objectResolve.solve(node);
        }else if (type.equals("map")){
            MapResolve mapResolve=new MapResolve(configResourcesSection);
            resolve= mapResolve;
//            object=mapResolve.solve(node);
        }else if (type.equals("list")){
            ListResolve listResolve=new ListResolve(configResourcesSection);
            resolve= listResolve;
//            object=listResolve.solve(node);
        }else if (type.equals("int")) {
            IntResolve intResolve=new IntResolve(configResourcesSection);
            resolve= intResolve;
//            object=intResolve.solve(node);
        } else if (type.equals("double")) {
            DoubleResolve doubleResolve=new DoubleResolve(configResourcesSection);
            resolve= doubleResolve;
//            object= doubleResolve.solve(node);
        } else if (type.equals("char")) {
            CharResolve charResolve=new CharResolve(configResourcesSection);
            resolve= charResolve;
//            object= charResolve.solve(node);
        } else if (type.equals("byte")) {
            ByteResolve byteResolve=new ByteResolve(configResourcesSection);
            resolve= byteResolve;
//            object=byteResolve.solve(node);
        } else if (type.equals("boolean")) {
            BooleanResolve booleanResolve=new BooleanResolve(configResourcesSection);
            resolve= booleanResolve;
//            object= booleanResolve.solve(node);
        } else if (type.equals("float")) {
            FloatResolve floatResolve=new FloatResolve(configResourcesSection);
            resolve= floatResolve;
//            object= floatResolve.solve(node);
        } else if (type.equals("long")) {
            LongResolve longResolve=new LongResolve(configResourcesSection);
            resolve= longResolve;
//            object= longResolve.solve(node);
        } else if (type.equals("short")) {
            ShortResolve shortResolve=new ShortResolve(configResourcesSection);
            resolve=shortResolve;
//            object= shortResolve.solve(node);
        } else {
            object=null;
        }
        if (resolve!=null&&node!=null){
            object=resolve.convert(node);
        }
        return object;
    }
    public static    Node getNodeById(String id,ConfigResourcesSection configResourcesSection){
        Node nodeid=null;
//        Node node=configResourcesSection.getRootElement();
        Node node=configResourcesSection.getRootElement();
        NodeList childNodes = node.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++) {
            Node item = childNodes.item(i);
            if (item.getAttributes() != null) {
                if (item.getAttributes().getNamedItem("id")!=null) {
                    String nodeValue = item.getAttributes().getNamedItem("id").getNodeValue();
                    if (nodeValue.equals(id)) {
                        if (nodeid == null) {
                            nodeid = item;
                            Log.i("value",nodeid.getNodeValue()+ ((Element)nodeid).getTextContent());
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
        return nodeid;
    }

}
