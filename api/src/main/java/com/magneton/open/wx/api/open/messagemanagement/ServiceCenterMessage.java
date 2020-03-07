package com.magneton.open.wx.api.open.messagemanagement;

import com.magneton.open.wx.api.open.Invoker;

/**
 * 客服消息
 *
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Service_Center_messages.html
 *
 * 当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），
 * 微信将会把消息数据推送给开发者，开发者可以在一段时间内（目前修改为48小时）调用客服接口，
 * 通过POST一个JSON数据包来发送消息给普通用户。
 * 此接口主要用于客服等有人工消息处理环节的功能，
 * 方便开发者为用户提供更加优质的服务。
 *
 * 目前允许的动作列表如下
 * （公众平台会根据运营情况更新该列表，不同动作触发后，允许的客服接口下发消息条数不同，
 * 下发条数达到上限后，会遇到错误返回码，具体请见返回码说明页）：
 *
 * 1、用户发送信息
 * 2、点击自定义菜单（仅有点击推事件、扫码推事件、
 * 扫码推事件且弹出“消息接收中”提示框这3种菜单类型是会触发客服接口的）
 * 3、关注公众号
 * 4、扫描二维码
 * 5、支付成功
 * 6、用户维权
 * 为了帮助公众号使用不同的客服身份服务不同的用户群体，客服接口进行了升级，
 * 开发者可以管理客服账号，并设置客服账号的头像和昵称。
 * 该能力针对所有拥有客服接口权限的公众号开放。
 *
 * 另外，请开发者注意，本接口中所有使用到media_id的地方，现在都可以使用素材管理中的永久素材media_id了。
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
public interface ServiceCenterMessage extends Invoker {

}
