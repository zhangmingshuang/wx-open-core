package com.magneton.we.api.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangmingshuang
 * @since 2020/3/1
 */
public class StringUtil {

    public static String format(String str, Object... args) {
        char[] chars = str.toCharArray();
        int l = str.length(), i = 0;
        StringBuilder builder = new StringBuilder(l + (l >> 1));
        byte skip = 0;
        for (char c : chars) {
            switch (c) {
                case '{':
                    skip = 1;
                    break;
                case '}':
                    if (skip == 1) {
                        //{}
                        builder.append(args[i++]);
                    }
                    skip = 0;
                    break;
                default:
                    if (skip == 0) {
                        builder.append(c);
                    } else if (skip == 1) {
                        builder.append(args[i++]);
                        skip = 2;
                    }
            }
        }
        return builder.toString();
    }

    public static boolean isEmpty(String cs) {
        return cs == null || cs.length() == 0;
    }

    public static long getLongVal(String val) {
        if (isEmpty(val)) {
            return 0L;
        }
        return Long.parseLong(val);
    }

    public static double getDoubleVal(String val) {
        if (isEmpty(val)) {
            return 0D;
        }
        return Double.parseDouble(val);
    }

    public static int getIntVal(String val) {
        if (isEmpty(val)) {
            return 0;
        }
        return Integer.parseInt(val);
    }
}
