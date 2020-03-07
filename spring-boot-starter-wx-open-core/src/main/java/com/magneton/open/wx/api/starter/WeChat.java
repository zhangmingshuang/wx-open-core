package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.msgprocessor.WeMsgProcessor;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public interface WeChat extends WeEnvironment {

    WeMsgProcessor msgProcessor();

    EventProcessor eventProcessor();
}
