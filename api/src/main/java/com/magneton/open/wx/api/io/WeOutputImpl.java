package com.magneton.open.wx.api.io;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.magneton.open.wx.api.core.WeMsgConverException;
import com.magneton.open.wx.api.processor.WeMsgProcessor;
import com.magneton.open.wx.api.entity.msg.WxMsg;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class WeOutputImpl implements WeOutput {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeOutputImpl.class);

    private WeMsgProcessor dispatcher;
    private WXBizMsgCrypt wxBizMsgCrypt;

    @Override
    public void afterDispatcherSet(WeMsgProcessor dispatcher) {
        this.dispatcher = dispatcher;
        this.wxBizMsgCrypt = dispatcher.wxBizMsgCrypt();
    }

    @Override
    public String encryptSyncMsg(WxMsg msg) {
        if (msg == null) {
            return null;
        }
        if (wxBizMsgCrypt == null) {
            return msg.toXml();
        }
        try {
            return wxBizMsgCrypt.encryptMsg(
                msg.toXml(), msg.getCreateTime(), msg.getNonce());
        } catch (AesException e) {
            throw new WeMsgConverException("[WxOutput]加密微信公众号消息异常", e);
        }
    }

    @Override
    public String interfaceValidate(String timestamp, String nonce, String echostr, String signature) {
        String token = dispatcher.apiConfig().getToken();

        String signatureText = timestamp + nonce + token;
        HashCode hashCode = Hashing.sha1().hashString(signatureText, Charset.defaultCharset());
        String sha1Signature = hashCode.toString();
        if (!sha1Signature.equals(signature)) {
            LOGGER.error("[weixin]actionValidate。timestamp=[{}]," +
                             "nonce=[{}],signature=[{}],token=[{}]。签名有误，签名串：[{}]",
                         timestamp, nonce, signature, token, signatureText);
            return "error";
        }
        return echostr;
    }
}
