package com.magneton.open.wx.api.handler;


import com.magneton.open.wx.api.entity.msg.WxMsg;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public interface MsgHandler {
    /**
     * 微信消息处理
     *
     * @param msg 微信通知的消息
     * @return 要同步响应给微信的消息
     */
    WxMsg handle(HandleMsg msg);
}
