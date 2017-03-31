package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import android.view.View;

import com.xiaoyunchengzhu.XmlConvert;
import com.xiaoyunchengzhu.XmlFind;

/**
 * Created by zhangshiyu on 2017/3/31.
 */

public final class XmlResolve {


    public static<T> void find(T goal){

            try {
                XmlFind gun= (XmlFind) Class.forName(goal.getClass().getName()+ XmlConvert.suffix).newInstance();
                gun.find(goal);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    public static <T> T find(String id){
       return new ConfigManger().convert(id);
    }
}
