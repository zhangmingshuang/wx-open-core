package com.magneton.open.wx.api.core;


/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class WxMsgConverException extends RuntimeException {

    public WxMsgConverException(String msg) {
        super(msg);
    }

    public WxMsgConverException(String msg, Throwable e) {
        super(msg, e);
    }
}
