package com.magneton.open.wx.api.open.messagemanagement;

import com.magneton.open.wx.api.open.messagemanagement.msg.MsgReply;
import com.magneton.open.wx.api.invoker.Invoker;

/**
 * @author zhangmingshuang
 * @since 2019/11/6
 */
public interface CustomMessageInvoker extends Invoker {

    boolean send(MsgReply msgReply);
}
