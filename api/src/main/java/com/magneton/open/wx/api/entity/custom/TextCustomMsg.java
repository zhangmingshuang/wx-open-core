package com.magneton.open.wx.api.entity.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服消息-文本消息
 *
 * 说明：
 * {@code
 * <pre>
 *   1.data-miniprogram-appid 项，填写小程序appid，则表示该链接跳小程序；
 *   2.data-miniprogram-path项，填写小程序路径，路径与app.json中保持一致，可带参数；
 *   3.对于不支持data-miniprogram-appid 项的客户端版本，如果有herf项，则仍然保持跳href中的网页链接；
 *   4.data-miniprogram-appid对应的小程序必须与公众号有绑定关系。
 * </pre>
 * }
 *
 * @author zhangmingshuang
 * @since 2019/11/6
 */
@Setter
@Getter
@ToString
public class TextCustomMsg implements CustomMsg {

    private String toUser;
    private String content;

    @Override
    public String toJSON() {
        return "{\"touser\":\"" + toUser + "\","
            + "\"msgtype\":\"text\","
            + "\"text\":{\"content\":\"" + content + "\"}"
            + "}";
    }
}
