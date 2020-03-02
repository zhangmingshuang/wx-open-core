package com.magneton.open.wx.api.invoker;

import com.magneton.open.wx.api.core.LifeCycle;
import com.magneton.open.wx.api.core.WeixinEnvironment;
import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public abstract class AbstractWeixinWeixinInvoker implements LifeCycle, WeixinEnvironment {

    private List<LifeCycle> lifeCycles;
    private List<Invoker> invokers;

    public AbstractWeixinWeixinInvoker(List<LifeCycle> lifeCycles) {
        this.lifeCycles = lifeCycles;
        this.init();
    }

    public void init() {
        this.beforeStart(this);

        AccessTokenInvoker accessTokenInvoker = this.getAccessTokenInvoker();

        invokers = this.getInvokers();
        for (Invoker invoker : invokers) {
            this.onStarting(invoker);
        }

        this.afterStart();
    }

    @Override
    public void beforeStart(WeixinEnvironment environment) {
        for (int i = 0, l = lifeCycles.size(); i < l; i++) {
            LifeCycle lifeCycle = lifeCycles.get(i);
            lifeCycle.beforeStart(environment);
        }
    }

    @Override
    public void onStarting(Invoker invoker) {
        for (int i = 0, l = lifeCycles.size(); i < l; i++) {
            LifeCycle lifeCycle = lifeCycles.get(i);
            lifeCycle.onStarting(invoker);
        }
    }

    @Override
    public void afterStart() {
        for (int i = 0, l = lifeCycles.size(); i < l; i++) {
            LifeCycle lifeCycle = lifeCycles.get(i);
            lifeCycle.afterStart();
        }
    }
}
