package com.magneton.we.api.open.messagemanagement.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 回复图文消息
 * <pre>
 * {@code
 *  <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[news]]></MsgType>
 *   <ArticleCount>1</ArticleCount>
 *   <Articles>
 *     <item>
 *       <Title><![CDATA[title1]]></Title>
 *       <Description><![CDATA[description1]]></Description>
 *       <PicUrl><![CDATA[picurl]]></PicUrl>
 *       <Url><![CDATA[url]]></Url>
 *     </item>
 *   </Articles>
 * </xml>
 * }
 * </pre>
 *
 * <pre>
 *     ToUserName	是	接收方帐号（收到的OpenID）
 *      FromUserName	是	开发者微信号
 *      CreateTime	是	消息创建时间 （整型）
 *      MsgType	是	消息类型，图文为news
 *      ArticleCount	是	图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
 *      Articles	是	图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
 *      Title	是	图文消息标题
 *      Description	是	图文消息描述
 *      PicUrl	是	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
 *      Url	是	点击图文消息跳转链接
 * </pre>
 * 参考地址：{@code https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543}
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
@Setter
@Getter
@ToString
public class NewsMsgReply extends AbstractMsg {

    private int articleCount = 1;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    @Override
    public String getData() {
        return "<xml>" +
            "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>" +
            "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>" +
            "<CreateTime>" + getCreateTime() + "</CreateTime>" +
            "<MsgType><![CDATA[news]]></MsgType>" +
            "<ArticleCount>" + getArticleCount() + "</ArticleCount>" +
            "<Articles>" +
            "<item>" +
            "<Title><![CDATA[" + getTitle() + "]]></Title>" +
            "<Description><![CDATA[" + getDescription() + "]]></Description>" +
            "<PicUrl><![CDATA[" + getPicUrl() + "]]></PicUrl>" +
            "<Url><![CDATA[" + getUrl() + "]]></Url>" +
            "</item>" +
            "</Articles>" +
            "</xml>";
    }
}
