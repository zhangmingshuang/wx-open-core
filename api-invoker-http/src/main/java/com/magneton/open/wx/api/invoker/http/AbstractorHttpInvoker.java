package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.core.WeEnvironment;
import lombok.Getter;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
@Getter
public abstract class AbstractorHttpInvoker {

    private WeEnvironment environment;

    public AbstractorHttpInvoker(WeEnvironment environment) {
        this.environment = environment;
    }

}
