//package com.magneton.open.wx.api.entity.custom;
//
///**
// * 客服消息-图文消息
// *
// * 发送图文消息（点击跳转到外链） 图文消息条数限制在1条以内，
// * 注意，如果图文数超过1，则将会返回错误码45008。
// *
// * @author zhangmingshuang
// * @since 2019/11/6
// */
//public class NewsCustomMsg implements CustomMsg {
//
//    private String toUser;
//    /**
//     * 标题
//     */
//    private String title;
//    /**
//     * 内容描述
//     */
//    private String description;
//    /**
//     * 链接地址
//     */
//    private String url;
//    /**
//     * 图片地址
//     */
//    private String picUrl;
//
//    @Override
//    public String toJSON() {
//        return "{"
//            + "\"touser\":\"" + toUser + "\","
//            + "\"msgtype\":\"news\","
//            + "\"news\":{"
//            + "\"articles\": ["
//            + "{"
//            + "\"title\":\"" + title + "\","
//            + "\"description\":\"" + description + "\","
//            + "\"url\":\"" + url + "\","
//            + "\"picurl\":\"" + picUrl + "\""
//            + "}"
//            + "]"
//            + "}"
//            + "}";
//    }
//}
