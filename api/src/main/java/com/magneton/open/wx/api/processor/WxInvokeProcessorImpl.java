//package com.magneton.open.wx.api.processor;
//
//import com.magneton.open.wx.api.ApiConfig;
//import com.magneton.open.wx.api.open.common.AccessTokenInvoker;
//import com.magneton.open.wx.api.invoker.UserInfoInvoker;
//import com.magneton.open.wx.api.open.custommenus.MenuInvoker;
//import lombok.Builder;
//
///**
// * 微信调用分发器
// *
// * @author zhangmingshuang
// * @since 2019/9/5
// */
//@Builder
//public class WxInvokeProcessorImpl implements WxInvokeProcessor {
//
//    private ApiConfig apiConfig;
//    private AccessTokenInvoker accessTokenInvoker;
//    private MenuInvoker menuInvoker;
//    private UserInfoInvoker userInfoInvoker;
//
//    @Override
//    public ApiConfig apiConfig() {
//        return apiConfig;
//    }
//
//    @Override
//    public AccessTokenInvoker accessTokenInvoker() {
//        return accessTokenInvoker;
//    }
//
//    @Override
//    public MenuInvoker menuInvoker() {
//        return menuInvoker;
//    }
//
//    @Override
//    public UserInfoInvoker userInfoInvoker() {
//        return userInfoInvoker;
//    }
//}
