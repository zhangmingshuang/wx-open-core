package com.magneton.we.api.core;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public interface InvokerLifeCycle {

    void beforeStart(WeEnvironment environment);

    void afterStart();
}
