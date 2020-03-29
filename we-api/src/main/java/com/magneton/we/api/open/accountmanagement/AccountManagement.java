package com.magneton.we.api.open.accountmanagement;

import com.magneton.we.api.open.We;

public interface AccountManagement extends We {

    /**
     * 1. 通过ticket换取二维码
     * <p>
     * 获取二维码ticket后，开发者可用ticket换取二维码图片。请注意，本接口无须登录态即可调用。
     * <p>
     * 请求说明
     * <p>
     * HTTP GET请求（请使用https协议）
     * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode
     * <p>
     * 返回说明
     * <p>
     * ticket正确情况下，http 返回码是200，是一张图片，可以直接展示或者下载。
     *
     * @param request
     * @return
     */
    QrCodeTicketResponse createTicket(QrCodeTicketRequest request);

    /**
     * 2. 获取二维码图片地址
     * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     *
     * @param response
     * @return
     */
    String getQrCodeUrl(QrCodeTicketResponse response);
}
