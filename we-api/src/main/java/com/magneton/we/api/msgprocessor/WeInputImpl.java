package com.magneton.we.api.msgprocessor;

import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.core.WeMsgConverException;
import com.magneton.we.api.core.HandlerException;
import com.magneton.we.api.msgprocessor.handler.MsgCondition;
import com.magneton.we.api.msgprocessor.handler.MsgConditions;
import com.magneton.we.api.msgprocessor.handler.WeMsgHandler;
import com.magneton.we.api.msgprocessor.handler.MsgHandlerWrapper;
import com.magneton.we.api.core.MsgReply;
import com.magneton.we.api.parser.WxXmlParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class WeInputImpl implements WeInput {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeInputImpl.class);

    private final List<MsgHandlerWrapper> wrappers = new ArrayList<>();
    private WeEnvironment weEnvironment;

    public WeInputImpl(List<WeMsgHandler> handlers) {
        this.build(handlers);
    }

    public WeInputImpl(WeEnvironment weEnvironment) {
        this.weEnvironment = weEnvironment;
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
            if (weEnvironment.weMsgCrypt() != null) {
                postData = weEnvironment.weMsgCrypt().decryptMsg(
                    msgSignature, timestamp, nonce, postData);
            }
            Map<String, String> parse = WxXmlParser.parse(postData);
            parse.put("nonce", nonce);
            return parse;
        } catch (Throwable e) {
            throw new WeMsgConverException("[WxInput]解析微信公众号消息异常", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                //Ignore
            }
        }
    }

    @Override
    public MsgReply distribute(InputStream inputStream,
                               String msgSignature,
                               String timestamp,
                               String nonce) {
        Map<String, String> params
            = this.decryptMsg(inputStream, msgSignature, timestamp, nonce);
        return this.distribute(params);
    }

    @Override
    public MsgReply distribute(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("---------------------------");
            params.forEach((k, v) -> {
                LOGGER.debug(k + "   =   " + v);
            });
            LOGGER.debug("---------------------------");
        }
        MsgHandlerWrapper suitable = this.getSuitable(params);
        if (suitable == null) {
            return null;
        }
        return suitable.handle(params);
    }


    private void build(List<WeMsgHandler> handlers) {
        Map<String, MsgHandlerWrapper> cache = new HashMap<>();
        MsgHandlerWrapper wrapper;
        for (WeMsgHandler handler : handlers) {
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

    private MsgHandlerWrapper getSuitable(Map<String, String> params) {
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
