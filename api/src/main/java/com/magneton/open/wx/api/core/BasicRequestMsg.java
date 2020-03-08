package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.util.StringUtil;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString
public class BasicRequestMsg {

    /**
     * 开发者微信号
     */
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long createTime;
    /**
     * 消息类型，文本为text
     */
    private String msgType;

    protected BasicRequestMsg parse(Map<String, String> params) {
        if (params == null) {
            throw new NullPointerException("params 为空");
        }
        this.toUserName = params.get("ToUserName");
        this.fromUserName = params.get("FromUserName");
        this.createTime = StringUtil.getLongVal(params.get("CreateTime"));
        this.msgType = params.get("MsgType");
        return this;
    }
}
