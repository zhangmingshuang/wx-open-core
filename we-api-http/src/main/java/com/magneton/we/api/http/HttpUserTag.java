package com.magneton.we.api.http;

import com.alibaba.fastjson.JSONObject;
import com.magneton.we.api.core.WeEnvironment;
import com.magneton.we.api.core.WeParamException;
import com.magneton.we.api.http.core.HttpResponse;
import com.magneton.we.api.http.core.HttpRequest;
import com.magneton.we.api.open.usermanagement.UserTag;
import com.magneton.we.api.open.usermanagement.pojo.Tag;
import com.magneton.we.api.open.usermanagement.pojo.TagFans;
import com.magneton.we.api.util.StringUtil;

import java.util.List;

public class HttpUserTag extends AbstractorHttpWe
        implements UserTag {

    private static final String CREATE = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token={}";
    private static final String GET = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token={}";
    private static final String UPDATE = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token={}";
    private static final String DELETE = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token={}";
    private static final String GET_FANS = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token={}";
    private static final String BATCH_ADDING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token={}";
    private static final String BATCH_UNADDING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token={}";
    private static final String GET_ID_LIST = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token={}";

    public HttpUserTag(WeEnvironment weEnvironment) {
        super(weEnvironment);
    }

    @Override
    public List<String> getIdList(String openId) {
        String url = this.getUrl(GET_ID_LIST);
        String jsonData = "{\"openid\":" + openId + "\"}";
        HttpResponse result = HttpRequest.doRequest(url, jsonData);
        if (!result.isSuccess()) {
            return null;
        }
        return result.getJSONArray("tagid_list").toJavaList(String.class);
    }


    @Override
    public boolean batchUntagging(long id, String[] openIds) {
        return this.batchTag(BATCH_UNADDING, id, openIds);
    }

    @Override
    public boolean batchAgging(long id, String[] openIds) {
        return this.batchTag(BATCH_ADDING, id, openIds);
    }

    private boolean batchTag(String batchUrl, long id, String[] openIds) {
        if (openIds == null || openIds.length < 1
                || openIds.length > 50) {
            throw new WeParamException("粉丝列表不能为空或者超过50个");
        }

        String url = this.getUrl(batchUrl);
        JSONObject obj = new JSONObject();
        obj.put("openid_list", openIds);
        obj.put("tagid", id);
        HttpResponse result = HttpRequest.doRequest(url, obj.toJSONString());
        return result.isSuccess();
    }

    @Override
    public TagFans getFans(long id, String nextOpenId) {
        if (nextOpenId == null) {
            nextOpenId = "";
        }
        String url = this.getUrl(GET_FANS);
        String jsonData = "{\"tagid\":" + id + "\"next_openid\":\"" + nextOpenId + "\"}";
        HttpResponse result = HttpRequest.doRequest(url, jsonData);
        if (!result.isSuccess()) {
            return null;
        }
        return result.toJavaObject(TagFans.class);
    }

    @Override
    public boolean delete(long id) {
        String url = this.getUrl(DELETE);
        String jsonData = "{\"tag\":{\"id\":" + id + "}}";
        HttpResponse result = HttpRequest.doRequest(url, jsonData);
        return result.isSuccess();
    }

    @Override
    public boolean update(long id, String name) {
        this.assertName(name);
        String url = this.getUrl(UPDATE);
        String jsonData = "{\"tag\":{\"id\":" + id + ",\"name\":\"" + name + "\"}}";
        HttpResponse result = HttpRequest.doRequest(url, jsonData);
        return result.isSuccess();
    }

    @Override
    public List<Tag> get() {
        String url = this.getUrl(CREATE);
        HttpResponse result = HttpRequest.doRequest(url);
        if (!result.isSuccess()) {
            return null;
        }
        return result.getJSONArray("tags").toJavaList(Tag.class);
    }

    @Override
    public Tag create(String name) {
        this.assertName(name);
        String url = this.getUrl(CREATE);
        String jsonData = "{\"tag\":{\"name\":\"" + name + "\"}}";
        HttpResponse result = HttpRequest.doRequest(url, jsonData);
        if (!result.isSuccess()) {
            return null;
        }
        return result.getJSONObject("tag").toJavaObject(Tag.class);
    }

    private String getUrl(String url) {
        String accessToken = getWeEnvironment().accessToken().getAccessToken();
        return StringUtil.format(url, accessToken);
    }

    private void assertName(String name) {
        if (StringUtil.isEmpty(name)
                || name.length() > 30) {
            throw new WeParamException("参数name不能为空或者长度不能超过30");
        }
    }

}
