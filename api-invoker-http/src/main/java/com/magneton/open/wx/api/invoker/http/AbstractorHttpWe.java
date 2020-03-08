package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.core.WeEnvironment;
import lombok.Getter;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
@Getter
public abstract class AbstractorHttpWe {

    private WeEnvironment environment;

    public AbstractorHttpWe(WeEnvironment environment) {
        this.environment = environment;
    }

}
