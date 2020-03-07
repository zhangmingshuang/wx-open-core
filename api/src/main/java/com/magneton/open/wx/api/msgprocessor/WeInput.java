package com.magneton.open.wx.api.msgprocessor;

import com.magneton.open.wx.api.core.MsgReply;
import java.io.InputStream;
import java.util.Map;

/**
 * 基于事件模式，将收到的信息消息解析之后转发给对象的事件处理器
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface WeInput {

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
    MsgReply distribute(Map<String, String> params);

    /**
     * 数据分发处理
     *
     * @param inputStream  微信输入流
     * @param msgSignature 签名，如果明文消息则为空
     * @param timestamp    时间戳
     * @param nonce        随机串
     * @return {@link WxMsg}
     */
    MsgReply distribute(InputStream inputStream,
                        String msgSignature,
                        String timestamp,
                        String nonce);
}
