package com.magneton.open.wx.api.processor;


import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.io.WxInput;
import com.magneton.open.wx.api.io.WxOutput;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 消息分发器
 *
 * @author zhangmingshuang
 * @see WxWxMsgProcessorImpl
 * @since 2019/9/4
 */
public interface WxMsgProcessor {

    WxInput input();

    WxOutput output();

    WXBizMsgCrypt wxBizMsgCrypt();

    ApiConfig apiConfig();
}
