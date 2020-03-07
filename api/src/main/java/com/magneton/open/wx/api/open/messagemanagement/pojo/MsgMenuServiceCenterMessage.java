package com.magneton.open.wx.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送菜单消息
 *
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html#7
 *
 * <pre>
 *     {@code {
 *   "touser": "OPENID"
 *   "msgtype": "msgmenu",
 *   "msgmenu": {
 *     "head_content": "您对本次服务是否满意呢? "
 *     "list": [
 *       {
 *         "id": "101",
 *         "content": "满意"
 *       },
 *       {
 *         "id": "102",
 *         "content": "不满意"
 *       }
 *     ],
 *     "tail_content": "欢迎再次光临"
 *   }
 * }}
 * </pre>
 * 按照上述例子，用户会看到这样的菜单消息：
 *
 * “您对本次服务是否满意呢？
 *
 * 满意
 *
 * 不满意”
 *
 * 其中，“满意”和“不满意”是可点击的，当用户点击后，微信会发送一条XML消息到开发者服务器，格式如下：
 *
 * <xml>
 * <ToUserName><![CDATA[ToUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>1500000000</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[满意]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * <bizmsgmenuid>101</bizmsgmenuid>
 * </xml>
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class MsgMenuServiceCenterMessage extends AbstractServiceCenterMessage {

    @JSONField(name = "msgmenu")
    private MsgMenu msgMenu;

    @Override
    public String getMsgType() {
        return "msgmenu";
    }

    @Setter
    @Getter
    @ToString
    public static class MsgMenu {

        @JSONField(name = "head_content")
        private String headContent;
        @JSONField(name = "tail_content")
        private String tailContent;
        private List<Msg> list;
    }

    @Setter
    @Getter
    @ToString
    public static class Msg {

        private String id;
        private String content;
    }
}
