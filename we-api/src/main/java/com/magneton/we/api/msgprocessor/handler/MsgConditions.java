package com.magneton.we.api.msgprocessor.handler;

import java.lang.annotation.*;

/**
 * 多条件组合
 * <pre>
 *      \@MsgConditions({
 *          \@MsgCondition(key="test", eq="value"),
 *          \@MsgCondition(key="test2", eq="value2")
 *      })
 *      相当于判断
 *      String value = xxx.get("test");
 *      String value2 = xxx.get("test2");
 *      if("value".equals(value) 且 "value2".equals(value2)){
 *          //do something
 *      }
 * </pre>
 *
 * @author zhangmingshuang
 * @see MsgCondition
 * @since 2019/9/4
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MsgConditions {

    MsgCondition[] value();
}
