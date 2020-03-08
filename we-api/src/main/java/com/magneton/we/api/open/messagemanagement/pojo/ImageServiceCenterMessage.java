package com.magneton.we.api.open.messagemanagement.pojo;

import com.magneton.we.api.open.basic.pojo.MediaId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送图片消息
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ImageServiceCenterMessage extends AbstractServiceCenterMessage {

    private MediaId image;

    @Override
    public String getMsgType() {
        return "image";
    }
}
