package com.magneton.open.wx.api.open.messagemanagement.msg;

import com.magneton.open.wx.api.core.MsgReply;
import com.magneton.open.wx.api.util.StringUtil;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@Getter
@Setter
@ToString
public abstract class AbstractMsg implements MsgReply {

    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String nonce;

    @Override
    public String getTimeStamp() {
        return getCreateTime();
    }

    public String getCreateTime() {
        if (StringUtil.isEmpty(createTime)) {
            this.createTime = String.valueOf(System.currentTimeMillis() / 1000);
        }
        return createTime;
    }

    @Override
    public String getNonce() {
        if (StringUtil.isEmpty(nonce)) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            this.nonce = String.valueOf(random.nextInt(10000, 90000));
        }
        return nonce;
    }

}
