package com.magneton.we.api.open.usermanagement;

import com.magneton.we.api.open.usermanagement.pojo.UserInfo;

public interface UserInformation {

    /**
     * 获取微信公众号的用户信息
     *
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return 获得到的用户信息
     */
    UserInfo info(String openId);
}
