package com.magneton.open.wx.api.core;

/**
 * 微信调用平台，平台同步响应给微信的接口标识
 *
 * @author zhangmingshuang
 * @since 2019/11/6
 */
public interface MsgReply {

    String getData();

    String getTimeStamp();

    String getNonce();
}
