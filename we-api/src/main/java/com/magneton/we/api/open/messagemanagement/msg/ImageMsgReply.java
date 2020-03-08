package com.magneton.we.api.open.messagemanagement.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 回复图片消息
 *
 * <pre>
 * {@code
 * <xml>
 *      <ToUserName><![CDATA[toUser]]></ToUserName>
 *      <FromUserName><![CDATA[fromUser]]></FromUserName>
 *      <CreateTime>12345678</CreateTime>
 *      <MsgType><![CDATA[image]]></MsgType>
 *      <Image>
 *          <MediaId><![CDATA[media_id]]></MediaId>
 *      </Image>
 * </xml>
 * }
 * </pre>
 *
 * <pre>
 *     ToUserName	是	接收方帐号（收到的OpenID）
 *      FromUserName	是	开发者微信号
 *      CreateTime	是	消息创建时间 （整型）
 *      MsgType	是	消息类型，图片为image
 *      MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id。
 * </pre>
 * 参考地址：{@code https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543}
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
@Setter
@Getter
@ToString
public class ImageMsgReply extends AbstractMsg {

    private String mediaId;

    @Override
    public String getData() {
        return "<xml>" +
            "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>" +
            "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>" +
            "<CreateTime>" + getCreateTime() + "</CreateTime>" +
            "<MsgType><![CDATA[image]]></MsgType>" +
            "<Image>" +
            "<MediaId><![CDATA[" + getMediaId() + "]]></MediaId>" +
            "</Image>" +
            "</xml>";
    }
}
