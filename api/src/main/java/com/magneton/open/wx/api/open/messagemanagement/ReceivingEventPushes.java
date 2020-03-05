package com.magneton.open.wx.api.open.messagemanagement;

import com.magneton.open.wx.api.open.custommenus.CustomMenuPushEvents;

/**
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_event_pushes.html
 *
 * 在微信用户和公众号产生交互的过程中，
 * 用户的某些操作会使得微信服务器通过事件推送的形式通知到开发者在开发者中心处设置的服务器地址，
 * 从而开发者可以获取到该信息。其中，某些事件推送在发生后，是允许开发者回复用户的，
 * 某些则不允许，详细内容如下：
 *
 * 目录
 *
 * 1 关注/取消关注事件
 *
 * 2 扫描带参数二维码事件
 *
 * 3 上报地理位置事件
 *
 * 4 自定义菜单事件
 *
 * 5 点击菜单拉取消息时的事件推送
 *
 * 6 点击菜单跳转链接时的事件推送
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public interface ReceivingEventPushes {

    /**
     * 1. 关注/取消关注事件
     * 用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。
     * 方便开发者给用户下发欢迎消息或者做帐号的解绑。为保护用户数据隐私，
     * 开发者收到用户取消关注事件时需要删除该用户的所有信息。
     *
     * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。
     *
     * 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
     *
     * 假如服务器无法保证在五秒内处理并回复，可以直接回复空串，
     * 微信服务器不会对此作任何处理，并且不会发起重试。
     *
     * 2. 扫描带参数二维码事件 用户未关注时，进行关注后的事件推送
     */
    String SUBSCRIBE = "subscribe";
    /**
     * 取消关注事件
     */
    String UNSUBSCRIBE = "unsubscribe";
    /**
     * 扫描带参数二维码事件  用户已关注时的事件推送
     */
    String SCAN = "SCAN";
    /**
     * 上报地理位置事件
     *
     * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，
     * 或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。
     *
     * 上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
     *
     * 与接收普通消息中时有一个 {@link CustomMenuPushEvents#LOCATION_SELECT}请注意区别
     */
    String LOCATION = "LOCATION";
    /**
     * 自定义菜单事件 {@link CustomMenuPushEvents#CLICK}
     *
     * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
     *
     * 点击菜单拉取消息时的事件推送
     */
    String CLICK = "CLICK";
    /**
     * 自定义菜单事件
     *
     * 点击菜单跳转链接时的事件推送
     */
    String VIEW = "VIEW";
}
