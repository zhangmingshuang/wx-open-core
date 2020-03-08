package com.magneton.we.api.http;

import com.google.common.hash.Hashing;
import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.http.core.HttpRequest;
import com.magneton.we.api.http.core.HttpResponse;
import com.magneton.we.api.open.oauth.PageAuthorization;
import com.magneton.we.api.open.oauth.pojo.OAuthAccessToken;
import com.magneton.we.api.open.oauth.pojo.OAuthUserInfo;
import com.magneton.we.api.util.StringUtil;

import java.nio.charset.Charset;

/**
 * 用户信息获取器
 *
 * @author zhangmingshuang
 * @since 2019/9/9
 */
public class HttpPageAuthorization extends AbstractorHttpWe
        implements PageAuthorization {

    //网页授权获取用户信息
    private static final String AUTHORIZE
            = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect";

    private static final String ACCESS_TOKEN
            = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={}&secret={}&code={}&grant_type=authorization_code";

    private static final String USER_INFO
            = "https://api.weixin.qq.com/sns/userinfo?access_token={}&openid={}&lang=zh_CN";


    public HttpPageAuthorization(WeEnvironment environment) {
        super(environment);
    }

    @Override
    public String authorize(String rediectUri, String scope, String state) {
        if (StringUtil.isEmpty(state)) {
            state = Hashing.sha256()
                    .hashString(rediectUri + "," + scope, Charset.defaultCharset())
                    .toString();
        }
        String url = StringUtil.format(AUTHORIZE,
                getWeEnvironment().apiConfig().getAppId(),
                rediectUri,
                scope,
                state);
        return url;
    }

    @Override
    public OAuthAccessToken accessToken(String code) {
        String url = StringUtil.format(ACCESS_TOKEN,
                getWeEnvironment().apiConfig().getAppId(),
                getWeEnvironment().apiConfig().getAppSecret(),
                code);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse.toJavaObject(OAuthAccessToken.class);
    }

    @Override
    public OAuthUserInfo userInfo(String accessToken, String openid) {
        String url = StringUtil.format(USER_INFO,
                accessToken,
                openid);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse.toJavaObject(OAuthUserInfo.class);
    }

}
