package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @author zhangmingshuang
 * @since 2020/3/7
 */
public class WeMsgCryptWrapper extends WeMsgCrypt {

    private WXBizMsgCrypt wxBizMsgCrypt;

    public WeMsgCryptWrapper(WXBizMsgCrypt wxBizMsgCrypt) {
        this.wxBizMsgCrypt = wxBizMsgCrypt;
    }

    @Override
    public String encryptMsg(String replyMsg,
                             String timeStamp,
                             String nonce) throws AesException {
        return wxBizMsgCrypt.encryptMsg(replyMsg, timeStamp, nonce);
    }

    @Override
    public String decryptMsg(String msgSignature,
                             String timestamp,
                             String nonce,
                             String postData) throws AesException {
        return wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, postData);
    }
}
