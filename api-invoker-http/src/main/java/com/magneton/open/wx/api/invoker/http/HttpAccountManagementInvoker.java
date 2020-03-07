package com.magneton.open.wx.api.invoker.http;

import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
import com.magneton.open.wx.api.open.accountmanagement.AccountManagementInvoker;
import com.magneton.open.wx.api.open.accountmanagement.QrCodeTicketRequest;
import com.magneton.open.wx.api.open.accountmanagement.QrCodeTicketResponse;
import com.magneton.open.wx.api.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpAccountManagementInvoker extends AbstractorHttpInvoker
        implements AccountManagementInvoker {

    private static final String CREATE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={}";
    private static final String GET_QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={}";

    public HttpAccountManagementInvoker(WeEnvironment environment) {
        super(environment);
    }

    @Override
    public QrCodeTicketResponse createTicket(QrCodeTicketRequest request) {

        String accessToken = getEnvironment().getAccessTokenInvoker().getAccessToken();
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
