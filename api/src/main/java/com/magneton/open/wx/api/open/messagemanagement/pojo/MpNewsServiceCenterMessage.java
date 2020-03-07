package com.magneton.open.wx.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.magneton.open.wx.api.open.basic.pojo.MediaId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 客服接口-发消息
 *
 * 发送图文消息（点击跳转到图文消息页面）
 * 图文消息条数限制在1条以内，注意，如果图文数超过1，则将会返回错误码45008。
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString
public class MpNewsServiceCenterMessage extends AbstractServiceCenterMessage {

    @JSONField(name = "mpnews")
    private MediaId mpNews;

    @Override
    public String getMsgType() {
        return "mpnews";
    }
}
