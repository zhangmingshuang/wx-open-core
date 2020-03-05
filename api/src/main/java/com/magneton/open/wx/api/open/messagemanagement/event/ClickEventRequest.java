package com.magneton.open.wx.api.open.messagemanagement.event;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义菜单事件
 *
 * 点击菜单拉取消息时的事件推送
 *
 * @author zhangmingshuang
 * @since 2020/3/6
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ClickEventRequest extends AbstractEventRequest {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String eventKey;

    @Override
    public ClickEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.eventKey = params.get("EventKey");
        return this;
    }
}
