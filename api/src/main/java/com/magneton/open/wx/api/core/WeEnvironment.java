package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.WeApiConfig;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.basic.AccessToken;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.open.usermanagement.UserInformation;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagement;


/**
 * 微信环境，用来提供微个的各种接口调用入口
 *
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public interface WeEnvironment {

    WeApiConfig apiConfig();

    AccessToken accessToken();

    CustomMenu customMenu();

    UserInformation userInfomation();

    AccountManagement accountManagment();

    WeMsgCrypt weMsgCrypt();
}
