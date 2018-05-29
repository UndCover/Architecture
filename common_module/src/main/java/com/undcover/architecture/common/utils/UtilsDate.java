/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class UtilsDate {
    private static final String TAG = UtilsDate.class.getSimpleName();
    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    // 格式：年－月－日 小时：分钟
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    // 格式：年月日 小时分钟秒
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    // 格式：年－月－日
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    // 格式：月－日
    public static final String SHORT_DATE_FORMAT = "MM-dd";

    // 格式: 年月日时分秒
    public static final String SHORT_LINE_FORMAT = "yyyyMMddHHmmss";

    // 格式: 年月日时分秒
    public static final String SHORT_LINE_FORMAT_TWO = "yyyyMMddHHmm";
    // 格式：小时：分钟：秒
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    // 格式：年-月
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    // 年的加减
    public static final int SUB_YEAR = Calendar.YEAR;

    // 月加减
    public static final int SUB_MONTH = Calendar.MONTH;

    // 天的加减
    public static final int SUB_DAY = Calendar.DATE;

    // 小时的加减
    public static final int SUB_HOUR = Calendar.HOUR;

    // 分钟的加减
    public static final int SUB_MINUTE = Calendar.MINUTE;

    // 秒的加减
    public static final int SUB_SECOND = Calendar.SECOND;

    /**
     * 把符合日期格式的字符串转换为日期类型
     * 
     * @param dateStr
     * @return
     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
            // log.error(e);
            d = null;
        }
        return d;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static Date stringtoDate(String dateStr, String format,
            ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
            d = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
            // log.error(e);
        }
        return result;
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param format
     * @return
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

    /**
     * @param dateStr
     * @param amount
     * @return
     */
    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), FORMAT_ONE);
    }

    /**
     * 两个日期相减
     *
     * @param firstTime
     * @param secTime
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * 获得某月的天数
     *
     * @param year int
     * @param month int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * 获取某年某月的天数
     *
     * @param year int
     * @param month int 月份[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期(几号)
     *
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     *
     * @return int
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     *
     * @return int
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     *
     * @param date Date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     *
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     *
     * @param date Date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     *
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     *
     * @param after
     * @return
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * 比较指定日期与当前日期的差
     *
     * @param after
     * @return
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }

    /**
     * 比较指定日期与当前日期的差
     *
     * @param before
     * @return
     * @author chenyz
     */
    public static long dayDiffCurr(String before) {
        Date currDate = UtilsDate.stringtoDate(currDay(), LONG_DATE_FORMAT);
        Date beforeDate = stringtoDate(before, LONG_DATE_FORMAT);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;

    }

    /**
     * 获取每月的第一周
     *
     * @param year
     * @param month
     * @return
     * @author chenyz
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取每月的最后一周
     *
     * @param year
     * @param month
     * @return
     * @author chenyz
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }

    public static Date getNowDate(){
        return Calendar.getInstance().getTime();
    }

    /**
     * 根据生日获取星座
     *
     * @param birth YYYY-mm-dd
     * @return
     */
    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1,
                birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        int[] arr = {
                20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22
        };
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "座";
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     *
     * @param date YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前);
     *
     * @param date 日期 为null时表示当天
     * @param months 相加(相减)的月数
     */
    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
     *
     * @param date 日期 为null时表示当天
     * @param day 相加(相减)的月数
     */
    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    /**
     * 取得距离今天 day 日的日期
     *
     * @param day
     * @param format
     * @return
     * @author chenyz
     */
    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return dateToString(cal.getTime(), format);
    }

    /**
     * 取得指定日期过 day 周后的日期 (当 day 为负数表示指定月之前)
     *
     * @param date 日期 为null时表示当天
     */
    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * 获取当前的日期(yyyy-MM-dd)
     */
    public static String currDay() {
        return UtilsDate.dateToString(new Date(), UtilsDate.LONG_DATE_FORMAT);
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String befoDay() {
        return befoDay(UtilsDate.LONG_DATE_FORMAT);
    }

    /**
     * 根据时间类型获取昨天的日期
     *
     * @param format
     * @return
     * @author chenyz
     */
    public static String befoDay(String format) {
        return UtilsDate.dateToString(UtilsDate.nextDay(new Date(), -1), format);
    }

    /**
     * 获取明天的日期
     */
    public static String afterDay() {
        return UtilsDate.dateToString(UtilsDate.nextDay(new Date(), 1),
                UtilsDate.LONG_DATE_FORMAT);
    }

    /**
     * 取得当前时间距离1900/1/1的天数
     *
     * @return
     */
    public static int getDayNum() {
        int daynum = 0;
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
        return daynum;
    }

    /**
     * getDayNum的逆方法(用于处理Excel取出的日期格式数据等)
     *
     * @param day
     * @return
     */
    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    /** 针对yyyy-MM-dd HH:mm:ss格式,显示yyyymmdd */
    public static String getYmdDateCN(String datestr) {
        if (datestr == null)
            return "";
        if (datestr.length() < 10)
            return "";
        StringBuffer buf = new StringBuffer();
        buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7))
                .append(datestr.substring(8, 10));
        return buf.toString();
    }

    /**
     * 获取本月第一天
     *
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return dateToString(cal.getTime(), format);
    }

    /**
     * 获取本月最后一天
     *
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return dateToString(cal.getTime(), format);
    }

    /**
     * 获取yyyy年mm月dd日
     *
     * @param date
     * @return
     */
    public static String getChineDate(Date date) {
        String strDate = UtilsDate.dateToString(date, UtilsDate.LONG_DATE_FORMAT);
        return strDate.split("-")[0] + "年" + strDate.split("-")[1] + "月" + strDate.split("-")[2]
                + "日";
    }

    /**
     * 获得yyyy年MM月dd日 hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String getChineLongDate(Date date) {
        String strDate = UtilsDate.dateToString(date, UtilsDate.LONG_DATE_FORMAT);
        return strDate.split("-")[0] + "年" + strDate.split("-")[1] + "月" + strDate.split("-")[2]
                + "日"
                + " " + dateToString(date, LONG_TIME_FORMAT);
    }

    /**
     * 获取mm月dd日 hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String getChineShortDate(Date date) {
        String strDate = UtilsDate.dateToString(date, UtilsDate.LONG_DATE_FORMAT);
        return strDate.split("-")[1] + "月" + strDate.split("-")[2] + "日"
                + " " + dateToString(date, LONG_TIME_FORMAT);
    }

    public static String getyyyMMddHHmmss2(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** 得到年月日时分秒 */
    public static String getyyyyMMddHHmmss(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** 得到年月日 */
    public static String getyyyyMMdd(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    public static Date getyyyyMMddHHmmss(String str){

        if (!TextUtils.isEmpty(str)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(str);
                return date;
            } catch (ParseException e) {
                SmartLog.e(TAG, e.getMessage());
            }
        }
        return null;
    }
    /** 得到时分秒 */
    public static String getHHmmss(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** 用来计算两个long型之差的时分秒 */
    public static String getHHmmssBetween(long ms) {
        long second = ms / 1000 % 60;
        long minute = ms / 1000 / 60 % 60;
        long hour = ms / 1000 / 60 / 60;

        String secondStr = String.valueOf(second);
        String minuteStr = String.valueOf(minute);
        String hourStr = String.valueOf(hour);

        StringBuilder mSB = new StringBuilder();

        mSB.append(hourStr.length() < 2 ? "0" + hourStr : hourStr).append(":");
        mSB.append(minuteStr.length() < 2 ? "0" + minuteStr : minuteStr).append(":");
        mSB.append(secondStr.length() < 2 ? "0" + secondStr : secondStr);

        return mSB.toString();
    }

    public static long getLongFromFormat(String date, String format) {
        SimpleDateFormat mFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        if (parse != null) {
            return parse.getTime();
        }
        else {
            return -1;
        }
    }

    public static long getLongFromyyyyMMdd(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        if (parse != null) {
            return parse.getTime();
        }
        else {
            return -1;
        }
    }

    public static String getDateStringFromyyyyMMdd(String date) {
        // String year = date.substring(0, 4);
        // String month = date.substring(4,6);
        // String day = date.substring(6,8);

        // return year+ "-" + month + "-" + day;
        // return year + month + day;
        return date;
    }

    /** yyyy-MM-dd HH:mm:ss */
    public static long getLongFromyyyyMMddHHmmss(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        if (parse != null) {
            return parse.getTime();
        }
        else {
            return -1;
        }
    }

    /** yyyy-MM-dd HH:mm:ss */
    public static long getLongFromyyyyMMddHHmmss_ext(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        if (parse != null) {
            return parse.getTime();
        }
        else {
            return -1;
        }
    }

    // for winstat start
    /** 得到年月日时分秒 */
    public static String getTimeStamps() {
        long ms = System.currentTimeMillis();
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** 得到年月日时分秒 */
    public static String getyyyyMMddHHmmss_f1(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** yyyy-MM-dd HH:mm:ssZ */
    public static String getLongFromyyyyMMddHHmmssZ(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    /** 得到年月日时分秒 */
    public static String getyyyyMMddHHmmss_f2(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    // end for winstat

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getDateAfterDays(String startdate, int days) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        Date dateStart = sdf.parse(startdate);
        cal.setTime(dateStart);

        long ms1 = dateStart.getTime();
        long longday = days;
        long ms2 = (long) (longday * 24 * 60 * 60 * 1000);
        long ms = ms1 + ms2;
        GregorianCalendar gd = new GregorianCalendar();
        gd.setTimeInMillis(ms);
        Date dateEnd = gd.getTime();
        String string = sdf.format(dateEnd);
        return string;
    }
    
    public static String getDateBeforeDays(String startdate, int days) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        Date dateStart = sdf.parse(startdate);
        cal.setTime(dateStart);

        long ms1 = dateStart.getTime();
        long longday = days;
        long ms2 = (long) (longday * 24 * 60 * 60 * 1000);
        long ms = ms1 - ms2;
        GregorianCalendar gd = new GregorianCalendar();
        gd.setTimeInMillis(ms);
        Date dateEnd = gd.getTime();
        String string = sdf.format(dateEnd);
        return string;
    }

    public static String getyyyyMMdd(int year, int month, int day) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gd = new GregorianCalendar(year, month, day);
        Date date = gd.getTime();
        String string = mFormat.format(date);
        return string;
    }

    public static String getyyyyMMdd(Date date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        String string = mFormat.format(date);
        return string;
    }

    /**
     * 判断是否晚于今天
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isAfterToday(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        int cY = c.get(Calendar.YEAR);
        if (cY > year) {
            return false;
        }
        if (cY < year) {
            return true;
        }
        int cM = c.get(Calendar.MONTH) + 1;
        if (cM > month) {
            return false;
        }
        if (cM < month) {
            return true;
        }
        int cD = c.get(Calendar.DAY_OF_MONTH);
        if (cD < day) {
            return true;
        }
        return false;
    }

    /**
     * 判断日期是不是在日期区间里（包含）
     * 
     * @param date 要判断的日期 yyyy-MM-dd
     * @param minDate 最小日期 yyyy-MM-dd
     * @param maxDate 最大日期 yyyy-MM-dd
     * @return
     */
    public static boolean isDateIn(String date, String minDate, String maxDate) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(mFormat.parse(date));

            Calendar minC = Calendar.getInstance();
            minC.setTime(mFormat.parse(minDate));

            Calendar maxC = Calendar.getInstance();
            maxC.setTime(mFormat.parse(maxDate));

            if (c.getTimeInMillis() >= minC.getTimeInMillis()
                    && c.getTimeInMillis() <= maxC.getTimeInMillis()) {
                return true;
            }
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        return false;
    }

    /**
     * 返回yyyy年M月d日的日期（如：2013年9月5日、2013年11月12日）
     * 
     * @param date
     * @return
     */
    public static String getChineseDate(Date date, String dfmt) {
        String fmt = "yyyy年M月d日";
        SimpleDateFormat f = new SimpleDateFormat(dfmt == null ? fmt : dfmt);
        return f.format(date);
    }

    /**
     * clone Calendar
     * 
     * @param c
     * @return
     */
    public static Calendar cloneCalendar(Calendar c) {
        Calendar clone = (Calendar) c.clone();
        clone.setTime(c.getTime());
        return clone;
    }

    /**
     * 获取两个时间之间相差的月份数组
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @return 两个时间的相差的月份数
     */
    public static int getMonthsOffset(Date start, Date end) {
        int offset = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);
        while (c1.compareTo(c2) < 0)
        {
            offset++;
            c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
        }
        return offset;
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 根据日期获取完整的农历信息 输出格式getAllLunar("2010", "7", "18") 农历 【虎】贰零壹零 年
     *         六月小廿三 庚寅年癸未月乙酉日
     */
    public static String getAllLunar(String year, String month, String day)
            throws Exception {
        Date sDObj;
        String s;
        int SY, SM, SD;
        int sy;
        SY = Integer.parseInt(year);
        SM = Integer.parseInt(month);
        SD = Integer.parseInt(day);
        sy = (SY - 4) % 12;
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        sDObj = cl.getTime();
        // 日期
        Sun2Lunar(sDObj); // 农历
        s = "农历 " + "【" + Animals[sy] + "】" + cYear(getYear()) + "年" + " ";
        s += (getIsLeap() ? "闰" : "") + monthNong[getMonth()] + "月"
                + (monthDays(getYear(), getMonth()) == 29 ? "小" : "大");
        s += cDay(getDay()) + " ";
        s += cyclical(getYearCyl()) + "年" + cyclical(getMonCyl()) + "月"
                + cyclical(getDayCyl()) + "日";
        return s;
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 根据阳历日期获取阴历年份
     * @throws Exception
     */
    public static String getLauarYear(String year, String month, String day) throws Exception {
        getAllLunar(year, month, day);
        return cYear(getYear());
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 根据阳历日期获取阴历月份
     * @throws Exception
     */
    public static String getLauarMonth(String year, String month, String day) throws Exception {
        getAllLunar(year, month, day);
        return monthNong[getMonth()];
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 根据阳历日期获取阴历日
     * @throws Exception
     */
    public static String getLauarDay(String year, String month, String day) throws Exception {
        getAllLunar(year, month, day);
        return cDay(getDay());
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 根据阳历日期获取属相
     */
    public static String getZodiac(String year, String month, String day)
            throws Exception {
        getAllLunar(year, month, day);
        int SY, sy;
        SY = getYear();
        sy = (SY - 4) % 12;
        return Animals[sy];
    }

    /**
     * @param sunyear
     * @param sunmonth
     * @param sunday
     * @return 根据阳历日期获取星座
     */
    public static String getConstellation(String sunyear, String sunmonth, String sunday)
            throws Exception {
        int m = Integer.parseInt(sunmonth);
        int d = Integer.parseInt(sunday);
        if (d < constellationEdgeDay[m]) {
            m = m - 1;
        }
        if (m >= 0) {
            return constellationArr[m];
        }
        return constellationArr[11];
    }

    private static int monCyl, dayCyl, yearCyl;
    private static int year, month, day;
    private static boolean isLeap;

    public static final String[] constellationArr = {
            "魔羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座",
            "魔羯座"
    };

    public static final int[] constellationEdgeDay = {
            30, 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22
    };

    private static int[] lunarInfo = {
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
            0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
            0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
            0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
            0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
            0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
            0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
            0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
            0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
            0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
            0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
            0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
            0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
            0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
            0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
            0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
            0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
            0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
            0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0
    };

    // private static int[] solarMonth = {
    // 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
    //
    // 30, 31
    // };

    private static String[] Gan = {
            "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
            "壬", "癸"
    };

    private static String[] Zhi = {
            "子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"
    };

    private static String[] Animals = {
            "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
            "猴", "鸡", "狗", "猪"
    };

    // private static int[] sTermInfo = {
    // 0, 21208, 42467, 63836, 85337, 107014,
    // 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,
    // 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224,
    // 483532, 504758
    // };

    private static String[] nStr1 = {
            "日", "一", "二", "三", "四", "五", "六", "七",
            "八", "九", "十"
    };

    private static String[] nStr2 = {
            "初", "十", "廿", "卅", "　"
    };

    private static String[] monthNong = {
            "正", "正", "二", "三", "四", "五", "六",
            "七", "八", "九", "十", "十一", "十二"
    };

    private static String[] yearName = {
            "零", "壹", "贰", "叁", "肆", "伍", "陆",
            "柒", "捌", "玖"
    };

    // 传回农历 y年的总天数
    private static int lYearDays(int y) {
        int i;
        int sum = 348; // 29*12
        for (i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunarInfo[y - 1900] & i) == 0 ? 0 : 1; // 大月+1天
        }
        return (sum + leapDays(y)); // +闰月的天数
    }

    // 传回农历 y年闰月的天数
    private static int leapDays(int y) {
        if (leapMonth(y) != 0)
            return ((lunarInfo[y - 1900] & 0x10000) == 0 ? 29 : 30);
        else
            return (0);
    }

    // 传回农历 y年闰哪个月 1-12 , 没闰传回 0
    private static int leapMonth(int y) {
        return (lunarInfo[y - 1900] & 0xf);
    }

    // 传回农历 y年m月的总天数
    private static int monthDays(int y, int m) {
        return ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0 ? 29 : 30);
    }

    private static void Sun2Lunar(Date objDate) {

        int i, leap = 0, temp = 0;
        Calendar cl = Calendar.getInstance();
        cl.set(1900, 0, 31);
        Date baseDate = cl.getTime();
        int offset = (int) ((objDate.getTime() - baseDate.getTime()) / 86400000); // 天数(86400000=24*60*60*1000)
        dayCyl = offset + 40; // 1899-12-21是农历1899年腊月甲子日
        monCyl = 14; // 1898-10-01是农历甲子月
        // 得到年数
        for (i = 1900; i < 2050 && offset > 0; i++) {
            temp = lYearDays(i); // 农历每年天数
            offset -= temp;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += temp;
            i--;
            monCyl -= 12;
        }
        year = i; // 农历年份
        yearCyl = i - 1864; // 1864年是甲子年
        leap = leapMonth(i); // 闰哪个月
        isLeap = false;
        for (i = 1; i < 13 && offset > 0; i++) {
            // 闰月
            if (leap > 0 && i == (leap + 1) && isLeap == false) {
                --i;
                isLeap = true;
                temp = leapDays(year);
            } else {
                temp = monthDays(year, i);
            }
            // 解除闰月
            if (isLeap == true && i == (leap + 1))
                isLeap = false;
            offset -= temp;
            if (isLeap == false)
                monCyl++;
        }
        if (offset == 0 && leap > 0 && i == leap + 1)
            if (isLeap) {
                isLeap = false;
            } else {
                isLeap = true;
                --i;
                --monCyl;
            }
        if (offset < 0) {
            offset += temp;
            --i;
            --monCyl;
        }
        month = i; // 农历月份
        day = offset + 1; // 农历天份
    }

    // 传入 offset 传回干支, 0=甲子
    private static String cyclical(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    // 中文日期
    private static String cDay(int d) {
        String s;
        switch (d) {
            case 10:
                s = "初十";
                break;
            case 20:
                s = "二十";
                break;
            case 30:
                s = "三十";
                break;
            default:
                s = nStr2[(int) (d / 10)];// 取商
                s += nStr1[d % 10];// 取余
        }
        return (s);
    }

    private static String cYear(int y) {
        String s = " ";
        int d;
        while (y > 0) {
            d = y % 10;
            y = (y - d) / 10;
            s = yearName[d] + s;
        }
        return (s);
    }

    private static int getYear() {
        return (year);
    }

    private static int getMonth() {
        return (month);
    }

    private static int getDay() {
        return (day);
    }

    private static int getMonCyl() {
        return (monCyl);
    }

    private static int getYearCyl() {
        return (yearCyl);
    }

    private static int getDayCyl() {
        return (dayCyl);
    }

    private static boolean getIsLeap() {
        return (isLeap);
    }

    public static String getyyyyMMddHHmm(long ms) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(ms);
        String string = mFormat.format(date);
        return string;
    }

    public static String getTime(String time) {
        try {
            long longtime = Long.parseLong(time);
            return getyyyyMMddHHmm(longtime);
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
            return "";
        }
    }

    public static String getNoticeTime(String time) {
        time = time.substring(0, time.length() - 3);
        return getTime(time);
    }

    public static long getLongFromyyyyMMddHHmm(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    // @SuppressLint("SimpleDateFormat")
    // public static long parseDate(String date, String pattern) throws
    // ParseException {
    // SimpleDateFormat f = new SimpleDateFormat(pattern);
    // return f.parse(date).getTime();
    // }

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(long date, String pattern) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        return f.format(new Date(date));
    }

    public static String changeFormat(String date, String srcPattern, String destPattern)
            throws ParseException {
        long l = parseDate(date, srcPattern);
        return formatDate(l, destPattern);
    }

    /**
     * for example : yyyy-MM-dd HH:mm:ss
     * 
     * @param pattern
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDateString(String pattern) {
        SimpleDateFormat ff = new SimpleDateFormat(pattern);
        return ff.format(new Date());
    }

    public static int getWeekday(String time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
            date = new Date();
        }
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        int result = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (result == 0) {
            result = 7;
        }
        return result;
    }

    public static String getFormateDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /* xsm, add, 20150414 */
    public static long parseDate(String time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            SmartLog.e(TAG, e.getMessage());
            date = new Date();
        }
        return date.getTime();
    }

}
