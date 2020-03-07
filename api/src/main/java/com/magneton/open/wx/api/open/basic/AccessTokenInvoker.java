package com.magneton.open.wx.api.open.basic;

import com.magneton.open.wx.api.invoker.Invoker;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface AccessTokenInvoker extends Invoker {


    String getAccessToken();

    String webAuthAccessTokenUrlBuild(String redirectUri);

    default AccessTicketResponse getJsSdkTicket(String url) {
        return getJsSdkTicket(url, "888888");
    }

    AccessTicketResponse getJsSdkTicket(String url, String noncestr);

    default void removeAccessTokenCache() {

    }
}