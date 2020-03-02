package com.magneton.open.wx.api.processor;

import com.magneton.open.wx.api.ApiConfig;
import com.magneton.open.wx.api.io.WxInput;
import com.magneton.open.wx.api.io.WxOutput;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.magneton.open.wx.api.handler.MsgCondition;
import com.magneton.open.wx.api.handler.MsgConditions;
import lombok.Getter;

/**
 * 消息分发处理器，用来将消息分发到具体的实现类中，和消息输出处理
 *
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
 * @since 2019/9/4
 */
@Getter
public class WxWxMsgProcessorImpl implements WxMsgProcessor {

    private WxInput input;
    private WxOutput output;
    private WXBizMsgCrypt wxBizMsgCrypt;
    private ApiConfig apiConfig;

    private WxWxMsgProcessorImpl(WxInput input,
                                 WxOutput output,
                                 WXBizMsgCrypt wxBizMsgCrypt,
                                 ApiConfig apiConfig) {
        this.wxBizMsgCrypt = wxBizMsgCrypt;
        this.apiConfig = apiConfig;
        this.input = input;
        this.output = output;
        this.input.afterDispatcherSet(this);
        this.output.afterDispatcherSet(this);
    }

    @Override
    public ApiConfig apiConfig() {
        return apiConfig;
    }

    @Override
    public WxInput input() {
        return input;
    }

    @Override
    public WxOutput output() {
        return output;
    }

    @Override
    public WXBizMsgCrypt wxBizMsgCrypt() {
        return wxBizMsgCrypt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private WxInput input;
        private WxOutput output;
        private WXBizMsgCrypt wxBizMsgCrypt;
        private ApiConfig apiConfig;

        public WxWxMsgProcessorImpl build() {
            return new WxWxMsgProcessorImpl(input, output, wxBizMsgCrypt, apiConfig);
        }

        public Builder wxBizMsgCrypt(WXBizMsgCrypt wxBizMsgCrypt) {
            this.wxBizMsgCrypt = wxBizMsgCrypt;
            return this;
        }

        public Builder input(WxInput input) {
            this.input = input;
            return this;
        }

        public Builder output(WxOutput output) {
            this.output = output;
            return this;
        }

        public Builder config(ApiConfig apiConfig) {
            this.apiConfig = apiConfig;
            return this;
        }
    }
}
