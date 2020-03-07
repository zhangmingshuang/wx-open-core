package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagement;
import com.magneton.open.wx.api.open.basic.AccessToken;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.msgprocessor.WeMsgProcessor;
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
    public WeMsgProcessor msgProcessor() {
        return weMsgProcessor;
    }

    @Override
    public EventProcessor eventProcessor() {
        return eventProcessor;
    }

    @Override
    public WeApiConfig apiConfig() {
        return weEnvironment.apiConfig();
    }

    @Override
    public AccessToken accessToken() {
        return weEnvironment.accessToken();
    }

    @Override
    public CustomMenu customMenu() {
        return weEnvironment.customMenu();
    }

    @Override
    public UserInformation userInfomation() {
        return weEnvironment.userInfomation();
    }

    @Override
    public AccountManagement accountManagment() {
        return weEnvironment.accountManagment();
    }

    @Override
    public WeMsgCrypt weMsgCrypt() {
        return weEnvironment.weMsgCrypt();
    }
}
