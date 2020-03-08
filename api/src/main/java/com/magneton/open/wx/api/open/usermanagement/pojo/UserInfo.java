package com.magneton.open.wx.api.open.usermanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 获取用户基本信息（包括UnionID机制）
 * <p>
 * 开发者可通过OpenID来获取用户基本信息。
 */
@Setter
@Getter
@ToString
public class UserInfo {
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private int subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    @JSONField(name = "openid")
    private String openId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int sex;
    /**
     * 用户所在城市,如 厦门
     */
    private String city;
    /**
     * 用户所在国家 如 中国
     */
    private String country;
    /**
     * 用户所在省份 如 福建
     */
    private String province;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 用户头像，最后一个数值代表正方形头像大小
     * （有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * 如：http://thirdwx.qlogo.cn/mmopen/
     * g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQ
     * KkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0
     */
    @JSONField(name = "headimgurl")
    private String headImgUrl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * 如 1382694957
     */
    @JSONField(name = "subscribe_time")
    private long subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    @JSONField(name = "unionId")
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    @JSONField(name = "groupid")
    private int groupId;
    /**
     * 用户被打上的标签ID列表
     */
    @JSONField(name = "tagid_list")
    private List<Long> tagidList;
    /**
     * 返回用户关注的渠道来源，
     *
     * @see WeSubscribeScene
     */
    @JSONField(name = "subscribe_scene")
    private String subscribeScene;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    @JSONField(name = "qr_scene")
    private String qrScene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    @JSONField(name = "qr_scene_str")
    private String qrSceneStr;
}
