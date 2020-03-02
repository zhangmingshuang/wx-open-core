package com.magneton.open.wx.api.entity.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2019/9/20
 */
@Setter
@Getter
@ToString
public class AccessTicket {

    /**
     * 公众号的唯一标识
     */
    private String appId;
    /**
     * 生成签名的时间戳
     */
    private long timestamp;
    /**
     * 生成签名的随机串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String signature;
}
