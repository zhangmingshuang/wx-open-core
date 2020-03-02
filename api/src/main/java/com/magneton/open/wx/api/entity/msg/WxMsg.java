package com.magneton.open.wx.api.entity.msg;

import com.magneton.service.core.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@Getter
@Setter
@ToString
public abstract class WxMsg {

    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String nonce;

    public abstract String toXml();


    public String getCreateTime() {
        if (StringUtil.isEmpty(createTime)) {
            this.createTime = String.valueOf(System.currentTimeMillis() / 1000);
        }
        return createTime;
    }

    public String getNonce() {
        if (StringUtil.isEmpty(nonce)) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            this.nonce = String.valueOf(random.nextInt(10000, 90000));
        }
        return nonce;
    }

}
