package com.magneton.we.api.open.messagemanagement.event;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义菜单事件
 *
 * 点击菜单跳转链接时的事件推送
 *
 * @author zhangmingshuang
 * @since 2020/3/6
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ViewEventRequest extends AbstractEventRequest {

    /**
     * 事件KEY值，设置的跳转URL
     */
    private String eventKey;

    @Override
    public ViewEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.eventKey = params.get("EventKey");
        return this;
    }
}
