package com.magneton.open.wx.api.handler;


import java.lang.annotation.*;

/**
 * 消息条件，相当于if
 * <pre>
 *      \@MsgCondition(key="test", eq="value")
 *      相当于判断
 *      String value = xxx.get("test");
 *      if("value".equals(value)){
 *              //do something
 *      }
 * </pre>
 *
 * @author zhangmingshuang
 * @since 2019/9/4
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MsgCondition {
    String key();

    String eq();
}
