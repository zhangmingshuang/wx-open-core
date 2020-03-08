package com.magneton.we.api.msgprocessor.handler;

import java.util.Map;

/**
 * 转换微信公众号推送过来的消息，是一个大而全的实体，则不是根据业务定义
 *
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public interface WeMsgParser<T> {

    T parse(Map<String, String> kvs);
}
