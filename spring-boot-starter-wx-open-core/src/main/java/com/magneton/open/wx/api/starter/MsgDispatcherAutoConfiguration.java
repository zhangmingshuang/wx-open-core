package com.magneton.open.wx.api.starter;

import com.magneton.open.wx.api.core.LifeCycle;
import com.magneton.open.wx.api.event.EventHandler;
import com.magneton.open.wx.api.event.WxEventProcessor;
import com.magneton.open.wx.api.event.WxWxEventProcessorImpl;
import com.magneton.open.wx.api.handler.MsgHandler;
import com.magneton.open.wx.api.invoker.AccessTokenInvoker;
import com.magneton.open.wx.api.invoker.MenuInvoker;
import com.magneton.open.wx.api.invoker.UserInfoInvoker;
import com.magneton.open.wx.api.core.WeixinEnvironment;
import com.magneton.open.wx.api.invoker.http.HttpWeixinWeixinInvoker;
import com.magneton.open.wx.api.io.WxInputImpl;
import com.magneton.open.wx.api.io.WxOutputImpl;
import com.magneton.open.wx.api.processor.WxInvokeProcessor;
import com.magneton.open.wx.api.processor.WxInvokeProcessorImpl;
import com.magneton.open.wx.api.processor.WxMsgProcessor;
import com.magneton.open.wx.api.processor.WxWxMsgProcessorImpl;
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


    @Bean
    public WeixinEnvironment weixinInvoker(@Autowired(required = false) List<LifeCycle> lifeCycles,
                                           @Autowired DispatcherWxProperties dispatcherWxProperties) {
        return new HttpWeixinWeixinInvoker(lifeCycles, dispatcherWxProperties);
    }

    @Bean
    public WxEventProcessor wxEventProcessor(List<EventHandler> eventhandlers) {
        WxWxEventProcessorImpl processor = new WxWxEventProcessorImpl();
        processor.setClickEvents(eventhandlers);
        return processor;
    }

    @Bean
    public WxInvokeProcessor invokeProcessor(AccessTokenInvoker accessTokenInvoker,
                                             MenuInvoker menuInvoker,
                                             UserInfoInvoker userInfoInvoker) {
        return WxInvokeProcessorImpl.builder()
                                    .accessTokenInvoker(accessTokenInvoker)
                                    .menuInvoker(menuInvoker)
                                    .userInfoInvoker(userInfoInvoker)
                                    .build();
    }

    @Bean
    public WxMsgProcessor msgProcessor(List<MsgHandler> handlers,
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
        return WxWxMsgProcessorImpl.builder()
                                   .config(wxProperties)
                                   .wxBizMsgCrypt(wxBizMsgCrypt)
                                   .input(new WxInputImpl(handlers))
                                   .output(new WxOutputImpl())
                                   .build();
    }
}
