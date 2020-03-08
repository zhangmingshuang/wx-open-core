package com.magneton.we.api.open.messagemanagement.msg;

import java.util.Map;

import com.magneton.we.api.core.AbstractRequestMsg;
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
public class VoiceMsgRequest extends AbstractRequestMsg {

    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String format;

    @Override
    public VoiceMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.mediaId = params.get("MeidaId");
        this.format = params.get("Format");
        return this;
    }
}
