package com.magneton.open.wx.api.open.intelligentinterface;

import com.magneton.open.wx.api.open.We;
import com.magneton.open.wx.api.open.intelligentinterface.pojo.IdCardBack;
import com.magneton.open.wx.api.open.intelligentinterface.pojo.IdCardFront;

/**
 * Ocr
 * https://developers.weixin.qq.com/doc/offiaccount/Intelligent_Interface/OCR.html
 * <p>
 * 接口限制
 * <p>
 * 内测期间，对于已认证的订阅号、服务号、企业号、小程序，
 * 我们提供了 500 次/天 的免费额度供开发者使用。
 * 如免费额度无法满足使用需求，开发者可以【申请调整 OCR 接口调用次数上限】为主题，
 * 发送邮件至：wx_city@tencent.com 申请调整，
 * 并在正文中注明小程序帐号 AppID、业务主体、业务背景、服务流程载体、日调用量预估。
 * 调整的额度仅在内测期间有效。
 * <p>
 * 使用 TIPS
 * <p>
 * 此接口为后台接口，可基于自有业务承载情况，
 * 搭配小程序或 H5 的拍照、相册选照等 一起使用，
 * 即可完成身份证照片的采集、上传、识别、信息返回等流程，
 * 用于需要基于身份 证、银行卡等实体卡或证，采集照片或文字信息等的业务场景。
 * <p>
 * <p>
 * 文件大小限制：小于2M
 */
public interface Ocr extends We {
    /**
     * 身份证正面
     *
     * @param imgUrl
     * @return
     */
    IdCardFront idCardFront(String imgUrl);

    /**
     * 身份证反面
     *
     * @param imgUrl
     * @return
     */
    IdCardBack idCardBack(String imgUrl);
}
