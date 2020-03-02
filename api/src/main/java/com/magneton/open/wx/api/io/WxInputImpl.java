package com.magneton.open.wx.api.io;

import com.magneton.open.wx.api.core.WxMsgConverException;
import com.magneton.open.wx.api.processor.WxMsgProcessor;
import com.magneton.open.wx.api.entity.msg.WxMsg;
import com.magneton.open.wx.api.handler.HandleMsg;
import com.magneton.open.wx.api.handler.HandlerException;
import com.magneton.open.wx.api.handler.MsgCondition;
import com.magneton.open.wx.api.handler.MsgConditions;
import com.magneton.open.wx.api.handler.MsgHandler;
import com.magneton.open.wx.api.handler.MsgHandlerWrapper;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.sgcc.wx.msg.dispatcher.api.handler.*;
import com.magneton.open.wx.api.parser.WxXmlParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会根据{@link MsgCondition}和{@link MsgConditions}进行条件组合判断
 * <p>
 * 条件相当于if else逻辑判断，所以，会出现逻辑冲突，如:
 *
 * <pre>
 * \@MsgCondition(key="test",on="value")
 * 与
 * \@MsgCinditions({
 *   \@MsgCondition(key="test",on="value"),
 *   \@MsgCondition(key="test2",on="value2"),
 * })
 * </pre>
 * 存在逻辑判断冲突，只要满足 {@code test=value}的情况，则无法进行逻辑分支的选择。
 * <p>
 * 所以，在对类创建进行注解解析时，将对组合条件进行逻辑等级判断，
 * 即组合越多的逻辑等级越高。如下示例将组成：
 * <pre>
 *      String value = xxx.get("test");
 *      String value2 = xxx.get("test2");
 *      if(value.equals("test") 且 value2.equals("value2"){
 *          //do something
 *      } else if(value.equals("test"){
 *          //do something
 *      }
 * </pre>
 *
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public class WxInputImpl implements WxInput {

    private final List<MsgHandlerWrapper> wrappers = new ArrayList<>();
    private WxMsgProcessor dispatcher;
    private WXBizMsgCrypt wxBizMsgCrypt;

    public WxInputImpl(List<MsgHandler> handlers) {
        this.build(handlers);
    }

    @Override
    public void afterDispatcherSet(WxMsgProcessor dispatcher) {
        this.dispatcher = dispatcher;
        this.wxBizMsgCrypt = dispatcher.wxBizMsgCrypt();
    }

    @Override
    public Map<String, String> decryptMsg(InputStream inputStream,
                                          String msgSignature,
                                          String timestamp,
                                          String nonce) {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String postData = result.toString("UTF-8");
            if (wxBizMsgCrypt != null) {
                postData = wxBizMsgCrypt.decryptMsg(
                    msgSignature, timestamp, nonce, postData);
            }
            Map<String, String> parse = WxXmlParser.parse(postData);
            parse.put("nonce", nonce);
            return parse;
        } catch (Throwable e) {
            throw new WxMsgConverException("[WxInput]解析微信公众号消息异常", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                //Ignore
            }
        }
    }

    @Override
    public WxMsg distribute(InputStream inputStream,
                            String msgSignature,
                            String timestamp,
                            String nonce) {
        Map<String, String> params
            = this.decryptMsg(inputStream, msgSignature, timestamp, nonce);
        return this.distribute(params);
    }

    @Override
    public WxMsg distribute(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        System.out.println("---------------------------");
        params.forEach((k, v) -> {
            System.out.println(k + "   =   " + v);
        });
        System.out.println("---------------------------");
        MsgHandler suitable = this.getSuitable(params);
        if (suitable == null) {
            return null;
        }
        return suitable.handle(new HandleMsg(params));
    }


    private void build(List<MsgHandler> handlers) {
        Map<String, MsgHandlerWrapper> cache = new HashMap<>();
        MsgHandlerWrapper wrapper;
        for (MsgHandler handler : handlers) {
            if (handler == null) {
                continue;
            }
            wrapper = new MsgHandlerWrapper(handler);
            wrapper.parse();
            String rule = wrapper.getRule();
            MsgHandlerWrapper exist = cache.get(rule);
            if (exist != null) {
                throw new HandlerException(handler.getClass(), exist.getHandler().getClass());
            }
            cache.put(rule, wrapper);
        }
        wrappers.addAll(cache.values());
        wrappers.sort((a, b) -> b.getLv() - a.getLv());
    }

    private MsgHandler getSuitable(Map<String, String> params) {
        for (int i = 0, s = wrappers.size(); i < s; i++) {
            MsgHandlerWrapper handlerWrapper = wrappers.get(i);
            if (!handlerWrapper.isProcessable(params)) {
                continue;
            }
            return handlerWrapper;
        }
        return null;
    }
}
