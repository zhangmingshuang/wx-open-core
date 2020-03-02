package com.magneton.open.wx.api.handler;

import lombok.ToString;

import java.util.Map;

/**
 * 转换微信公众号推送过来的消息，是一个大而全的实体，则不是根据业务定义
 *
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@ToString
public class HandleMsg {
    private Map<String, String> map;

    public HandleMsg(Map<String, String> map) {
        this.map = map;
    }

    public String getMsgId() {
        return map.get("MsgId");
    }

    public String getEvent() {
        return map.get("EventHandler");
    }

    public String getEventKey() {
        return map.get("EventKey");
    }

    public String getMsgType() {
        return map.get("MsgType");
    }

    public String getMediaId() {
        return map.get("MediaId");
    }

    public String getPicUrl() {
        return map.get("PicUrl");
    }

    public String getCreateTime() {
        return map.get("CreateTime");
    }

    public String getNonce() {
        return map.get("nonce");
    }

    public String getFromUserName() {
        return map.get("FromUserName");
    }

    public String getToUserName() {
        return map.get("ToUserName");
    }

    public String getContent() {
        return map.get("Content");
    }

    public String getLabel() {
        return map.get("Label");
    }

    public String getLocationX() {
        return map.get("Location_X");
    }

    public String getLocationY() {
        return map.get("Location_Y");
    }

    public String getScale() {
        return map.get("Scale");
    }
}
