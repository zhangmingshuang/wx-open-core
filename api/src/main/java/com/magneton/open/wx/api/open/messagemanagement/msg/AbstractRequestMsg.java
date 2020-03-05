package com.magneton.open.wx.api.open.messagemanagement.msg;

import com.magneton.open.wx.api.handler.MsgParser;
import com.magneton.open.wx.api.open.BasicRequestMsg;
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
    implements MsgParser<AbstractRequestMsg> {

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
