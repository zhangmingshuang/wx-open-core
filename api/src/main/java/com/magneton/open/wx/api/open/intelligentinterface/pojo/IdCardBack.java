package com.magneton.open.wx.api.open.intelligentinterface.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Ocr
 * <p>
 * 身份证背面
 * <pre>
 * {@code
 * {
 *     "errcode": 0,
 *     "errmsg": "ok",
 *     "type": "Back", //背面
 *     "valid_date": "20171025-20271025", //有效期
 * }
 * }
 * </pre>
 */
@Setter
@Getter
@ToString
public class IdCardBack {
    /**
     * 有效期
     */
    @JSONField(name = "valid_date")
    private String validDate;
}
