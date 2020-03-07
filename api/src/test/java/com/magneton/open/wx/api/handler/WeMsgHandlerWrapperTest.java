package com.magneton.open.wx.api.handler;

import com.magneton.open.wx.api.msgprocessor.handler.WeMsgHandler;
import com.magneton.open.wx.api.msgprocessor.handler.MsgHandlerWrapper;
import com.magneton.open.wx.api.core.MsgReply;
import com.magneton.open.wx.api.open.messagemanagement.msg.TextMsgRequest;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public class WeMsgHandlerWrapperTest {

    private class T implements WeMsgHandler<TextMsgRequest> {

        @Override
        public MsgReply handle(TextMsgRequest msg) {
            System.out.println(msg);
            return null;
        }
    }

    @Test
    public void test() {
        MsgHandlerWrapper msgHandlerWrapper = new MsgHandlerWrapper(new T());
        msgHandlerWrapper.parse();
        boolean assignableFrom = msgHandlerWrapper
            .getGenericHandlerClass().isAssignableFrom(TextMsgRequest.class);
        Assert.assertTrue(assignableFrom);

        msgHandlerWrapper.handle(new HashMap<>());

    }
}
