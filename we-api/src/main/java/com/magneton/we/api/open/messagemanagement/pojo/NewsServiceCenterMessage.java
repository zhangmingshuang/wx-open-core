package com.magneton.we.api.open.messagemanagement.pojo;

import com.magneton.we.api.open.basic.pojo.Articles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服接口-发消息
 *
 * 发送图文消息（点击跳转到外链）
 * 图文消息条数限制在1条以内，注意，如果图文数超过1，则将会返回错误码45008。
 *
 * @author zhangmingshuang
 * @since 2020/3/7
 */
@Setter
@Getter
@ToString(callSuper = true)
public class NewsServiceCenterMessage extends AbstractServiceCenterMessage {

    private News news;

    @Override
    public String getMsgType() {
        return "news";
    }

    @Setter
    @Getter
    @ToString
    public static class News {

        private Articles articles;
    }
}
