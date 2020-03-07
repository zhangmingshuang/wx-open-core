package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.event.EventHandler;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.event.EventProcessorImpl;
import com.magneton.open.wx.api.msgprocessor.WeInput;
import com.magneton.open.wx.api.msgprocessor.WeOutput;
import com.magneton.open.wx.api.msgprocessor.handler.WeMsgHandler;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.invoker.http.HttpWeInvoker;
import com.magneton.open.wx.api.msgprocessor.WeInputImpl;
import com.magneton.open.wx.api.msgprocessor.WeMsgCrypt;
import com.magneton.open.wx.api.msgprocessor.WeOutputImpl;
import com.magneton.open.wx.api.msgprocessor.WeMsgProcessor;
import com.magneton.open.wx.api.msgprocessor.WeMsgProcessorImpl;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zhangmingshuang
 * @since 2019/8/12
 */
@Configuration
@ComponentScan("com.sgcc.wx.msg.dispatcher.http")
public class MsgDispatcherAutoConfiguration {


    /**
     * 微信环境，用来提供微个的各种接口调用入口
     *
     * @param invokerLifeCycles
     * @param dispatcherWxProperties
     * @return WeixinEnvironment
     */
    @Bean
    public WeEnvironment weEnvironment(
        @Autowired DispatcherWxProperties wxProperties,
        @Autowired(required = false) List<InvokerLifeCycle> invokerLifeCycles,
        @Autowired DispatcherWxProperties dispatcherWxProperties) {

        WeMsgCrypt weMsgCrypt = null;
        if (wxProperties.isLaws()) {
            try {
                WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(wxProperties.getToken(),
                                                                wxProperties.getEncodingAESKey(),
                                                                wxProperties.getAppId());
                weMsgCrypt = new WeMsgCryptWrapper(wxBizMsgCrypt);
            } catch (AesException e) {
                throw new RuntimeException(e);
            }
        }

        return new HttpWeInvoker(invokerLifeCycles, dispatcherWxProperties, weMsgCrypt);

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
                                       @Autowired DispatcherWxProperties wxProperties,
                                       @Autowired WeEnvironment weEnvironment) {
        WeInput weInput = new WeInputImpl(handlers);
        WeOutput weOutput = new WeOutputImpl(weEnvironment);

        return WeMsgProcessorImpl.builder()
                                 .input(weInput)
                                 .output(weOutput)
                                 .build();
    }
}
