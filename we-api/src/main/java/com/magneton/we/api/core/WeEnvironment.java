package com.magneton.we.api.core;

import com.magneton.we.api.WeApiConfig;
import com.magneton.we.api.msgprocessor.WeMsgCrypt;
import com.magneton.we.api.open.basic.AccessToken;
import com.magneton.we.api.open.custommenus.CustomMenu;
import com.magneton.we.api.open.intelligentinterface.Ocr;
import com.magneton.we.api.open.oauth.PageAuthorization;
import com.magneton.we.api.open.accountmanagement.AccountManagement;
import com.magneton.we.api.open.usermanagement.UserInformation;
import com.magneton.we.api.open.usermanagement.UserTag;


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

    PageAuthorization pageAuthorization();

    AccountManagement accountManagement();

    UserTag userTag();

    UserInformation userInformation();

    Ocr ocr();

    WeMsgCrypt weMsgCrypt();
}
