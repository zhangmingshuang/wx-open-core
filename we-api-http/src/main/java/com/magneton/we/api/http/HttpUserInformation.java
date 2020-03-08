package com.magneton.we.api.http;

import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.http.core.HttpRequest;
import com.magneton.we.api.http.core.HttpResponse;
import com.magneton.we.api.open.usermanagement.UserInformation;
import com.magneton.we.api.open.usermanagement.pojo.UserInfo;
import com.magneton.we.api.util.StringUtil;

public class HttpUserInformation extends AbstractorHttpWe
        implements UserInformation {

    /**
     * 获取微信公众号关注者的信息
     */
    private static final String INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={}&openid={}&lang=zh_CN";

    public HttpUserInformation(WeEnvironment environment) {
        super(environment);
    }

    @Override
    public UserInfo info(String openId) {
        String accessToken = getWeEnvironment().accessToken().getAccessToken();

        String url = StringUtil.format(INFO, accessToken, openId);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse.toJavaObject(UserInfo.class);
    }
}
