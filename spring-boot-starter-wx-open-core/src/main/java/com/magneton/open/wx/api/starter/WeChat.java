package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.core.WeixinEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.processor.WxMsgProcessor;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public interface WeChat extends WeixinEnvironment {

    WxMsgProcessor getWxMsgProcessor();

    EventProcessor getEventProcessor();
}
