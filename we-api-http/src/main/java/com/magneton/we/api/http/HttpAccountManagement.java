package com.magneton.we.api.http;

import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.http.core.HttpRequest;
import com.magneton.we.api.http.core.HttpResponse;
import com.magneton.we.api.open.accountmanagement.AccountManagement;
import com.magneton.we.api.open.accountmanagement.QrCodeTicketRequest;
import com.magneton.we.api.open.accountmanagement.QrCodeTicketResponse;
import com.magneton.we.api.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpAccountManagement extends AbstractorHttpWe
        implements AccountManagement {

    private static final String CREATE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={}";
    private static final String GET_QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={}";

    public HttpAccountManagement(WeEnvironment environment) {
        super(environment);
    }

    @Override
    public QrCodeTicketResponse createTicket(QrCodeTicketRequest request) {

        String accessToken = getWeEnvironment().accessToken().getAccessToken();
        String url = StringUtil.format(CREATE_TICKET, accessToken);

        HttpResponse result = HttpRequest.doJsonRequest(url, request);
        if (!result.isSuccess()) {
            return null;
        }
        return result.toJavaObject(QrCodeTicketResponse.class);
    }

    @Override
    public String getQrCodeUrl(QrCodeTicketResponse response) {
        String ticket = response.getTicket();
        try {
            String encode = URLEncoder.encode(ticket, "UTF-8");
            return StringUtil.format(GET_QR_CODE_URL, encode);
        } catch (UnsupportedEncodingException e) {
            //Ignore
        }
        return null;
    }
}
