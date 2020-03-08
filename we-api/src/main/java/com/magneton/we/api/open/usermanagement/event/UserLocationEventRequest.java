package com.magneton.we.api.open.usermanagement.event;

import com.magneton.we.api.open.messagemanagement.event.AbstractEventRequest;
import com.magneton.we.api.util.StringUtil;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 上报地理位置事件
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/Gets_a_users_location.html
 * <p>
 * 用户同意上报地理位置后，每次进入公众号会话时，
 * 都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，
 * 公众号可以在公众平台网站中修改以上设置。上报地理位置时，
 * 微信会将上报地理位置事件推送到开发者填写的URL。
 * <p>
 * 开通了上报地理位置接口的公众号，用户在关注后进入公众号会话时，
 * 会弹框让用户确认是否允许公众号使用其地理位置。弹框只在关注后出现一次，
 * 用户以后可以在公众号详情页面进行操作。
 * <p>
 * 第三方在收到地理位置上报信息之后，
 * 只需要回复success表明收到即可，是不允许回复消息给粉丝的。
 *
 * @author zhangmingshuang
 * @since 2020/3/6
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UserLocationEventRequest extends AbstractEventRequest {

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
    public UserLocationEventRequest parse(Map<String, String> params) {
        super.parse(params);
        this.latitude = StringUtil.getDoubleVal(params.get("Latitude"));
        this.longitude = StringUtil.getDoubleVal(params.get("Longitude"));
        this.precision = StringUtil.getDoubleVal(params.get("Precision"));
        return this;
    }
}
