package com.mylq.sluggard.core.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author WangRunQian
 * @date 2019/12/4
 * @since 1.0.0
 */
public class DateUtil {

    private DateUtil() {
    }

    /**
     * 获取当前日期的字符串形式
     *
     * @return 当前日期的字符串形式
     */
    public static String getNowDateStr() {

        DateFormat df = new SimpleDateFormat("yyyy/M/d");
        return df.format(new Date());
    }
}
