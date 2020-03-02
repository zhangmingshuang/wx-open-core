package com.magneton.open.wx.api.constant;

/**
 * @author zhangmingshuang
 * @since 2019/9/5
 */
public interface DispatcherRedisConstant {
    byte[] WX_ACCESS_TOKEN = "wx:dispatcher:accessToken".getBytes();
    byte[] WX_JS_TICKET = "wx:dispatcher:jsticket".getBytes();
    byte[] WX_ACCESS_TOKEN_ROB = "wx:dispatcher:accessToken:rob".getBytes();
}
