package com.magneton.open.wx.api.event;

import com.magneton.open.wx.api.event.EventHandler;

/**
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface WxEventProcessor<T> {

    EventHandler<T> getEvent(String event, String eventKey);
}
