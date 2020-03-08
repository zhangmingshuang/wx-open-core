package com.magneton.we.api.http.core;

import com.magneton.we.api.http.util.WebUtils;

import java.io.IOException;

/**
 * @author zhangmingshuang
 * @since 2019/9/9
 */
public class RequestProxy {

    private RequestProxy() {

    }

    public static String doGet(String url) throws IOException {
        //todo monitor
        return WebUtils.doGet(url, null);
    }

    public static String doPost(String url, String body) throws IOException {
        //todo monitor
        return WebUtils.doPost(url, "application/json", body.getBytes(), 2000, 2000);
    }
}
