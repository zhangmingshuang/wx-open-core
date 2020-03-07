package com.magneton.open.wx.api.invoker.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magneton.open.wx.api.open.custommenus.CustomMenu;
import com.magneton.open.wx.api.core.WeEnvironment;
import com.magneton.open.wx.api.invoker.http.core.HttpRequest;
import com.magneton.open.wx.api.invoker.http.core.HttpResponse;
import com.magneton.open.wx.api.invoker.http.core.RequestProxy;
import com.magneton.open.wx.api.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 菜单操作
 *
 * @author zhangmingshuang
 * @see https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Querying_Custom_Menus.html
 * @since 2019/9/6
 */
public class HttpCustomMenu extends AbstractorHttpInvoker
    implements CustomMenu {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCustomMenu.class);

    private static final String URL_MENU_QUERY
        = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token={}";

    private static final String URL_MENU_CREATE
        = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={}";

    public HttpCustomMenu(WeEnvironment environment) {
        super(environment);
    }

    /**
     * 查询微信公众号菜单
     * <pre>
     * is_menu_open	菜单是否开启，0代表未开启，1代表开启
     * selfmenu_info	菜单信息
     * button	菜单按钮
     * type	菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice。使用API设置的则有8种，详见《自定义菜单创建接口》
     * name	菜单名称
     * value、url、key等字段	对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单： Text:保存文字到value； Img、voice：保存mediaID到value； Video：保存视频下载链接到value； News：保存图文消息到news_info，同时保存mediaID到value； View：保存链接到url。 使用API设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、	pic_weixin、location_select：保存值到key；view：保存链接到url
     * news_info	图文消息的信息
     * title	图文消息的标题
     * digest	摘要
     * author	作者
     * show_cover	是否显示封面，0为不显示，1为显示
     * cover_url	封面图片的URL
     * content_url	正文的URL
     * source_url	原文的URL，若置空则无查看原文入口
     * </pre>
     *
     * @return 查询结果
     */
    @Override
    public JSONObject query() {
        String accessToken = getEnvironment().accessToken().getAccessToken();

        String url = StringUtil.format(URL_MENU_QUERY, accessToken);

        HttpResponse result = HttpRequest.doRequest(url);
        if (!result.isSuccess()) {
            return null;
        }
        return result;
    }

    @Override
    public boolean create(String menu) {
        String accessToken = getEnvironment().accessToken().getAccessToken();

        String url = StringUtil.format(URL_MENU_CREATE, accessToken);
        return this.doCreate(url, menu);
    }


    /**
     * 创建菜单
     *
     * <pre>
     * button	是	一级菜单数组，个数应为1~3个
     * sub_button	否	二级菜单数组，个数应为1~5个
     * type	是	菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     * name	是	菜单标题，不超过16个字节，子菜单不超过60个字节
     * key	click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节
     * url	view、miniprogram类型必须	网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     * media_id	media_id类型和view_limited类型必须	调用新增永久素材接口返回的合法media_id
     * appid	miniprogram类型必须	小程序的appid（仅认证公众号可配置）
     * pagepath	miniprogram类型必须	小程序的页面路径
     * </pre>
     *
     * @param url  地址
     * @param menu 菜单信息
     * @return 是否创建成功
     */
    protected boolean doCreate(String url, String menu) {

        try {
            //todo monitor
            String response = RequestProxy.doPost(url, menu);
            JSONObject json = JSON.parseObject(response);
            Integer errcode = json.getInteger("errcode");

            if (errcode != null && errcode.intValue() != 0) {
                LOGGER.info("[weixin]doCreate fail. {}", response);
                return false;
            }
            return true;
        } catch (IOException e) {
            LOGGER.error("[wx]请求" + url.substring(0, url.indexOf('?')) + "异常", e);
        }
        return false;
    }


}
