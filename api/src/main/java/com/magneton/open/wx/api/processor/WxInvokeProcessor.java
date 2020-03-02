package com.magneton.open.wx.api.processor;

import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.invoker.MenuInvoker;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface WxInvokeProcessor {

    ApiConfig apiConfig();

    AccessTokenInvoker accessTokenInvoker();

    MenuInvoker menuInvoker();

    UserInfoInvoker userInfoInvoker();
}
