package com.magneton.we.api.starter;

import com.magneton.we.api.core.InvokerLifeCycle;
import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.event.EventHandler;
import com.magneton.we.api.event.EventProcessor;
import com.magneton.we.api.event.EventProcessorImpl;
import com.magneton.we.api.http.HttpWe;
import com.magneton.we.api.msgprocessor.*;
import com.magneton.we.api.msgprocessor.handler.WeMsgHandler;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2019/8/12
 */
@Configuration
public class MsgDispatcherAutoConfiguration {


    /**
     * 微信环境，用来提供微个的各种接口调用入口
     *
     * @param invokerLifeCycles
     * @param dispatcherWeProperties
     * @return weEnvironment
     */
    @Bean
    public WeEnvironment weEnvironment(
            @Autowired DispatcherWeProperties dispatcherWeProperties,
            @Autowired(required = false) List<InvokerLifeCycle> invokerLifeCycles) {

        WeMsgCrypt weMsgCrypt = null;
        if (dispatcherWeProperties.isLaws()) {
            try {
                WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(dispatcherWeProperties.getToken(),
                        dispatcherWeProperties.getEncodingAESKey(),
                        dispatcherWeProperties.getAppId());
                weMsgCrypt = new WeMsgCryptWrapper(wxBizMsgCrypt);
            } catch (AesException e) {
                throw new RuntimeException(e);
            }
        }

        return new HttpWe(invokerLifeCycles, dispatcherWeProperties, weMsgCrypt);

    }

    @Bean
    public EventProcessor eventProcessor(
            @Autowired(required = false) List<EventHandler> eventhandlers) {
        EventProcessorImpl processor = new EventProcessorImpl();
        processor.setClickEvents(eventhandlers);
        return processor;
    }

    @Bean
    public WeMsgProcessor msgProcessor(@Autowired List<WeMsgHandler> handlers,
                                       @Autowired DispatcherWeProperties dispatcherWeProperties,
                                       @Autowired WeEnvironment weEnvironment) {
        WeInput weInput = new WeInputImpl(handlers);
        WeOutput weOutput = new WeOutputImpl(weEnvironment);

        return WeMsgProcessorImpl.builder()
                .input(weInput)
                .output(weOutput)
                .build();
    }
}
