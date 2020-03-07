package com.magneton.open.wx.api.open.messagemanagement.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 回复语音消息
 * <pre>
 * {@code
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[voice]]></MsgType>
 *   <Voice>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *   </Voice>
 * </xml>
 * }
 * </pre>
 *
 * <pre>
 *      ToUserName	是	接收方帐号（收到的OpenID）
 *      FromUserName	是	开发者微信号
 *      CreateTime	是	消息创建时间戳 （整型）
 *      MsgType	是	消息类型，语音为voice
 *      MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
 * </pre>
 * 参考地址：{@code https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543}
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
@Setter
@Getter
@ToString
public class VoiceMsgReply extends AbstractMsg {

    private String mediaId;

    @Override
    public String getData() {
        return "<xml>" +
            "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>" +
            "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>" +
            "<CreateTime>" + getCreateTime() + "</CreateTime>" +
            "<MsgType><![CDATA[voice]]></MsgType>" +
            "<Voice>" +
            "<MediaId><![CDATA[" + getMediaId() + "]]></MediaId>" +
            "</Voice>" +
            "</xml>";
    }
}
