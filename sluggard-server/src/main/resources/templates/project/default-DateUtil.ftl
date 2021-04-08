package ${project.basePackage}.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private DateUtil() {
    }

    /**
     * 默认时区类型：东八区
     */
    public static final String DEFAULT_TIME_ZONE_TYPE = "GMT+8";
    /**
     * 默认日期和时间格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_FORMAT_PATTERN_TIME = "HH:mm:ss";

    /**
     * 获取当前系统毫秒级时间戳
     *
     * @return 当前系统毫秒级时间戳
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前系统日期和时间
     *
     * @return 当前系统日期和时间
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取指定格式的当前系统日期和时间的字符串
     *
     * @param pattern 日期和时间格式，若为空则使用默认的格式
     * @return 指定格式的当前系统日期和时间的字符串
     */
    public static String getFormatCurrentDate(String pattern) {
        if (Objects.isNull(pattern)) {
            pattern = DEFAULT_FORMAT_PATTERN_DATE;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(getCurrentDate());
    }

    /**
     * 将指定日期初始化为日历
     *
     * @param date 日期
     * @return 日历
     */
    private static Calendar initCalendar(Date date) {
        Calendar temCalendar = Calendar.getInstance();
        temCalendar.setTime(date);
        return temCalendar;
    }

    /**
     * 获取指定日期当月的月底日期
     *
     * @param date 日期
     * @return 月底日期
     */
    public static Date getEndOfMonth(Date date) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.set(Calendar.DAY_OF_MONTH, temCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return temCalendar.getTime();
    }

    /**
     * 计算加指定天数后的日期
     *
     * @param date 日期
     * @param days 天数
     * @return 指定天数后的日期
     */
    public static Date afterDay(Date date, int days) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.DATE, days);
        return temCalendar.getTime();
    }

    /**
     * 计算加指定分钟后的时间
     *
     * @param date 时间
     * @param minute 分钟
     * @return 指定分钟后的时间
     */
    public static Date afterMinute(Date date, int minute) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.MINUTE, minute);
        return temCalendar.getTime();
    }

    /**
     * 计算加指定秒钟后的时间
     *
     * @param date 时间
     * @param second 秒钟
     * @return 指定秒钟后的时间
     */
    public static Date afterSecond(Date date, int second) {
        Calendar temCalendar = initCalendar(date);
        temCalendar.add(Calendar.SECOND, second);
        return temCalendar.getTime();
    }

    /**
     * 将日期格式的字符串转换为日期
     *
     * @param dateStr 字符串
     * @param dateFormat 日期格式
     * @return 日期
     */
    public static Date dateStrToDate(String dateStr, String dateFormat) {
        if (Objects.isNull(dateStr)) {
            return null;
        }
        if (Objects.isNull(dateFormat)) {
            dateFormat = DEFAULT_FORMAT_PATTERN_DATETIME;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param dateStr 日期格式字符串
     * @param dateFormat 日期格式
     * @return 长整型时间戳
     */
    public static long dateStrToLong(String dateStr, String dateFormat) {
        Date date = dateStrToDate(dateStr, dateFormat);
        return date == null ? 0L : date.getTime();
    }

    /**
     * 将日期转换为字符串
     *
     * @param date 日期
     * @param dateFormat 日期格式
     * @return 字符串
     */
    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 获取一个日期类型的年月日
     *
     * @param date 日期
     * @return 只包含年月日的日期
     */
    public static Date getDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_FORMAT_PATTERN_DATE);
        try {
            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取两个日期相差天数
     *
     * @param start 起始日期
     * @param end 截止日期
     * @return 相差天数
     */
    public static long getDatePeriod(Date start, Date end) {
        Date startDay = getDay(start);
        Date endDay = getDay(end);
        if (startDay == null || endDay == null) {
            return -1L;
        }
        return Math.abs((startDay.getTime() - endDay.getTime()) / (1000L * 60L * 60L * 24L));
    }

    /**
     * 获取两个时间的间隔
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 返回相差毫秒数
     */
    public static long getTimePeriod(Date start, Date end) {
        return end.getTime() - start.getTime();
    }
}
