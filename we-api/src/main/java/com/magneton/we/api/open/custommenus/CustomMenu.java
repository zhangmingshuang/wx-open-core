package com.magneton.we.api.open.custommenus;

import com.alibaba.fastjson.JSONObject;
import com.magneton.we.api.open.We;

/**
 * https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html
 *
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public interface CustomMenu extends We {

    /**
     * 菜单查询
     *
     * @return 查询到的菜单数据
     */
    JSONObject query();

    /**
     * 创建菜单
     * 注意： 每次创建都应该是完整的菜单数据，因为每次提交都会覆盖旧创建的菜单
     *
     * @param menu 要创建的菜单数据
     * @return 是否创建成功
     */
    boolean create(String menu);
}
