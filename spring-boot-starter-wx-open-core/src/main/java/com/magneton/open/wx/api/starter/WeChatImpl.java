package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.core.WeixinEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.CustomMessageInvoker;
import com.magneton.open.wx.api.invoker.MenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.invoker.http.HttpWeixinWeixinInvoker;
import com.magneton.open.wx.api.processor.WxMsgProcessor;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Component
@Configurable
public class WeChatImpl implements WeChat {

    @Autowired
    private WeixinEnvironment weixinEnvironment;
    @Autowired
    private EventProcessor eventProcessor;
    @Autowired
    private WxMsgProcessor wxMsgProcessor;

    @Override
    public WxMsgProcessor getWxMsgProcessor() {
        return wxMsgProcessor;
    }

    @Override
    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    @Override
    public ApiConfig getApiConfig() {
        return weixinEnvironment.getApiConfig();
    }

    @Override
    public AccessTokenInvoker getAccessTokenInvoker() {
        return weixinEnvironment.getAccessTokenInvoker();
    }

    @Override
    public CustomMessageInvoker getCustomMessageInvoker() {
        return weixinEnvironment.getCustomMessageInvoker();
    }

    @Override
    public MenuInvoker getMenuInvoker() {
        return weixinEnvironment.getMenuInvoker();
    }

    @Override
    public UserInfoInvoker getUserInvoker() {
        return weixinEnvironment.getUserInvoker();
    }
}
