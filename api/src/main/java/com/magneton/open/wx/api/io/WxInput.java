package com.magneton.open.wx.api.io;

import com.magneton.open.wx.api.processor.WxMsgProcessor;
import com.magneton.open.wx.api.entity.msg.WxMsg;

import java.io.InputStream;
import java.util.Map;

/**
 * 消息分发器，用来将消息接收到的微信消息
 * 并分发到具体的实现类中
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface WxInput {

    void afterDispatcherSet(WxMsgProcessor dispatcher);


    /**
     * 数据解析处理
     *
     * @param inputStream  微信输入流
     * @param msgSignature 签名，如果明文消息则为空
     * @param timestamp    时间戳
     * @param nonce        随机串
     * @return Map key-value
     */
    Map<String, String> decryptMsg(InputStream inputStream,
                                   String msgSignature,
                                   String timestamp,
                                   String nonce);

    /**
     * 数据分发处理
     *
     * @param params 要处理的数据
     * @return 处理结果
     */
    WxMsg distribute(Map<String, String> params);

    /**
     * 数据分发处理
     *
     * @param inputStream  微信输入流
     * @param msgSignature 签名，如果明文消息则为空
     * @param timestamp    时间戳
     * @param nonce        随机串
     * @return {@link WxMsg}
     */
    WxMsg distribute(InputStream inputStream,
                     String msgSignature,
                     String timestamp,
                     String nonce);
}
