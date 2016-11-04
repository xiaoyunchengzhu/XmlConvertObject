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



    private File file;

    public ConfigResourcesSection(){

    }
    public ConfigResourcesSection(File file)
    {
        this.file=file;

    }

    /**
     * assets's resource
     * @return
     */
    private  InputStream getUrlStringStream()
    {
        String path="/assets/configResource.xml";
        InputStream inStream = ConfigResourcesSection.class.getResourceAsStream(path);
        return inStream;
    }

    /**
     * you can user-defined resouce'path.in sdcard.
     * @param file
     * @return
     */
    private  InputStream getUrlStringStream(File file)
    {
//        String path=context.getFilesDir().getAbsolutePath()+"/configResource.xml";
//        File file=new File(path);
        InputStream inputStream=null;
        try {
             inputStream=new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
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
            InputStream inputStream=(file==null?getUrlStringStream():getUrlStringStream(file));
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


}
