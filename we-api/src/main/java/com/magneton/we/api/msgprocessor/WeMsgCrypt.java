package com.magneton.we.api.msgprocessor;

/**
 * @author zhangmingshuang
 * @since 2020/3/7
 */
public abstract class WeMsgCrypt {

    public abstract String encryptMsg(
        String replyMsg, String timestamp, String nonce) throws Throwable;

    public abstract String decryptMsg(
        String msgSignature, String timestamp, String nonce, String postData) throws Throwable;

}
