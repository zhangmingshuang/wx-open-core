package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.invoker.AbstractWeInvoker;
import com.magneton.open.wx.api.open.basic.AccessTokenInvoker;
import com.magneton.open.wx.api.open.messagemanagement.CustomMessageInvoker;
import com.magneton.open.wx.api.open.custommenus.CustomMenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagementInvoker;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public class HttpWeInvoker extends AbstractWeInvoker {

    private WeApiConfig weApiConfig;
    private AccessTokenInvoker accessTokenInvoker = new HttpAccessTokenInvoker(this);
    private AccountManagementInvoker accountManagementInvoker;

    public HttpWeInvoker(List<InvokerLifeCycle> invokerLifeCycles, WeApiConfig weApiConfig) {
        super(invokerLifeCycles);
        this.weApiConfig = weApiConfig;
    }

    @Override
    public WeApiConfig getWeApiConfig() {
        return weApiConfig;
    }

    @Override
    public AccessTokenInvoker getAccessTokenInvoker() {
        return accessTokenInvoker;
    }

    @Override
    public AccountManagementInvoker getAccountManagementInvoker() {
        if (accountManagementInvoker == null) {
            accountManagementInvoker = new HttpAccountManagementInvoker(this);
        }
        return accountManagementInvoker;
    }

    @Override
    public CustomMessageInvoker getCustomMessageInvoker() {
        return new HttpCustomMessageInvoker(this);
    }

    @Override
    public CustomMenuInvoker getMenuInvoker() {
        return new HttpCustomMenuInvoker(this);
    }

    @Override
    public UserInfoInvoker getUserInvoker() {
        return new HttpUserInfoInvoker(this);
    }
}
