package com.magneton.we.api.event;

import java.lang.annotation.*;

/**
 * 处理事件匹配
 *
 * @author zhangmingshuang
 * @since 2019/9/6
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventCondition {

    String DEFAULT = "*";

    String event() default DEFAULT;

    String eventKey() default DEFAULT;


}
