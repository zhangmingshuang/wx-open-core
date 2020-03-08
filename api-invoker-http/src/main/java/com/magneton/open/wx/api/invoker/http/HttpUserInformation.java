package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.open.usermanagement.pojo.UserInfo;
import com.magneton.open.wx.api.util.StringUtil;

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
        String accessToken = getEnvironment().accessToken().getAccessToken();

        String url = StringUtil.format(INFO, accessToken, openId);
        HttpResponse httpResponse = HttpRequest.doRequest(url);
        if (!httpResponse.isSuccess()) {
            return null;
        }
        return httpResponse.toJavaObject(UserInfo.class);
    }
}
