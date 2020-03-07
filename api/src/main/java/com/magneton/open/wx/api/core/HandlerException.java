package com.magneton.open.wx.api.core;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public class HandlerException extends RuntimeException {

    public HandlerException(String msg) {
        super(msg);
    }

    public HandlerException(Class a, Class b) {
        super(a + "," + b + "处理逻辑冲突");
    }
}
