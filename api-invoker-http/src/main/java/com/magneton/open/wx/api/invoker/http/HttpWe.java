package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.AbstractWe;
import com.magneton.open.wx.api.open.basic.AccessToken;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.open.intelligentinterface.Ocr;
import com.magneton.open.wx.api.open.oauth.PageAuthorization;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagement;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.open.usermanagement.UserTag;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public class HttpWe extends AbstractWe {

    private WeApiConfig weApiConfig;
    private AccessToken accessToken = new HttpAccessToken(this);
    private PageAuthorization pageAuthorization = new HttpPageAuthorization(this);
    private UserInformation userInformation = new HttpUserInformation(this);
    private AccountManagement accountManagement;
    private CustomMenu customMenu;
    private UserTag userTag;
    private Ocr ocr;

    public HttpWe(List<InvokerLifeCycle> invokerLifeCycles,
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
    public AccountManagement accountManagement() {
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
    public PageAuthorization pageAuthorization() {
        return pageAuthorization;
    }

    @Override
    public UserInformation userInformation() {
        return userInformation;
    }

    @Override
    public Ocr ocr() {
        if(ocr == null){
            ocr = new HttpOcr(this);
        }
        return ocr;
    }

    @Override
    public UserTag userTag() {
        if (userTag == null) {
            userTag = new HttpUserTag(this);
        }
        return userTag;
    }
}
