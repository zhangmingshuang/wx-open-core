package com.magneton.we.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送视频消息
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class VideoServiceCenterMessage extends AbstractServiceCenterMessage {

    private Video video;

    @Override
    public String getMsgType() {
        return "video";
    }

    @Setter
    @Getter
    @ToString
    public static class Video {

        @JSONField(name = "media")
        private String mediaId;
        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;
        private String title;
        private String description;
    }
}
