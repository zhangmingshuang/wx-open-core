package com.magneton.we.api.open.messagemanagement.msg;

import java.util.Map;

import com.magneton.we.api.core.AbstractRequestMsg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 视频消息
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString
public class VideoMsgRequest extends AbstractRequestMsg {

    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;

    @Override
    public VideoMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.mediaId = params.get("MediaId");
        this.thumbMediaId = params.get("ThumbMediaId");
        return this;
    }
}
