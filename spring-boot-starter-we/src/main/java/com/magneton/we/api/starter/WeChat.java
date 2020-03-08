package com.magneton.we.api.starter;


import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.event.EventProcessor;
import com.magneton.we.api.msgprocessor.WeMsgProcessor;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public interface WeChat extends WeEnvironment {
    /**
     * 消息处理器
     *
     * @return
     */
    WeMsgProcessor msgProcessor();

    /**
     * 事件处理器
     *
     * @return
     */
    EventProcessor eventProcessor();
}
