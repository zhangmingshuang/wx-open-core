package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.CustomMessageInvoker;
import com.magneton.open.wx.api.invoker.Invoker;
import com.magneton.open.wx.api.invoker.MenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信环境，用来提供微个的各种接口调用入口
 *
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public interface WeixinEnvironment {

    ApiConfig getApiConfig();

    AccessTokenInvoker getAccessTokenInvoker();

    CustomMessageInvoker getCustomMessageInvoker();

    MenuInvoker getMenuInvoker();

    UserInfoInvoker getUserInvoker();

    default List<Invoker> getInvokers() {
        List<Invoker> list = new ArrayList<>(4);
        list.add(getAccessTokenInvoker());
        list.add(getCustomMessageInvoker());
        list.add(getMenuInvoker());
        list.add(getUserInvoker());
        return list;
    }
}
