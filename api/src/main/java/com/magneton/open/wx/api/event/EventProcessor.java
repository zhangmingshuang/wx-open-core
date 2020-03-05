package com.magneton.open.wx.api.event;


/**
 * 内部事件处理器
 *
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface EventProcessor<T> {

    EventHandler<T> getEvent(String event, String eventKey);
}
