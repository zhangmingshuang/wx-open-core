package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.open.basic.AccessTokenInvoker;
import com.magneton.open.wx.api.open.messagemanagement.CustomMessageInvoker;
import com.magneton.open.wx.api.invoker.Invoker;
import com.magneton.open.wx.api.open.custommenus.CustomMenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagementInvoker;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信环境，用来提供微个的各种接口调用入口
 *
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public interface WeEnvironment {

    WeApiConfig getWeApiConfig();

    AccessTokenInvoker getAccessTokenInvoker();

    CustomMessageInvoker getCustomMessageInvoker();

    CustomMenuInvoker getMenuInvoker();

    UserInfoInvoker getUserInvoker();

    AccountManagementInvoker getAccountManagementInvoker();

    default List<Invoker> getInvokers() {
        List<Invoker> list = new ArrayList<>(4);
        list.add(getAccessTokenInvoker());
        list.add(getCustomMessageInvoker());
        list.add(getMenuInvoker());
        list.add(getUserInvoker());
        list.add(getAccessTokenInvoker());
        return list;
    }
}
