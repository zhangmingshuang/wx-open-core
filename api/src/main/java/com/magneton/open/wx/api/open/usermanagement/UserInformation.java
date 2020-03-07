package com.magneton.open.wx.api.open.usermanagement;

import com.alibaba.fastjson.JSONObject;
import com.magneton.open.wx.api.open.Invoker;

/**
 * @author zhangmingshuang
 * @since 2019/9/9
 */
public interface UserInformation extends Invoker {

    /**
     * 获取微信公众号的用户信息
     *
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return 获得到的用户信息
     * <pre>
     * subscribe	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * openid	用户的标识，对当前公众号唯一
     * nickname	用户的昵称
     * sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * city	用户所在城市
     * country	用户所在国家
     * province	用户所在省份
     * language	用户的语言，简体中文为zh_CN
     * headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * subscribe_time	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * remark	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * groupid	用户所在的分组ID（兼容旧的用户分组接口）
     * tagid_list	用户被打上的标签ID列表
     * subscribe_scene	返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_ LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     * qr_scene	二维码扫码场景（开发者自定义）
     * qr_scene_str	二维码扫码场景描述（开发者自定义）
     * </pre>
     * 参考地址：{@code https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId}
     */
    JSONObject getOffiaccountUserInfo(String openId);

    /**
     * 1、微信网页授权： 用户同意授权，获取code
     * <p>
     * 以snsapi_base为scope发起的网页授权，
     * 是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。
     * 用户感知的就是直接进入了回调页（往往是业务页面）
     *
     * @param rediectUri 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
     * @param scope      应用授权作用域，
     *                   snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *                   snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
     *                   并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     * @param state      重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return 组装好的可请求的地址
     */
    String webAuth(String rediectUri, String scope, String state);

    /**
     * 2、微信网页授权： 通过code换取网页授权access_token
     * <p>
     * 首先请注意，这里通过code换取的是一个特殊的网页授权access_token, 与基础支持中的access_token（该access_token用于调用其他接口）不同。
     * <p>
     * 公众号可通过下述接口来获取网页授权access_token。 如果网页授权的作用域为snsapi_base， 则本步骤中获取到网页授权access_token的同时，
     * 也获取到了openid，
     * <p>
     * snsapi_base式的网页授权流程即到此为止。
     *
     * @param code 填写第一步获取的code参数 {@link #webAuth}
     * @return AccessToken信息
     * <pre>
     * access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * expires_in	access_token接口调用凭证超时时间，单位（秒）
     * refresh_token	用户刷新access_token
     * openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * scope	用户授权的作用域，使用逗号（,）分隔
     * </pre>
     */
    JSONObject webAuthAccessToken(String code);

    /**
     * 3、微信网页授权：拉取用户信息(需scope为 snsapi_userinfo)
     *
     * @param accessToken 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同。 从{@link
     *                    #webAuthAccessToken}获取
     * @param openid      用户的唯一标识
     * @return 用户信息
     * <pre>
     * openid	用户的唯一标识
     * nickname	用户昵称
     * sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * province	用户个人资料填写的省份
     * city	普通用户个人资料填写的城市
     * country	国家，如中国为CN
     * headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * </pre>
     */
    JSONObject webUserInfo(String accessToken, String openid);

}
