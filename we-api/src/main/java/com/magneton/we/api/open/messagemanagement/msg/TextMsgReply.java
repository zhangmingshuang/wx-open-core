package com.magneton.we.api.open.messagemanagement.msg;

import com.magneton.we.api.util.StringUtil;
import java.nio.charset.Charset;

import lombok.Getter;
import lombok.ToString;

/**
 * 回复文本消息
 * <pre>
 * {@code
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[text]]></MsgType>
 *   <Content><![CDATA[你好]]></Content>
 * </xml>
 * }
 * </pre>
 *
 * <pre>
 *     ToUserName	是	接收方帐号（收到的OpenID）
 *      FromUserName	是	开发者微信号
 *      CreateTime	是	消息创建时间 （整型）
 *      MsgType	是	消息类型，文本为text
 *      Content	是	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示），限制2048
 * </pre>
 * 参考地址：{@code https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543}
 *
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@Getter
@ToString
public class TextMsgReply extends AbstractMsg {

    private String content;

    @Override
    public String getData() {
        return "<xml>" +
            "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>" +
            "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>" +
            "<CreateTime>" + getCreateTime() + "</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[" + getContent() + "]]></Content>" +
            "</xml>";
    }


    public void setContent(String content) {
        int len;
        if (!StringUtil.isEmpty(content)
            && (len = content.getBytes(Charset.defaultCharset()).length) > 2048) {
            throw new RuntimeException("微信消息体context限制2048个字节。当前设置长度为" + len);
        }
        this.content = content;
    }
}
