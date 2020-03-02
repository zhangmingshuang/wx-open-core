package com.magneton.open.wx.api.invoker.http;

import com.google.common.hash.Hashing;
import com.magneton.open.wx.api.core.WeixinEnvironment;
import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
import com.magneton.open.wx.api.entity.msg.AccessTicket;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
import com.magneton.open.wx.api.util.StringUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class HttpAccessTokenInvoker extends AbstractorHttpInvoker
    implements AccessTokenInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenInvoker.class);
    private static final String URL_ACCESS_TOKEN
        = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    private static final String WEB_AUTH_URL
        = "https://open.weixin.qq.com/connect/oauth2/authorize" +
        "?appid={}&redirect_uri={}&response_type=code&scope={}&state={}#wechat_redirect";

    private static final String JS_SDK_TICKET
        = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={}&type=jsapi";

    private volatile AccessToken accessToken;
    private JsTicket jsTicket;

    private Lock lock = new ReentrantLock();

    public HttpAccessTokenInvoker(WeixinEnvironment environment) {
        super(environment);
    }


    @AllArgsConstructor
    private static class AccessToken {

        private final String cached;
        private final long expireAt;
    }

    @AllArgsConstructor
    private static class JsTicket {

        private final String cached;
        private final long expireAt;
    }

    @PostConstruct
    public void init() {
        this.getAccessToken();
    }


    @Override
    public AccessTicket getJsSdkTicket(String url, String noncestr) {
        String jsSdkTicket = this.readJsTicket();
        if (StringUtil.isEmpty(jsSdkTicket)) {
            return null;
        }
        long timestamp = System.currentTimeMillis();
        //生成签名参数
        StringBuilder builder = new StringBuilder();
        builder.append("jsapi_ticket=")
               .append(jsSdkTicket)
               .append("&noncestr=").append(noncestr)
               .append("&timestamp=").append(timestamp)
               .append("&url=").append(url);

        String sign = Hashing.sha1()
                             .hashString(builder.toString(), Charset.defaultCharset()).toString();

        AccessTicket accessTicket = new AccessTicket();
        accessTicket.setAppId(getEnvironment().getApiConfig().getAppId());
        accessTicket.setTimestamp(timestamp);
        accessTicket.setNonceStr(noncestr);
        accessTicket.setSignature(sign);
        return accessTicket;
    }

    public String webAuthAccessTokenUrlBuild(String redirectUri, String scope) {
        //don't change this way.
        String url = StringUtil.format(WEB_AUTH_URL,
                                       getEnvironment().getApiConfig().getAppId(),
                                       URLEncoder.encode(redirectUri),
                                       scope,
                                       String.valueOf(System.currentTimeMillis()),
                                       getEnvironment().getApiConfig().getAppSecret());
        return url;
    }

    @Override
    public String webAuthAccessTokenUrlBuild(String redirectUri) {
        return this.webAuthAccessTokenUrlBuild(redirectUri, "snsapi_base");
    }

    @Override
    public String getAccessToken() {
        //don't change this way.
        String url = StringUtil
            .format(URL_ACCESS_TOKEN,
                    getEnvironment().getApiConfig().getAppId(),
                    getEnvironment().getApiConfig().getAppSecret());
        return this.read(url);
    }

    @Override
    public void removeAccessTokenCache() {
        this.accessToken = null;
    }

    protected String readJsTicket() {
        if (this.jsTicket != null
            && System.currentTimeMillis() < this.jsTicket.expireAt) {
            return this.jsTicket.cached;
        }
        String accessToken = this.getAccessToken();
        if (StringUtil.isEmpty(accessToken)) {
            return null;
        }
        String getJsSdkTicketUrl = StringUtil.format(JS_SDK_TICKET, accessToken);
        HttpResponse result = HttpRequest.doRequest(getJsSdkTicketUrl);
        if (!result.isSuccess()) {
            return null;
        }
        String accessTokenVal = result.getString("ticket");
        if (StringUtil.isEmpty(accessTokenVal)) {
            return null;
        }
        int expiresInVal = result.getIntValue("expires_in");
        //正常情况下，过期值为7200秒，即2小时。
        //预留30分钟的过期段
        long expireAt = System.currentTimeMillis() + expiresInVal * 1000 - 30 * 60 * 1000;
        JsTicket token = new JsTicket(accessTokenVal, expireAt);
        this.jsTicket = token;
        return token.cached;
    }

    protected String read(String url) {
        if (this.accessToken != null
            && System.currentTimeMillis() < this.accessToken.expireAt) {
            return this.accessToken.cached;
        }
        lock.lock();
        try {
            if (this.accessToken != null
                && System.currentTimeMillis() < this.accessToken.expireAt) {
                return this.accessToken.cached;
            }
            HttpResponse result = HttpRequest.doRequest(url);
            if (!result.isSuccess()) {
                throw new AccessTokenException("[weixin]getAccessToken fail. url:" + url);
            }
            String accessTokenVal = result.getString("access_token");
            if (StringUtil.isEmpty(accessTokenVal)) {
                throw new AccessTokenException("[weixin]getAccessToken fail.");
            }
            int expiresInVal = result.getIntValue("expires_in");
            //正常情况下，过期值为7200秒，即2小时。
            //预留30分钟的过期段
            long expireAt = System.currentTimeMillis() + expiresInVal * 1000 - 30 * 60 * 1000;
            AccessToken token = new AccessToken(accessTokenVal, expireAt);
            this.accessToken = token;
            return token.cached;
        } finally {
            lock.unlock();
        }
    }

}
