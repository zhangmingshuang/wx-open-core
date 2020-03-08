package com.magneton.open.wx.api.open.oauth;

import com.magneton.open.wx.api.open.We;
import com.magneton.open.wx.api.open.oauth.pojo.OAuthAccessToken;
import com.magneton.open.wx.api.open.oauth.pojo.OAuthUserInfo;

/**
 * 微信网页管理-网页授权
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
 * <p>
 * 1 第一步：用户同意授权，获取code
 * 2 第二步：通过code换取网页授权access_token
 * 3 第三步：刷新access_token（如果需要）
 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 *
 * @author zhangmingshuang
 * @since 2019/9/9
 */
public interface PageAuthorization extends We {

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
    String authorize(String rediectUri, String scope, String state);

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
     */
    OAuthAccessToken accessToken(String code);

    /**
     * 3、微信网页授权：拉取用户信息(需scope为 snsapi_userinfo)
     *
     * @param accessToken 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同。 从{@link
     *                    #webAuthAccessToken}获取
     * @param openId      用户的唯一标识
     * @return 用户信息
     */
    OAuthUserInfo userInfo(String accessToken, String openId);

}
