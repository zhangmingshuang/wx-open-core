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
public class Articles {

    private String title;
    private String description;
    private String url;
    @JSONField(name = "picurl")
    private String picUrl;
}
