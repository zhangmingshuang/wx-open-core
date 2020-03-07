package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.AbstractWeInvoker;
import com.magneton.open.wx.api.open.basic.AccessToken;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagement;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public class HttpWeInvoker extends AbstractWeInvoker {

    private WeApiConfig weApiConfig;
    private AccessToken accessToken = new HttpAccessToken(this);
    private UserInformation userInformation = new HttpUserInformation(this);
    private AccountManagement accountManagement;
    private CustomMenu customMenu;

    public HttpWeInvoker(List<InvokerLifeCycle> invokerLifeCycles,
                         WeApiConfig weApiConfig,
                         WeMsgCrypt weMsgCrypt) {
        super(invokerLifeCycles, weMsgCrypt);
        this.weApiConfig = weApiConfig;
    }

    @Override
    public WeApiConfig apiConfig() {
        return weApiConfig;
    }

    @Override
    public AccessToken accessToken() {
        return accessToken;
    }

    @Override
    public AccountManagement accountManagment() {
        if (accountManagement == null) {
            accountManagement = new HttpAccountManagement(this);
        }
        return accountManagement;
    }

    @Override
    public CustomMenu customMenu() {
        if (customMenu == null) {
            customMenu = new HttpCustomMenu(this);
        }
        return customMenu;
    }

    @Override
    public UserInformation userInfomation() {
        return userInformation;
    }
}
