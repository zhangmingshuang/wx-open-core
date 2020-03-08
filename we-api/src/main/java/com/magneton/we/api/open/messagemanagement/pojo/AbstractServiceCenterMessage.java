package com.magneton.we.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@ToString
public abstract class AbstractServiceCenterMessage {

    @JSONField(name = "touser")
    @Setter
    @Getter
    private String toUser;

    @JSONField(name = "msgtype")
    @Setter
    private String msgType;

    public abstract String getMsgType();
}
