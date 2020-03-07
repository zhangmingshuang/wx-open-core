package com.magneton.open.wx.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 发送卡券
 *
 * 特别注意客服消息接口投放卡券仅支持非自定义Code码和导入code模式的卡券的卡券，详情请见：创建卡券。
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class WxCardServiceCenterMessage extends AbstractServiceCenterMessage {

    @JSONField(name = "wxcard")
    private WxCard wxCard;

    @Override
    public String getMsgType() {
        return "wxcard";
    }

    @Setter
    @Getter
    @ToString
    public static class WxCard {

        @JSONField(name = "card_id")
        private String cardId;
    }
}
