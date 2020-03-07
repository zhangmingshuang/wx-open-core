package com.magneton.open.wx.api.open.messagemanagement.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 文本内容
 * <a href="http://www.qq.com"
 * data-miniprogram-appid="appid"
 * data-miniprogram-path="pages/index/index">点击跳小程序</a>
 *
 * 说明：
 * 1.data-miniprogram-appid 项，填写小程序appid，则表示该链接跳小程序；
 * 2.data-miniprogram-path项，填写小程序路径，路径与app.json中保持一致，可带参数；
 * 3.对于不支持data-miniprogram-appid 项的客户端版本，如果有herf项，则仍然保持跳href中的网页链接；
 * 4.data-miniprogram-appid对应的小程序必须与公众号有绑定关系。
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class TextServiceCenterMessage extends AbstractServiceCenterMessage {

    private Text text;

    @Override
    public String getMsgType() {
        return "text";
    }

    @Setter
    @Getter
    @ToString
    public static class Text {

        private String content;
    }
}
