package com.xiaoyunchengzhu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangshiyu on 2017/3/31.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface XmlId {
    String value() default "";
}
