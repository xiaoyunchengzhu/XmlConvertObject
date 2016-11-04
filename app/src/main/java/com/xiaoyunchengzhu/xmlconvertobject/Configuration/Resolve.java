package com.xiaoyunchengzhu.xmlconvertobject.Configuration;

import org.w3c.dom.Node;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public interface Resolve<T> {

    T solve(Node urlNode);
}
