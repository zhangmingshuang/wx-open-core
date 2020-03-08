package com.magneton.open.wx.api.core;

import com.magneton.open.wx.api.msgprocessor.handler.WeMsgParser;
import com.magneton.open.wx.api.util.StringUtil;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString(callSuper = true)
public abstract class AbstractRequestMsg extends BasicRequestMsg
    implements WeMsgParser<AbstractRequestMsg> {

    /**
     * 消息id，64位整型
     */
    private long msgId;

    @Override
    public AbstractRequestMsg parse(Map<String, String> params) {
        super.parse(params);
        this.msgId = StringUtil.getLongVal(params.get("MsgId"));
        return this;
    }
}
