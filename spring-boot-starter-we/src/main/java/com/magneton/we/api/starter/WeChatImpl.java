package com.magneton.we.api.starter;

import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.event.EventProcessor;
import com.magneton.we.api.msgprocessor.WeMsgProcessor;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Component
@Configurable
public class WeChatImpl implements WeChat {

    @Autowired
    @Delegate
    private WeEnvironment weEnvironment;
    @Autowired
    private EventProcessor eventProcessor;
    @Autowired
    private WeMsgProcessor weMsgProcessor;

    @Override
    public WeMsgProcessor msgProcessor() {
        return weMsgProcessor;
    }

    @Override
    public EventProcessor eventProcessor() {
        return eventProcessor;
    }

}
