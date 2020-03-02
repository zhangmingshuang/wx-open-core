package com.magneton.open.wx.api.invoker;

import com.magneton.open.wx.api.entity.custom.CustomMsg;

/**
 * @author zhangmingshuang
 * @since 2019/11/6
 */
public interface CustomMessageInvoker extends Invoker {

    boolean send(CustomMsg customMsg);
}
