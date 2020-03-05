package com.magneton.open.wx.api.open.messagemanagement.event;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 扫描带参数二维码事件
 *
 * 用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ScanEventReuqest extends AbstractEventRequest {

    /**
     * 扫描带参数二维码事件
     * 用户已关注时的事件推送
     *
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    @Override
    public ScanEventReuqest parse(Map<String, String> params) {
        super.parse(params);
        this.eventKey = params.get("EventKey");
        this.ticket = params.get("Ticket");
        return this;
    }
}
