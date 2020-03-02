package com.magneton.open.wx.api.invoker.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.hash.Hashing;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.core.WeixinEnvironment;
import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
import com.magneton.open.wx.api.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * 用户信息获取器
 *
 * @author zhangmingshuang
 * @since 2019/9/9
 */
public class HttpUserInfoInvoker extends AbstractorHttpInvoker
    implements UserInfoInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMenuInvoker.class);

    /**
     * 获取微信公众号关注者的信息
     */
    private static final String URL_GET_USER_INFO
        = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={}&openid={}&lang=zh_CN";

    //网页授权获取用户信息
    private static final String URL_WEB_AUTH
        = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect";

    private static final String URL_WEB_AUTH_ACCESS_TOKEN
        = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={}&secret={}&code={}&grant_type=authorization_code";

    private static final String URL_WEB_USER_INFO
        = "https://api.weixin.qq.com/sns/userinfo?access_token={}&openid={}&lang=zh_CN";


    public HttpUserInfoInvoker(WeixinEnvironment environment) {
        super(environment);
    }

    @Override
    public String webAuth(String rediectUri, String scope, String state) {
        if (StringUtil.isEmpty(state)) {
            state = Hashing.sha256()
                           .hashString(rediectUri + "," + scope, Charset.defaultCharset())
                           .toString();
        }
        String url = StringUtil.format(URL_WEB_AUTH,
                                       getEnvironment().getApiConfig().getAppId(),
                                       rediectUri,
                                       scope,
                                       state);
        return url;
    }

    @Override
    public JSONObject webAuthAccessToken(String code) {
        String url = StringUtil.format(URL_WEB_AUTH_ACCESS_TOKEN,
                                       getEnvironment().getApiConfig().getAppId(),
                                       getEnvironment().getApiConfig().getAppSecret(),
                                       code);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse;
    }

    @Override
    public JSONObject webUserInfo(String accessToken, String openid) {
        String url = StringUtil.format(URL_WEB_USER_INFO,
                                       accessToken,
                                       openid);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse;
    }

    @Override
    public JSONObject getOffiaccountUserInfo(String openId) {
        String accessToken = getEnvironment().getAccessTokenInvoker().getAccessToken();

        String url = StringUtil.format(URL_GET_USER_INFO, accessToken, openId);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse;
    }


}
