package com.magneton.open.wx.api.open.messagemanagement.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 回复视频消息
 * <pre>
 * {@code
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[video]]></MsgType>
 *   <Video>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *     <Title><![CDATA[title]]></Title>
 *     <Description><![CDATA[description]]></Description>
 *   </Video>
 * </xml>
 * }
 * </pre>
 *
 * <pre>
 *     ToUserName	是	接收方帐号（收到的OpenID）
 *      FromUserName	是	开发者微信号
 *      CreateTime	是	消息创建时间 （整型）
 *      MsgType	是	消息类型，视频为video
 *      MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
 *      Title	否	视频消息的标题
 *      Description	否	视频消息的描述
 * </pre>
 * 参考地址：{@code https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543}
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
@Setter
@Getter
@ToString
public class VideoMsgReply extends AbstractMsg {

    private String mediaId;
    private String title;
    private String description;

    @Override
    public String getData() {
        return "<xml>" +
                "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>" +
                "<CreateTime>" + getCreateTime() + "</CreateTime>" +
                "<MsgType><![CDATA[video]]></MsgType>" +
                "<Video>" +
                "<MediaId><![CDATA[" + getMediaId() + "]]></MediaId>" +
                "<Title><![CDATA[" + getTitle() + "]]></Title>" +
                "<Description><![CDATA[" + getDescription() + "]]></Description>" +
                "</Video>" +
                "</xml>";
    }
}
