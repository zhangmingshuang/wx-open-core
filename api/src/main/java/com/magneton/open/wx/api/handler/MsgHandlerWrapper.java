package com.magneton.open.wx.api.handler;

import com.magneton.open.wx.api.entity.msg.WxMsg;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@Getter
public class MsgHandlerWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgHandlerWrapper.class);
    /**
     * 条件等级
     */
    private int lv;
    private String[] holdRule;
    private MsgHandler handler;
    private Class<? extends Type> genericHandlerClass;//handler的泛型类型

    public MsgHandlerWrapper(MsgHandler h) {
        this.handler = h;
    }

    public boolean isProcessable(Map<String, String> params) {
        if (holdRule == null || holdRule.length < 2) {
            return false;
        }
        for (int i = 0, l = holdRule.length; i < l; i += 2) {
            String k = holdRule[i];
            String v = holdRule[i + 1];
            String expect = params.get(k);
            if (!v.equals(expect)) {
                return false;
            }
        }
        return true;
    }

    public void parse() {
        Type[] genericInterfaces = handler.getClass().getGenericInterfaces();
        if (genericInterfaces == null || genericInterfaces.length != 1) {
            throw new HandlerException(handler.getClass() + "没有泛型参数");
        }
        Type[] actualTypeArguments = ((ParameterizedTypeImpl) genericInterfaces[0]).getActualTypeArguments();
        genericHandlerClass = (Class) actualTypeArguments[0];
        MsgCondition msgCondition = handler.getClass().getAnnotation(MsgCondition.class);
        if (msgCondition != null) {
            String key = msgCondition.key();
            String eq = msgCondition.eq();

            this.holdRule = new String[]{key, eq};
            this.lv = this.holdRule.length / 2;

        } else {
            MsgConditions msgConditions = handler.getClass().getAnnotation(MsgConditions.class);
            if (msgConditions != null) {
                MsgCondition[] conditions = msgConditions.value();
                if (conditions != null && conditions.length > 0) {
                    int size = conditions.length << 1;
                    this.holdRule = new String[size];
                    this.lv = this.holdRule.length / 2;

                    for (int i = 0; i < size; i += 2) {
                        msgCondition = conditions[i >> 1];

                        String key = msgCondition.key();
                        String eq = msgCondition.eq();

                        this.holdRule[i] = key;
                        this.holdRule[i + 1] = eq;
                    }
                }
            }
        }
    }


    public WxMsg handle(Map<String, String> params) {
        try {
            MsgParser msgParser = (MsgParser) genericHandlerClass.newInstance();
            return this.handler.handle((MsgParser) msgParser.parse(params));
        } catch (Throwable e) {
            LOGGER.error("调用" + this.handler.getClass() + "时异常", e);
        }
        return null;
    }

    public String getRule() {
        return Arrays.toString(this.holdRule);
    }
}
