package com.magneton.open.wx.api.invoker;

import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.open.basic.AccessTokenInvoker;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public abstract class AbstractWeInvoker implements InvokerLifeCycle, WeEnvironment {

    private List<InvokerLifeCycle> invokerLifeCycles;
    private List<Invoker> invokers;

    public AbstractWeInvoker(List<InvokerLifeCycle> invokerLifeCycles) {
        this.invokerLifeCycles = invokerLifeCycles;
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
    public void beforeStart(WeEnvironment environment) {
        for (int i = 0, l = invokerLifeCycles.size(); i < l; i++) {
            InvokerLifeCycle invokerLifeCycle = invokerLifeCycles.get(i);
            invokerLifeCycle.beforeStart(environment);
        }
    }

    @Override
    public void onStarting(Invoker invoker) {
        for (int i = 0, l = invokerLifeCycles.size(); i < l; i++) {
            InvokerLifeCycle invokerLifeCycle = invokerLifeCycles.get(i);
            invokerLifeCycle.onStarting(invoker);
        }
    }

    @Override
    public void afterStart() {
        for (int i = 0, l = invokerLifeCycles.size(); i < l; i++) {
            InvokerLifeCycle invokerLifeCycle = invokerLifeCycles.get(i);
            invokerLifeCycle.afterStart();
        }
    }
}
