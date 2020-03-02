package com.magneton.open.wx.api.event;

import com.magneton.open.wx.api.entity.msg.WxMsg;

/**
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface EventHandler<T> {
    WxMsg handle(T t);
}
