package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.core.WeixinEnvironment;
import lombok.Getter;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
@Getter
public abstract class AbstractorHttpInvoker {

    private WeixinEnvironment environment;

    public AbstractorHttpInvoker(WeixinEnvironment environment) {
        this.environment = environment;
    }

}
