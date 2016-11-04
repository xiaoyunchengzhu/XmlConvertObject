package com.xiaoyunchengzhu.xmlconvertobject.exception;

/**
 * Created by zhangshiyu on 2016/11/2.
 */

public class IdRepeat extends RuntimeException {
    public IdRepeat() {
        super();
    }

    public IdRepeat(String detailMessage) {
        super(detailMessage);

    }

    public IdRepeat(Throwable throwable) {
        super(throwable);
    }

    public IdRepeat(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
