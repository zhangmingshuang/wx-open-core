package com.magneton.we.api.open.intelligentinterface.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 身份证正面
 *
 * <pre>
 * {@code
 * {
 *     "errcode": 0,
 *     "errmsg": "ok",
 *     "type": "Front", //正面
 *     "name": "张三", //姓名
 *     "id": "123456789012345678", //身份证号码
 *     "addr": "广东省广州市XXX", //住址
 *     "gender": "男", //性别
 *     "nationality": "汉" //民族
 * }
 * }
 * </pre>
 */
@Setter
@Getter
@ToString
public class IdCardFront {
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号码
     */
    private String id;
    /**
     * 住址
     */
    private String addr;
    /**
     * 性别
     */
    private String gender;
    /**
     * 民族
     */
    private String nationality;
}
