package com.magneton.open.wx.api.open.messagemanagement.msg;

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
public class TextMsgRequest extends AbstractRequestMsg {

    private String content;

    @Override
    public TextMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.content = params.get("Content");
        return this;
    }
}
