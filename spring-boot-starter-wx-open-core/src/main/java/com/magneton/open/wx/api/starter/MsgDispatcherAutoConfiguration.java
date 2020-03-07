package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.core.InvokerLifeCycle;
import com.magneton.open.wx.api.event.EventHandler;
import com.magneton.open.wx.api.event.EventProcessor;
import com.magneton.open.wx.api.event.EventProcessorImpl;
import com.magneton.open.wx.api.handler.MsgHandler;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.invoker.http.HttpWeInvoker;
import com.magneton.open.wx.api.io.WeInputImpl;
import com.magneton.open.wx.api.io.WeOutputImpl;
import com.magneton.open.wx.api.processor.WeMsgProcessor;
import com.magneton.open.wx.api.processor.WeMsgProcessorImpl;
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
    public WeEnvironment weixinEnvironment(
        @Autowired(required = false) List<InvokerLifeCycle> invokerLifeCycles,
        @Autowired DispatcherWxProperties dispatcherWxProperties) {
        return new HttpWeInvoker(invokerLifeCycles, dispatcherWxProperties);
    }

    @Bean
    public EventProcessor eventProcessor(
        @Autowired(required = false) List<EventHandler> eventhandlers) {
        EventProcessorImpl processor = new EventProcessorImpl();
        processor.setClickEvents(eventhandlers);
        return processor;
    }

//    @Bean
//    public WxInvokeProcessor invokeProcessor(AccessTokenInvoker accessTokenInvoker,
//                                             MenuInvoker menuInvoker,
//                                             UserInfoInvoker userInfoInvoker) {
//        return WxInvokeProcessorImpl.builder()
//                                    .accessTokenInvoker(accessTokenInvoker)
//                                    .menuInvoker(menuInvoker)
//                                    .userInfoInvoker(userInfoInvoker)
//                                    .build();
//    }

    @Bean
    public WeMsgProcessor msgProcessor(List<MsgHandler> handlers,
                                       DispatcherWxProperties wxProperties) {
        WXBizMsgCrypt wxBizMsgCrypt = null;
        if (wxProperties.isLaws()) {
            try {
                wxBizMsgCrypt = new WXBizMsgCrypt(wxProperties.getToken(),
                                                  wxProperties.getEncodingAESKey(),
                                                  wxProperties.getAppId());
            } catch (AesException e) {
                throw new RuntimeException(e);
            }
        }
        return WeMsgProcessorImpl.builder()
                                   .config(wxProperties)
                                   .wxBizMsgCrypt(wxBizMsgCrypt)
                                   .input(new WeInputImpl(handlers))
                                   .output(new WeOutputImpl())
                                   .build();
    }
}
