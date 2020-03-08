package com.magneton.we.api.http;

import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.http.core.HttpRequest;
import com.magneton.we.api.http.core.HttpResponse;
import com.magneton.we.api.open.intelligentinterface.Ocr;
import com.magneton.we.api.open.intelligentinterface.pojo.IdCardBack;
import com.magneton.we.api.open.intelligentinterface.pojo.IdCardFront;
import com.magneton.we.api.util.StringUtil;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpOcr extends AbstractorHttpWe
        implements Ocr {

    private static final String ID_CARD = "https://api.weixin.qq.com/cv/ocr/idcard?img_url={}&access_token={}";

    public HttpOcr(WeEnvironment environment) {
        super(environment);
    }

    @Override
    public IdCardFront idCardFront(String imgUrl) {
        return this.getHttpResponse(imgUrl, IdCardFront.class);
    }


    @Override
    public IdCardBack idCardBack(String imgUrl) {
        return this.getHttpResponse(imgUrl, IdCardBack.class);
    }


    private <T> T getHttpResponse(String imgUrl, Class<T> response) {
        String accessToken = getWeEnvironment().accessToken().getAccessToken();
        String url = null;
        try {
            url = StringUtil.format(URLEncoder.encode(imgUrl, "UTF-8"), accessToken);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpResponse result = HttpRequest.doRequest(url);
        if (!result.isSuccess()) {
            return null;
        }
        return result.toJavaObject(response);
    }

}
