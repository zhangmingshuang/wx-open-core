package com.magneton.we.api.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * mapping 微信用户信息
 *
 * @author zhangmingshuang
 * @since 2019/9/9
 */
@Setter
@Getter
@ToString
public class WxUserInfo {

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private int subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    @JSONField(name = "openid")
    private String openId;
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int sex;
    private String country;
    private String province;
    private String city;
    private String language;
    private String headimgurl;
    @JSONField(name = "subscribe_time")
    private long subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
    /**
     * 返回用户关注的渠道来源，
     * ADD_SCENE_SEARCH 公众号搜索，
     * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
     * ADD_SCENE_PROFILE_CARD 名片分享，
     * ADD_SCENE_QR_CODE 扫描二维码，
     * ADD_SCENE_PROFILE_ LINK 图文页内名称点击，
     * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
     * ADD_SCENE_PAID 支付后关注，
     * ADD_SCENE_OTHERS 其他
     */
    @JSONField(name = "subscribe_scene")
    private String subscribeScene;
    @JSONField(name = "qr_scene")
    private String qrScene;
    @JSONField(name = "qr_scene_str")
    private String qrSceneStr;
}
