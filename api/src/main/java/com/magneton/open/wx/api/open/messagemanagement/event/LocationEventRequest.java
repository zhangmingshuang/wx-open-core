package com.magneton.open.wx.api.open.messagemanagement.event;

import com.magneton.open.wx.api.util.StringUtil;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 上报地理位置事件
 *
 * 用户同意上报地理位置后，每次进入公众号会话时，
 * 都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，
 * 公众号可以在公众平台网站中修改以上设置。上报地理位置时，
 * 微信会将上报地理位置事件推送到开发者填写的URL。
 *
 * @author zhangmingshuang
 * @since 2020/3/6
 */
@Setter
@Getter
@ToString(callSuper = true)
public class LocationEventRequest extends AbstractEventRequest {

    /**
     * 地理位置纬度
     */
    private double latitude;
    /**
     * 地理位置经度
     */
    private double longitude;
    /**
     * 地理位置精度
     */
    private double precision;

    @Override
    public LocationEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.latitude = StringUtil.getDoubleVal(params.get("Latitude"));
        this.longitude = StringUtil.getDoubleVal(params.get("Longitude"));
        this.precision = StringUtil.getDoubleVal(params.get("Precision"));
        return this;
    }
}
