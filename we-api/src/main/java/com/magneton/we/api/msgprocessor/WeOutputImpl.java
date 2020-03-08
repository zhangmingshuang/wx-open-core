package com.magneton.we.api.msgprocessor;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.core.WeMsgConverException;
import com.magneton.we.api.core.MsgReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class WeOutputImpl implements WeOutput {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeOutputImpl.class);

    private WeEnvironment weEnvironment;

    public WeOutputImpl(WeEnvironment weEnvironment) {
        this.weEnvironment = weEnvironment;
    }

    @Override
    public String encryptSyncMsg(MsgReply msg) {
        if (msg == null) {
            return null;
        }
        if (weEnvironment.weMsgCrypt() == null) {
            return msg.getData();
        }
        try {
            return weEnvironment.weMsgCrypt().encryptMsg(
                msg.getData(), msg.getTimeStamp(), msg.getNonce());
        } catch (Throwable e) {
            throw new WeMsgConverException("[WxOutput]加密微信公众号消息异常", e);
        }
    }

    @Override
    public String interfaceValidate(String timestamp, String nonce,
                                    String echostr, String signature) {
        String token = weEnvironment.accessToken().getAccessToken();

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
