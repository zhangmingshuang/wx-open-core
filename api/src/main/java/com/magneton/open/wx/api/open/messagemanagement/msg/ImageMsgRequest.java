package com.magneton.open.wx.api.open.messagemanagement.msg;

import java.util.Map;

import com.magneton.open.wx.api.core.AbstractRequestMsg;
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
public class ImageMsgRequest extends AbstractRequestMsg {

    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 图片链接（由系统生成）
     */
    private String picUrl;

    @Override
    public ImageMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.mediaId = params.get("MediaId");
        this.picUrl = params.get("PicUrl");
        return this;
    }
}
