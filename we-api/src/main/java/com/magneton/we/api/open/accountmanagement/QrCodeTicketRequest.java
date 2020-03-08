package com.magneton.we.api.open.accountmanagement;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 二维码
 * https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html
 * <p>
 * 临时二维码请求说明
 * <p>
 * http请求方式: POST URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
 * POST数据格式：json POST数据例子：
 * {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}} 或者也可以使用以下POST数据创建字符串形式的二维码参数：{"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
 * <p>
 * 永久二维码请求说明
 * <p>
 * http请求方式: POST URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
 * POST数据格式：json POST数据例子：
 * {"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
 * 或者也可以使用以下POST数据创建字符串形式的二维码参数：
 * {"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
 */
@Setter
@Getter
@ToString
public class QrCodeTicketRequest {
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    @JSONField(name = "expire_seconds")
    private int expireSeconds;
    /**
     * 二维码类型
     */
    @JSONField(name = "action_name")
    private ActionName actionName;
    /**
     * 二维码详细信息
     */
    @JSONField(name = "action_info")
    private String actionInfo;
    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    @JSONField(name = "scene_id")
    private int sceneId;
    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    @JSONField(name = "scene_str")
    private String sceneStr;

    enum ActionName {
        /**
         * 为临时的整型参数值，
         */
        QR_SCENE,
        /**
         * 为临时的字符串参数值，
         */
        QR_StR_SCENE,
        /**
         * 为永久的整型参数值，
         */
        QR_LIMIT_SCENE,
        /**
         * 为永久的字符串参数值
         */
        QR_LIMIT_STR_SCENE
    }
}
