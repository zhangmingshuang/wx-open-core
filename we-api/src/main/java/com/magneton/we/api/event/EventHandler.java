package com.magneton.we.api.event;

import com.magneton.we.api.core.MsgReply;

/**
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface EventHandler<T> {

    MsgReply handle(T t);
}
