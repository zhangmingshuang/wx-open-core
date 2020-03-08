package com.magneton.we.api.msgprocessor.handler;


import com.magneton.we.api.core.MsgReply;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public interface WeMsgHandler<T extends WeMsgParser> {

    /**
     * 微信消息处理
     *
     * @param msg 微信通知的消息
     * @return 要同步响应给微信的消息
     */
    MsgReply handle(T msg);
}
