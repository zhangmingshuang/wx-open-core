package com.magneton.open.wx.api.open.usermanagement.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Tag {
    /**
     * 标签id，由微信分配
     */
    private long id;
    /**
     * 标签名，UTF8编码
     */
    private String name;
    /**
     * 此标签下的粉丝数
     */
    private int count;
}
