package com.magneton.we.api.open.basic.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString
public class MediaId {

    @JSONField(name = "media_id")
    private String mediaId;
}
