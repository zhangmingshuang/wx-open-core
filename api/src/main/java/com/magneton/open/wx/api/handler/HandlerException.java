package com.magneton.open.wx.api.handler;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public class HandlerException extends RuntimeException {

    public HandlerException(Class a, Class b) {
        super(a + "," + b + "处理逻辑冲突");
    }
}
