//package com.magneton.open.wx.api.invoker.http;
//
//import com.magneton.open.wx.api.open.messagemanagement.ServiceCenterMessage;
//import com.magneton.open.wx.api.core.MsgReply;
//import com.magneton.open.wx.api.open.messagemanagement.MsgReplyInvoker;
//import com.magneton.open.wx.api.core.WeEnvironment;
//import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
//import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
//import com.magneton.open.wx.api.util.StringUtil;
//
///**
// * 当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），
// * 微信将会把消息数据推送给开发者，开发者可以在一段时间内（目前修改为48小时）调用客服接口，
// * 通过POST一个JSON数据包来发送消息给普通用户。
// *
// * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
// *
// * 目前允许的动作列表如下
// * （公众平台会根据运营情况更新该列表，不同动作触发后，允许的客服接口下发消息条数不同，
// * 下发条数达到上限后，会遇到错误返回码，具体请见返回码说明页）：
// *
// * {@code
// * <pre>
// *  1、用户发送信息
// *  2、点击自定义菜单（仅有点击推事件、扫码推事件、扫码推事件
// *          且弹出“消息接收中”提示框这3种菜单类型是会触发客服接口的）
// *  3、关注公众号
// *  4、扫描二维码
// *  5、支付成功
// *  6、用户维权
// * </pre>
// * }
// *
// * @author zhangmingshuang
// * @since 2019/11/6
// */
//public class HttpServiceCenterMessage extends AbstractorHttpInvoker
//    implements ServiceCenterMessage {
//
//    private static final String URL_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={}";
//
//    public HttpServiceCenterMessage(WeEnvironment environment) {
//        super(environment);
//    }
//
//    @Override
//    public boolean send(MsgReply msgReply) {
//
//        String accessToken = getWeEnvironment().getAccessTokenInvoker().getAccessToken();
//
//        String url = StringUtil.format(URL_SEND, accessToken);
//
//        HttpResponse httpResponse = HttpRequest.doRequest(url, msgReply.getData());
//
//        return httpResponse.isSuccess();
//    }
//}
