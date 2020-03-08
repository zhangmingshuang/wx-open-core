package com.magneton.open.wx.api.invoker.http.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magneton.open.wx.api.constant.WeResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhangmingshuang
 * @since 2019/9/17
 */
public class HttpRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);

    private HttpRequest() {

    }

    public static HttpResponse doJsonRequest(String url, Object data) {
        return doRequest(url, JSON.toJSONString(data));
    }

    public static HttpResponse doRequest(String url, String postData) {
        try {
            String response = RequestProxy.doPost(url, postData);
            return doResponseParse(response);
        } catch (IOException e) {
            LOGGER.error("[wx]请求" + url.substring(0, url.indexOf('?')) + "异常", e);
        }
        return null;
    }

    public static HttpResponse doRequest(String url) {
        try {
            String response = RequestProxy.doGet(url);
            return doResponseParse(response);
        } catch (IOException e) {
            LOGGER.error("[wx]请求" + url.substring(0, url.indexOf('?')) + "异常", e);
        }
        return null;
    }

    private static HttpResponse doResponseParse(String response) {
        HttpResponse object = JSONObject.parseObject(response, HttpResponse.class);
        Integer error = object.getInteger("errcode");
        if (error != null && error.intValue() != 0) {
            //请求失败
            String errorMsg = WeResponseCode.getError(error.intValue());
            LOGGER.error("[wx]请求失败。code:[{}], msg:[{}], response:[{}]",
                    error, errorMsg, response);
            object.setHasErrorCode(true);
        }
        return object;
    }
}
