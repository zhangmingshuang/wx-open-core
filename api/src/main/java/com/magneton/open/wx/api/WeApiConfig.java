package com.magneton.open.wx.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信API配置
 *
 * @author zhangmingshuang
 * @since 2019/9/3
 */
@Setter
@Getter
public class WeApiConfig {

    private String token;

    private String appId;
    private String appSecret;
    private String encodingAESKey;
    /**
     * 标识微信数据是否加密
     */
    private boolean laws;
    /**
     * 自身OpenId
     */
    private String openId;
}
