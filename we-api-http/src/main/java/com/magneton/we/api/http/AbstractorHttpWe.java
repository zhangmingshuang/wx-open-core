package com.magneton.we.api.http;

import com.magneton.we.api.core.WeEnvironment;
import lombok.Getter;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
@Getter
public abstract class AbstractorHttpWe {

    private WeEnvironment weEnvironment;

    public AbstractorHttpWe(WeEnvironment weEnvironment) {
        this.weEnvironment = weEnvironment;
    }

}
