package com.magneton.open.wx.api.invoker.http;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public class AccessTokenException extends RuntimeException {

    public AccessTokenException(String msg, Throwable e) {
        super(msg, e);
    }

    public AccessTokenException(String msg) {
        super(msg);
    }
}
