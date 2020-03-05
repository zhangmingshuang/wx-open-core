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
@ToString
public class LinkMsgRequest extends AbstractRequestMsg {

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 链接
     */
    private String url;

    @Override
    public LinkMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.title = params.get("Title");
        this.description = params.get("Description");
        this.url = params.get("Url");
        return this;
    }
}
