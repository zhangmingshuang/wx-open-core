package com.magneton.we.api.open.messagemanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送音乐消息
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class MusicServiceCenterMessage extends AbstractServiceCenterMessage {

    private Music music;

    @Override
    public String getMsgType() {
        return "music";
    }

    @Setter
    @Getter
    @ToString
    public static class Music {

        private String title;
        private String description;
        private String musicUrl;
        @JSONField(name = "hqmusicurl")
        private String hqMusicUrl;
        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;
    }
}
