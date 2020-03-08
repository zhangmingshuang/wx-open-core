package com.magneton.we.api.http.core;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
@Setter
@Getter
@ToString
public class HttpResponse extends JSONObject {

    private boolean hasErrorCode;

    public boolean isSuccess() {
        return !hasErrorCode;
    }
}