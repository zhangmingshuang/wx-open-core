package com.magneton.we.api.msgprocessor;

import com.magneton.we.api.core.MsgReply;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface WeOutput {

    String encryptSyncMsg(MsgReply msg);

    /**
     * 接口验证逻辑，用来处理微信公众号开发者配置地址时验证
     *
     * @param timestamp 微信参数
     * @param nonce     微信参数
     * @param echostr   微信参数
     * @param signature 微信参数
     * @return 响应参数
     */
    String interfaceValidate(String timestamp, String nonce, String echostr, String signature);
}
