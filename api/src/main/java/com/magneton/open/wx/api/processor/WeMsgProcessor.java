package com.magneton.open.wx.api.processor;


import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.io.WeInput;
import com.magneton.open.wx.api.io.WeOutput;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 消息分发器
 *
 * @author zhangmingshuang
 * @see WeMsgProcessorImpl
 * @since 2019/9/4
 */
public interface WeMsgProcessor {

    WeInput input();

    WeOutput output();

    WXBizMsgCrypt wxBizMsgCrypt();

    WeApiConfig apiConfig();
}
