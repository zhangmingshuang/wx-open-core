package com.magneton.open.wx.api.open.usermanagement.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户管理-标签管理-获取标签下粉丝列表
 */
@Setter
@Getter
@ToString
public class TagFans {
    /**
     * 这次获取的粉丝数量
     */
    private int count;
    /**
     * 粉丝列表
     */
    private Data data;
    /**
     * 拉取列表最后一个用户的openid
     */
    @JSONField(name = "next_open_id")
    private String nextOpenId;

    @Setter
    @Getter
    @ToString
    public static class Data {
        @JSONField(name = "openid")
        private String[] openId;
    }
}
