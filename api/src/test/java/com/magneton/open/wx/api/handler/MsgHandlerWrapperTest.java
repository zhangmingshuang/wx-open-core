package com.magneton.open.wx.api.handler;

import com.magneton.open.wx.api.entity.msg.WxMsg;
import com.magneton.open.wx.api.open.messagemanagement.msg.TextMsgRequest;
import java.util.HashMap;
import javax.xml.soap.Text;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public class MsgHandlerWrapperTest {

    private class T implements MsgHandler<TextMsgRequest> {

        @Override
        public WxMsg handle(TextMsgRequest msg) {
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
