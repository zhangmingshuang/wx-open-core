package com.magneton.we.api.core;


/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class WeMsgConverException extends RuntimeException {

    public WeMsgConverException(String msg) {
        super(msg);
    }

    public WeMsgConverException(String msg, Throwable e) {
        super(msg, e);
    }
}
