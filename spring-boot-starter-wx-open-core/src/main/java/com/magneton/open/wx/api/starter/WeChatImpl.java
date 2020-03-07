package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.open.basic.AccessTokenInvoker;
import com.magneton.open.wx.api.open.messagemanagement.CustomMessageInvoker;
import com.magneton.open.wx.api.open.custommenus.CustomMenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.processor.WeMsgProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Component
@Configurable
public class WeChatImpl implements WeChat {

    @Autowired
    private WeEnvironment weEnvironment;
    @Autowired
    private EventProcessor eventProcessor;
    @Autowired
    private WeMsgProcessor weMsgProcessor;

    @Override
    public WeMsgProcessor getWeMsgProcessor() {
        return weMsgProcessor;
    }

    @Override
    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    @Override
    public WeApiConfig getWeApiConfig() {
        return weEnvironment.getWeApiConfig();
    }

    @Override
    public AccessTokenInvoker getAccessTokenInvoker() {
        return weEnvironment.getAccessTokenInvoker();
    }

    @Override
    public CustomMessageInvoker getCustomMessageInvoker() {
        return weEnvironment.getCustomMessageInvoker();
    }

    @Override
    public CustomMenuInvoker getMenuInvoker() {
        return weEnvironment.getMenuInvoker();
    }

    @Override
    public UserInfoInvoker getUserInvoker() {
        return weEnvironment.getUserInvoker();
    }
}
