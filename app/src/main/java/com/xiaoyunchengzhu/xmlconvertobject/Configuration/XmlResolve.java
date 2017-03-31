package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import android.util.Log;
import android.view.View;

import com.xiaoyunchengzhu.XmlConvert;
import com.xiaoyunchengzhu.XmlFind;

/**
 * Created by zhangshiyu on 2017/3/31.
 */

public final class XmlResolve {

    private static String suffix= "_convert";

    /**
     * 配置注解，初始化使用
     * @param goal  参数类
     * @param <T>
     */
    public static<T> void find(T goal){

            try {
                XmlFind gun= (XmlFind) Class.forName(goal.getClass().getName()+ suffix).newInstance();
                gun.find(goal);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    /**
     * 普通使用。
     * @param id
     * @param <T>
     * @return
     */
    public static <T> T find(String id){
       return new ConfigManger().convert(id);
    }
}
