package com.magneton.open.wx.api.msgprocessor;


import com.magneton.open.wx.api.WeApiConfig;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 消息分发器
 *
 * @author zhangmingshuang
 * @see WeMsgProcessorImpl
 * @since 2019/9/4
 */
public interface WeMsgProcessor {

    /**
     * 微信消息输入源
     *
     * @return 输入源
     */
    WeInput input();

    /**
     * 微信消息输出源
     *
     * @return 输出源
     */
    WeOutput output();

}
