package com.magneton.open.wx.api.open.messagemanagement.event;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 关注事件
 *
 * 扫描带参数二维码事件 用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString(callSuper = true)
public class SubscribeEventRequest extends AbstractEventRequest {

    /**
     * 扫描带参数二维码事件
     * 用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者
     *
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    @Override
    public SubscribeEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.eventKey = params.get("EventKey");
        this.ticket = params.get("Ticket");
        return this;
    }
}
