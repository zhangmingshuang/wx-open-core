package com.magneton.open.wx.api.open.messagemanagement.msg;

import com.magneton.open.wx.api.util.StringUtil;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
@Setter
@Getter
@ToString
public class LocationMsgRequest extends AbstractRequestMsg {

    /**
     * 地理位置维度
     */
    private double locationX;
    /**
     * 地理位置经度
     */
    private double locationY;
    /**
     * 地图缩放大小
     */
    private int scale;
    /**
     * 地理位置信息
     */
    private String label;

    @Override
    public LocationMsgRequest parse(Map<String, String> params) {
        super.parse(params);
        this.locationX = StringUtil.getDoubleVal(params.get("Location_X"));
        this.locationY = StringUtil.getDoubleVal(params.get("Location_Y"));
        this.scale = StringUtil.getIntVal(params.get("Scale"));
        this.label = params.get("Label");
        return this;
    }
}
