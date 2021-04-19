package com.miaosha.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 16:51
 */
public class ValidatorUtil {

    private static final Pattern mobile_pattren = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattren.matcher(src);
        return m.matches();
    }

}
