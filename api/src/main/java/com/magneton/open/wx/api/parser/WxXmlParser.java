package com.magneton.open.wx.api.parser;


import com.magneton.open.wx.api.util.StringUtil;
import java.util.Map;

/**
 * @author zhangmingshuang
 * @since 2019/9/4
 */
public abstract class WxXmlParser {

    private static final WxXmlParser PARSER = new DefaultWxXmlPartser();

    public static final Map<String, String> parse(String xml) throws Exception {
        if (StringUtil.isEmpty(xml)) {
            return null;
        }
        return PARSER.doParse(xml);
    }

    abstract Map<String, String> doParse(String xml) throws Exception;
}
