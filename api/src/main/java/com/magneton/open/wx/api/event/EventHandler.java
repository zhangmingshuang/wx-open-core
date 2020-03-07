package com.magneton.open.wx.api.event;

import com.magneton.open.wx.api.core.MsgReply;

/**
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface EventHandler<T> {

    MsgReply handle(T t);
}
