package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagement;
import com.magneton.open.wx.api.open.basic.AccessToken;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.open.oauth.PageAuthorization;
import com.magneton.open.wx.api.msgprocessor.WeMsgProcessor;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.open.usermanagement.UserTag;
import lombok.experimental.Delegate;
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
    @Delegate
    private WeEnvironment weEnvironment;
    @Autowired
    private EventProcessor eventProcessor;
    @Autowired
    private WeMsgProcessor weMsgProcessor;

    @Override
    public WeMsgProcessor msgProcessor() {
        return weMsgProcessor;
    }

    @Override
    public EventProcessor eventProcessor() {
        return eventProcessor;
    }

}
