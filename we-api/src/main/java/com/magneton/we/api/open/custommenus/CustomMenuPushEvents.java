package com.magneton.we.api.open.custommenus;

/**
 * 微信公众号-自定义菜单-事件推送
 *
 * https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Custom_Menu_Push_Events.html
 *
 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。请注意，第3个到第8个的所有事件，仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送。
 *
 * 目录
 *
 * 1 点击菜单拉取消息时的事件推送
 *
 * 2 点击菜单跳转链接时的事件推送
 *
 * 3 scancode_push：扫码推事件的事件推送
 *
 * 4 scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送
 *
 * 5 pic_sysphoto：弹出系统拍照发图的事件推送
 *
 * 6 pic_photo_or_album：弹出拍照或者相册发图的事件推送
 *
 * 7 pic_weixin：弹出微信相册发图器的事件推送
 *
 * 8 location_select：弹出地理位置选择器的事件推送
 *
 * 9 点击菜单跳转小程序的事件推送
 *
 * @author zhangmingshuang
 * @since 2020/3/5
 */
public interface CustomMenuPushEvents {

    /**
     * 点击菜单拉取消息时的事件推送
     */
    String CLICK = "CLICK";
    /**
     * 点击菜单跳转链接时的事件推送
     */
    String VIEW = "VIEW";
    /**
     * 扫码推事件的事件推送
     */
    String SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件推送
     */
    String SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图的事件推送
     */
    String PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图的事件推送
     */
    String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器的事件推送
     */
    String PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器的事件推送
     */
    String LOCATION_SELECT = "location_select";
    /**
     * 点击菜单跳转小程序的事件推送
     */
    String VIEW_MINIPROGRAM = "view_miniprogram";
}
