package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.invoker.AbstractWeixinWeixinInvoker;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.CustomMessageInvoker;
import com.magneton.open.wx.api.invoker.MenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public class HttpWeixinWeixinInvoker extends AbstractWeixinWeixinInvoker {

    private ApiConfig apiConfig;

    public HttpWeixinWeixinInvoker(List<InvokerLifeCycle> invokerLifeCycles, ApiConfig apiConfig) {
        super(invokerLifeCycles);
        this.apiConfig = apiConfig;
    }

    @Override
    public ApiConfig getApiConfig() {
        return apiConfig;
    }

    @Override
    public AccessTokenInvoker getAccessTokenInvoker() {
        return new HttpAccessTokenInvoker(this);
    }

    @Override
    public CustomMessageInvoker getCustomMessageInvoker() {
        return new HttpCustomMessageInvoker(this);
    }

    @Override
    public MenuInvoker getMenuInvoker() {
        return new HttpMenuInvoker(this);
    }

    @Override
    public UserInfoInvoker getUserInvoker() {
        return new HttpUserInfoInvoker(this);
    }
}
