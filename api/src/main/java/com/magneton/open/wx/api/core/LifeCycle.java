package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.invoker.Invoker;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public interface LifeCycle {

    void beforeStart(WeixinEnvironment environment);

    void onStarting(Invoker invoker);

    void afterStart();
}
