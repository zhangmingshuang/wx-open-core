package com.magneton.we.api.open.messagemanagement.pojo;

import com.magneton.we.api.open.basic.pojo.MediaId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送语音消息
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class VoiceServiceCenterMessage extends AbstractServiceCenterMessage {

    private MediaId voice;

    @Override
    public String getMsgType() {
        return "voice";
    }
}
