package com.magneton.open.wx.api.open;

import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.open.basic.AccessToken;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2020/3/2
 */
public abstract class AbstractWe implements InvokerLifeCycle, WeEnvironment {

    private List<InvokerLifeCycle> invokerLifeCycles;
    private List<We> wes;
    private WeMsgCrypt weMsgCrypt;

    public AbstractWe(List<InvokerLifeCycle> invokerLifeCycles,
                      WeMsgCrypt weMsgCrypt) {
        this.invokerLifeCycles = invokerLifeCycles;
        this.weMsgCrypt = weMsgCrypt;
        this.init();
    }

    public void init() {
        this.beforeStart(this);
        AccessToken accessToken = this.accessToken();
        this.afterStart();
    }

    @Override
    public WeMsgCrypt weMsgCrypt() {
        return weMsgCrypt;
    }

    @Override
    public void beforeStart(WeEnvironment environment) {
        for (int i = 0, l = invokerLifeCycles.size(); i < l; i++) {
            InvokerLifeCycle invokerLifeCycle = invokerLifeCycles.get(i);
            invokerLifeCycle.beforeStart(environment);
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
