package com.xiaoyunchengzhu.xmlconvertobject.Configuration;


import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by zhangshiyu on 2016/4/13.
 */
public class ConfigResourcesSection {


    private InputStream inputStream;
    public ConfigResourcesSection(boolean isAssets)
    {
        if (isAssets)
        {
            inputStream=getUrlStringStream();
        }else {
        }
    }

    /**
     * assets's resource
     * @return
     */
    private  InputStream getUrlStringStream()
    {
        String path="/assets/Configuration/configResource.xml";
        InputStream inStream = ConfigResourcesSection.class.getResourceAsStream(path);
        return inStream;
    }

    /**
     * you can user-defined resouce'path.in sdcard.
     * @param context
     * @return
     */
    private  InputStream getUrlStringStream(Context context)
    {
        String path=context.getFilesDir().getAbsolutePath()+"/configResource.xml";
        File file=new File(path);
        InputStream inputStream=null;
        try {
             inputStream=new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    /**
     *
     * listItem
     * @param urlNode
     * @return
     */
    public      Object getValueFromUrlNode(Node urlNode,ConfigConverter configConverter)
    {
        Object o=null;
        try {
            Node type1 = urlNode.getAttributes().getNamedItem("Type");
            if (type1== null ) {
                String className = urlNode.getAttributes().getNamedItem("Class").getNodeValue();

                Class<?> aClass = Class.forName(className);
                o = aClass.newInstance();

                for (int i = 0; i < urlNode.getAttributes().getLength(); i++) {
                    String nodeName = urlNode.getAttributes().item(i).getNodeName();
                    if (!nodeName.equals("Id") && !nodeName.equals("Class")&&!nodeName.equals("propertyName")) {
                        Field declaredField = aClass.getDeclaredField(nodeName);
                        declaredField.setAccessible(true);
                        String value = urlNode.getAttributes().item(i).getNodeValue();
                        if (!value.startsWith("@")) {
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
                        }else {

                            declaredField.set(o,configConverter.convert(getId(value),getType(value)));
                        }
                    }
                }
                NodeList childNodes = urlNode.getChildNodes();
                for (int i=0;i<childNodes.getLength();i++)
                {
                    if (childNodes.item(i).getNodeName().equals("add"))
                    {
                        Node propertyName = childNodes.item(i).getAttributes().getNamedItem("propertyName");
                        Node type = childNodes.item(i).getAttributes().getNamedItem("Type");
                        Field declaredField = aClass.getDeclaredField(propertyName.getNodeValue());
                        declaredField.setAccessible(true);
                        if (type!=null) {
                            declaredField.set(o, configConverter.convert(childNodes.item(i).getAttributes().getNamedItem("Id").getNodeValue(), type.getNodeValue()));
                        }else {
                            declaredField.set(o,getValueFromUrlNode(childNodes.item(i), configConverter));
                        }
                    }

                }

                }else {
                o= configConverter.convert(urlNode.getAttributes().getNamedItem("Id").getNodeValue(),urlNode.getAttributes().getNamedItem("Type").getNodeValue());
            }

        }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(InstantiationException e){
                e.printStackTrace();
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(NoSuchFieldException e){
                e.printStackTrace();
            }

        return  o;
    }



    /**
     * root element
     * @return
     */
    public  Element getRootElement()
    {

        Document document=null;
        //DOM文件创建工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DOM创建对象
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();

        //获取XML的DOM
         document = builder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.getDocumentElement();
    }

    /**
     *
     * @param value example @String/id
     * @return   Type
     */
    private String getType(String value)
    {

        return value.substring(1,value.indexOf("/"));
    }

    /**
     *
     * @param value example @String/id
     * @return Id
     */
    private String getId(String value)
    {
        return value.substring(value.indexOf("/")+1,value.length());
    }

}
