package com.magneton.open.wx.api.open.messagemanagement.event;

import com.magneton.open.wx.api.msgprocessor.handler.WeMsgParser;
import com.magneton.open.wx.api.open.BasicRequestMsg;
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
public class AbstractEventRequest extends BasicRequestMsg
    implements WeMsgParser<AbstractEventRequest> {

    private String event;

    @Override
    public AbstractEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.event = params.get("Event");
        return this;
    }
}
