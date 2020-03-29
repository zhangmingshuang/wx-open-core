package com.magneton.we.api.open.assetmanagement;

/**
 * 素材管理
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html
 *
 * <pre>
 * 公众号经常有需要用到一些临时性的多媒体素材的场景，
 * 例如在使用接口特别是发送消息时，对多媒体文件、多媒体消息的获取和调用等操作，
 * 是通过media_id来进行的。
 *
 * 素材管理接口对所有认证的订阅号和服务号开放。
 * 通过本接口，公众号可以新增临时素材（即上传临时多媒体文件）。
 *
 * 注意点：
 * 1、临时素材media_id是可复用的。
 * 2、媒体文件在微信后台保存时间为3天，即3天后media_id失效。
 * 3、上传临时素材的格式、大小限制与公众平台官网一致。
 * 图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式
 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
 * 视频（video）：10MB，支持MP4格式
 * 缩略图（thumb）：64KB，支持JPG格式
 * 4、需使用https调用本接口。
 * </pre>
 */
public interface Materials {

}
