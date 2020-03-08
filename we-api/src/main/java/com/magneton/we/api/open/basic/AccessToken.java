package com.magneton.we.api.open.basic;

import com.magneton.we.api.open.We;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface AccessToken extends We {


    String getAccessToken();

    String webAuthAccessTokenUrlBuild(String redirectUri);

    default AccessTicketResponse getJsSdkTicket(String url) {
        return getJsSdkTicket(url, "888888");
    }

    AccessTicketResponse getJsSdkTicket(String url, String noncestr);

    default void removeAccessTokenCache() {

    }
}
