package net.daylong.baselibrary.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_HH_MM = " HH:mm";
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat datetimeFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat TIME_H_M_FORMAT = new SimpleDateFormat("HH:mm");

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    private static final int m = 1;
    private static final int f = m * 60;
    private static final int s = f * 60;
    public static final int DAY_TIME = s * 24;

    public static long getLongTime() {
        return System.currentTimeMillis();
    }

    //    放假日期
    private static String[] holidayDate = {
            "01-01",//元旦
            "01-24", "01-25", "01-26", "01-27", "01-28", "01-29", "01-30", //春节
            "04-04", "04-05", "04-06",//清明
            "05-01", "05-02", "05-03", "05-04", "05-05",//劳动节
            "06-25", "06-26", "06-27", //端午节
            "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07", "10-08",//国庆+中秋
    };
    //    加班日期
    private static String[] OTDate = {
            "01-19", "02-01",//春节
            "04-26", "05-09",//劳动节
            "06-28",//端午
            "09-27", "10-10",//国庆+中秋

    };

    /**
     * 字符串转时间
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(TimeZone.getDefault());

            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }


    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static String getDay(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        return simpleDateFormat.format(time);

    }

    public static String toDay() {
        return getDay(System.currentTimeMillis());
    }


    /**
     * 格式到分
     *
     * @param time
     * @return
     */
    public static String toYMDHM(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        return simpleDateFormat.format(time);

    }

    /**
     * 格式到分
     *
     * @param time
     * @return
     */
    public static String toYMDHMS(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        return simpleDateFormat.format(time);

    }

    /**
     * 格式到毫秒
     *
     * @param time
     * @return
     */
    public static String formatDateToMinute(long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(time);


    }

    /**
     * 格式到毫秒
     *
     * @param time
     * @return
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr   需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 把字符串转化为时间格式
     *
     * @param timestamp
     * @return
     */
    public static String getTimeHM(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp);
        sdf.format(date);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(date);
    }

    /**
     * 获得当前日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    public static String currentDatetimeLong() {
        return datetimeFormatLong.format(now());
    }

    public static String currentDatetimeDay() {
        return dateFormat.format(now());
    }

    /**
     * 格式化日期时间 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        datetimeFormat.setTimeZone(TimeZone.getDefault());

        return datetimeFormat.format(date);
    }

    /**
     * 获得当前时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        timeFormat.setTimeZone(TimeZone.getDefault());

        return timeFormat.format(now());
    }

    /**
     * 格式化时间 时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date date) {
        timeFormat.setTimeZone(TimeZone.getDefault());

        return timeFormat.format(date);
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate 日期范围开始
     * @param endDate   日期范围结束
     * @param src       需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }


    /**
     * 是否周末
     *
     * @return
     */
    public static boolean isWeekend() {

        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

    }

    /**
     * 是否周末
     *
     * @return
     */
    public static boolean isTeensTimerange() {

        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.HOUR_OF_DAY);
        return (i >= 22 || i > 0 && i < 6);

    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 把秒格式化为分种小时
     *
     * @param second
     * @return
     */
    public static String parseSecond(int second) {
        if (second >= 60) {
            return second / 60 + "分";
        } else if (second >= 60 * 60) {
            return second / 60 * 60 + "时";
        } else if (second >= 60 * 60 * 24) {
            return second / 60 * 60 + "天";
        } else {
            return second + "秒";
        }
    }

    /**
     * 把秒格式化为分种小时
     *
     * @param second
     * @return
     */
    public static String parseSecondTime(int second) {
        if (second >= 60) {
            return second / 60 + ":" + fromSecond(second % 60);
        } else if (second >= 60 * 60) {
            return second / 60 * 60 + ":" + fromSecond(second % 60);
        } else if (second >= 60 * 60 * 24) {
            return second / 60 * 60 + ":" + fromSecond(second % 60);
        } else {
            return "00:" + fromSecond(second % 60);
        }
    }

    /**
     * 把秒格式化为分种小时
     *
     * @param second
     * @return
     */
    public static String parseSecondTime2(int second) {
        if (second >= 60) {
            return fromSecond(second / 60) + ":" + fromSecond(second % 60);
        } else {
            return "00:" + fromSecond(second % 60);
        }
    }


    private static String fromSecond(int second) {
        if (second >= 10) {
            return +second + "";
        } else {
            if (second < 0) {
                return "00";
            }
            return "0" + second + "";
        }
    }


    /**
     * 比较时间大小
     *
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        df.setTimeZone(TimeZone.getDefault());

        try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            if (beginDate.getTime() < endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() > endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得星期几
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 获得天数差
     *
     * @param begin
     * @param end
     * @return
     */
    public static long getDayDiff(Date begin, Date end) {
        long day = 1;
        if (end.getTime() < begin.getTime()) {
            day = -1;
        } else if (end.getTime() == begin.getTime()) {
            day = 1;
        } else {
            day += (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }

    public static String formatSeconds(long seconds) {
        long totalYears = seconds / (60 * 60 * 24 * 365);
        long remainingMonths = (seconds % (60 * 60 * 24 * 365)) / (60 * 60 * 24 * 30);
        long remainingDays = ((seconds % (60 * 60 * 24 * 365)) % (60 * 60 * 24 * 30)) / (60 * 60 * 24);
        long remainingHours = (((seconds % (60 * 60 * 24 * 365)) % (60 * 60 * 24 * 30)) % (60 * 60 * 24)) / (60 * 60);
        long remainingMinutes = ((((seconds % (60 * 60 * 24 * 365)) % (60 * 60 * 24 * 30)) % (60 * 60 * 24)) % (60 * 60)) / 60;
        long remainingSeconds = (((((seconds % (60 * 60 * 24 * 365)) % (60 * 60 * 24 * 30)) % (60 * 60 * 24)) % (60 * 60)) % 60);

        StringBuilder result = new StringBuilder();
        if (totalYears > 0) {
            result.append(totalYears).append("年");
            if (remainingMonths > 0 || remainingDays > 0) {
                result.append(remainingMonths).append("月").append(remainingDays).append("天");
            }
        } else if (remainingDays > 0) {
            result.append(remainingDays).append("天")
                    .append(appendZeroPadded(remainingHours)).append(":")
                    .append(appendZeroPadded(remainingMinutes)).append(":")
                    .append(appendZeroPadded(remainingSeconds));
        } else {
            result.append(appendZeroPadded(remainingHours)).append(":")
                    .append(appendZeroPadded(remainingMinutes)).append(":")
                    .append(appendZeroPadded(remainingSeconds));
        }

        return result.toString();
    }


    private static String appendZeroPadded(long value) {
        return value < 10 ? "0" + value : value + "";
    }

    public static String getTimeString(int time) {
        int miao = time % 60;
        int fen = time / 60;
        int hour = 0;
        if (fen >= 60) {
            hour = fen / 60;
            fen = fen % 60;
        }
        String timeString = "";
        String miaoString = "";
        String fenString = "";
        String hourString = "";
        if (miao < 10) {
            miaoString = "0" + miao;
        } else {
            miaoString = miao + "";
        }
        if (fen < 10) {
            fenString = "0" + fen;
        } else {
            fenString = fen + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + fenString + ":" + miaoString;
        } else {
            timeString = fenString + ":" + miaoString;
        }
        return timeString;
    }

    public static String getSkillTimeString(int time) {
        int miao = time % 60;
        int fen = time / 60;
        int hour = 0;
        if (fen >= 60) {
            hour = fen / 60;
            fen = fen % 60;
        }
        String timeString = "";
        String miaoString = "";
        String fenString = "";
        String hourString = "";
        if (miao < 10) {
            miaoString = "0" + miao;
        } else {
            miaoString = miao + "";
        }
        if (fen < 10) {
            fenString = "0" + fen;
        } else {
            fenString = fen + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + fenString + ":" + miaoString;
        } else {

            if (fen != 0) {
                timeString = fenString + ":" + miaoString;

            } else {
                timeString = "00:" + miaoString;
            }

        }
        return timeString;
    }

    public static String getTime(int second) {
        if (second < 10) {
            return "00:00:0" + second;
        }
        if (second < 60) {
            return "00:00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }

    //   格式化时分秒
    public static String getTimeHMS(int second) {

        if (second < 10) {
            return "00:00:0" + second;
        }
        if (second < 60) {
            return "00:00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;


        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + ":" + minute + ":0" + second;
            }
            return "0" + hour + ":" + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + ":" + minute + ":0" + second;
        }
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 一小时的秒数
     */
    private static final int HOUR_SECOND = 60 * 60;

    /**
     * 一分钟的秒数
     */
    private static final int MINUTE_SECOND = 60;

    /**
     * 根据秒数获取时间串
     *
     * @param second (eg: 100s)
     * @return (eg : 00 : 01 : 40)
     */
    public static String getTimeStrBySecond(int second) {
        if (second <= 0) {

            return "00:00:00";
        }

        StringBuilder sb = new StringBuilder();
        int hours = second / HOUR_SECOND;
        if (hours > 0) {

            second -= hours * HOUR_SECOND;
        }

        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {

            second -= minutes * MINUTE_SECOND;
        }

        return (hours >= 10 ? (hours + "")
                : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second)));
    }

    /**
     * 根据秒数获取时间串
     *
     * @param second (eg: 100s)
     * @return (eg : 01 : 40)
     */
    public static String getTimeStrBySecond2(int second) {
        if (second <= 0) {

            return "00:00";
        }

        int hours = second / HOUR_SECOND;
        if (hours > 0) {

            second -= hours * HOUR_SECOND;
        }

        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {

            second -= minutes * MINUTE_SECOND;
        }

        return (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second));
    }


    /**
     * 倒计时时间 获取今天剩余时间
     *
     * @param currentTime 当前时间爱你
     * @return
     */
    public static int getToDayTimeLeftByCurrentTime(long currentTime) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(currentTime);
        long time = instance.getTime().getTime();
        instance.add(Calendar.DAY_OF_YEAR, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.MILLISECOND, 0);
        long time2 = instance.getTime().getTime();
        return (int) (time2 - time) / 1000;
    }

    /**
     * 格式到毫秒  yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatDateToMinuteHMS(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(time);

    }

    /**
     * 格式到毫秒  yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatDateToMinuteHM(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(time);

    }


    /**
     * 獲取剩餘時間
     *
     * @param currentTime
     * @return
     */
    public String getCurrentDayTime(long currentTime) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTime(new Date(System.currentTimeMillis()));
        long timeInMillis = calendar.getTimeInMillis();

        System.out.println("当前时间->" + formatDateToMinuteHMS(timeInMillis));
        //偏移到上个月
//       设置为1号
        calendar.add(Calendar.DAY_OF_MONTH, +1);//设置为1号,当前日期既为本月第一天
//        设置 0 时 0分 0秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long timeInMillis1 = calendar.getTimeInMillis();
        System.out.println("当前时间->" + formatDateToMinuteHMS(timeInMillis1));


        String timeHMS = getTimeHMS((int) ((timeInMillis1 - timeInMillis) / 1000));

        System.out.println("剩餘時間->" + timeHMS);

        return timeHMS;
    }

    public static String calculateTimeDifference(int timeDifference) {

        if (timeDifference <= 0) {
            return "刚刚";
        }

        long minutes = TimeUnit.SECONDS.toMinutes(timeDifference);
        long hours = TimeUnit.SECONDS.toHours(timeDifference);
        long days = TimeUnit.SECONDS.toDays(timeDifference);
        long months = days / 30;

        if (months > 0) {
            return months + "个月";
        } else if (days > 0) {
            return days + "天";
        } else if (hours > 0) {
            return hours + "小时";
        } else if (minutes > 0) {
            return minutes + "分钟";
        } else {
            return timeDifference + "秒";
        }
    }
}




