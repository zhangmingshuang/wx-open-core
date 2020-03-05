package com.magneton.open.wx.api.open.messagemanagement;

/**
 * 微信公众号-消息管理-接收普通消息
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html
 *
 * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
 *
 * 请注意：
 *
 * 1.关于重试的消息排重，推荐使用msgid排重。
 * 2.微信服务器在五秒内收不到响应会断掉连接，
 * 并且重新发起请求，总共重试三次。假如服务器无法保证在五秒内处理并回复，
 * 可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。详情请见“发送消息-被动回复消息”。
 * 3.如果开发者需要对用户消息在5秒内立即做出回应，
 * 即使用“发送消息-被动回复消息”接口向用户被动回复消息时，可以在公众平台官网的开发者中心处设置消息加密。
 * 开启加密后，用户发来的消息和开发者回复的消息都会被加密
 * （但开发者通过客服接口等API调用形式向用户发送消息，则不受影响）。
 * 关于消息加解密的详细说明，请见“发送消息-被动回复消息加解密说明”
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public class ReceivingStandardMessages {

}
