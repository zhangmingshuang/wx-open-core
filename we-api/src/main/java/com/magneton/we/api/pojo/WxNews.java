package com.magneton.we.api.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2019/9/9
 */
@Setter
@Getter
@ToString
public class WxNews {

    private String title;
    private String description;
    private String picUrl;
}
