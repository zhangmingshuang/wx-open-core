package com.magneton.open.wx.api.invoker;

import com.magneton.open.wx.api.entity.msg.AccessTicket;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface AccessTokenInvoker extends Invoker{


    String getAccessToken();

    String webAuthAccessTokenUrlBuild(String redirectUri);

    default AccessTicket getJsSdkTicket(String url) {
        return getJsSdkTicket(url, "888888");
    }

    AccessTicket getJsSdkTicket(String url, String noncestr);

    default void removeAccessTokenCache() {

    }
}
