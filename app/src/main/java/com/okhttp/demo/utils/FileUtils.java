package com.okhttp.demo.utils;


import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * desc:
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/9/26 15:32
 * contact: jason1211241203@gmail.com
 **/
public class FileUtils {

    private static String FILE_TEST = "/Users/wangjianan/Documents/res/test/test.txt";
    private static String FILE_CHARSET = "utf-8";
    private static final String TAG = "FileUtils";

    public static final String HOLIDAY1 = "2022年2月4日";
    public static final String HOLIDAY2 = "2022年1月3日";
    public static final String HOLIDAY3 = "2021年10月7日";
    public static final String HOLIDAY4 = "2021年9月21日";
    public static final String HOLIDAY5 = "2022年4月5日";
    public static final String HOLIDAY6 = "2022年5月4日";
    public static final String HOLIDAY7 = "2022年6月3日";
    /**
     * 2月4日时间戳
     */
    public static final long TWO_FOUR = 1643971435894L;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 8; i >= 0; i--) {

            cal.set(Calendar.YEAR, 2022);
            cal.set(Calendar.MONTH, i);
            //这个月第一天
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //这个月最后一天
            int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            System.out.println("firstDay=" + firstDay + " endDay=" + endDay);

            for (int j = endDay; j >= firstDay; j--) {

                if (j == 31 && i == 11) {
                    continue;
                }

                cal.set(Calendar.MONTH, i);
                cal.set(Calendar.DATE, j);
                String targetDay = sdf.format(cal.getTime());

                System.out.println("targetDay=" + targetDay);

                //判断target是否是周末，是就+1
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    continue;
                }

                //判断下一天是否是周末
                cal.add(Calendar.DATE, 1);
                String nextDay = sdf.format(cal.getTime());
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal.add(Calendar.DATE, 1);
                    nextDay = sdf.format(cal.getTime());
                    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal.add(Calendar.DATE, 1);
                        nextDay = sdf.format(cal.getTime());
                    }
                }
                System.out.println("nextDay=" + nextDay);
//
//                cal.add(Calendar.DATE, 1);
//                String next2Day = sdf.format(cal.getTime());
//                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                    cal.add(Calendar.DATE, 1);
//                    next2Day = sdf.format(cal.getTime());
//                    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                        cal.add(Calendar.DATE, 1);
//                        next2Day = sdf.format(cal.getTime());
//                    }
//                }
//                System.out.println("next2Day=" + next2Day);

                Calendar cal2 = Calendar.getInstance();
                cal2.set(Calendar.YEAR, 2022);
                cal2.set(Calendar.MONTH, i);
                cal2.set(Calendar.DATE, j);

                //上个交易日
                cal2.add(Calendar.DATE, -1);
                String lastDay = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    lastDay = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        lastDay = sdf.format(cal2.getTime());
                    }
                }
                if (lastDay.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    lastDay = sdf.format(cal2.getTime());
                }
                if (lastDay.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    lastDay = sdf.format(cal2.getTime());
                }
                System.out.println("lastDay=" + lastDay);

                cal2.add(Calendar.DATE, -1);
                String last2Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last2Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last2Day = sdf.format(cal2.getTime());
                    }
                }
                if (last2Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last2Day = sdf.format(cal2.getTime());
                }
                if (last2Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last2Day = sdf.format(cal2.getTime());
                }
                System.out.println("last2Day=" + last2Day);

                cal2.add(Calendar.DATE, -1);
                String last3Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last3Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last3Day = sdf.format(cal2.getTime());
                    }
                }
                if (last3Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last3Day = sdf.format(cal2.getTime());
                }
                if (last3Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last3Day = sdf.format(cal2.getTime());
                }
                System.out.println("last3Day=" + last3Day);

                cal2.add(Calendar.DATE, -1);
                String last4Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last4Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last4Day = sdf.format(cal2.getTime());
                    }
                }
                if (last4Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last4Day = sdf.format(cal2.getTime());
                }
                if (last4Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last4Day = sdf.format(cal2.getTime());
                }
                System.out.println("last4Day=" + last4Day);

                cal2.add(Calendar.DATE, -1);
                String last5Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last5Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last5Day = sdf.format(cal2.getTime());
                    }
                }
                if (last5Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last5Day = sdf.format(cal2.getTime());
                }
                if (last5Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last5Day = sdf.format(cal2.getTime());
                }
                System.out.println("last5Day=" + last5Day);

                cal2.add(Calendar.DATE, -1);
                String last6Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last6Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last6Day = sdf.format(cal2.getTime());
                    }
                }
                if (last6Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last6Day = sdf.format(cal2.getTime());
                }
                if (last6Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last6Day = sdf.format(cal2.getTime());
                }
                System.out.println("last6Day=" + last6Day);

                cal2.add(Calendar.DATE, -1);
                String last7Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last7Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last7Day = sdf.format(cal2.getTime());
                    }
                }
                if (last7Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last7Day = sdf.format(cal2.getTime());
                }
                if (last7Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last7Day = sdf.format(cal2.getTime());
                }
                System.out.println("last7Day=" + last7Day);

                cal2.add(Calendar.DATE, -1);
                String last8Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last8Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last8Day = sdf.format(cal2.getTime());
                    }
                }
                if (last8Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last8Day = sdf.format(cal2.getTime());
                }
                if (last8Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last8Day = sdf.format(cal2.getTime());
                }
                System.out.println("last8Day=" + last8Day);

                cal2.add(Calendar.DATE, -1);
                String last9Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last9Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last9Day = sdf.format(cal2.getTime());
                    }
                }
                if (last9Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last9Day = sdf.format(cal2.getTime());
                }
                if (last9Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last9Day = sdf.format(cal2.getTime());
                }
                System.out.println("last9Day=" + last9Day);

                cal2.add(Calendar.DATE, -1);
                String last10Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last10Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last10Day = sdf.format(cal2.getTime());
                    }
                }
                if (last10Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last10Day = sdf.format(cal2.getTime());
                }
                if (last10Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last10Day = sdf.format(cal2.getTime());
                }
                System.out.println("last10Day=" + last10Day);

                cal2.add(Calendar.DATE, -1);
                String last11Day = sdf.format(cal2.getTime());
                if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cal2.add(Calendar.DATE, -1);
                    last11Day = sdf.format(cal2.getTime());
                    if (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        cal2.add(Calendar.DATE, -1);
                        last11Day = sdf.format(cal2.getTime());
                    }
                }
                if (last11Day.equals(HOLIDAY1)) {
                    cal2.add(Calendar.DATE, -7);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY2)) {
                    cal2.add(Calendar.DATE, -3);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY3)) {
                    cal2.add(Calendar.DATE, -7);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY4)) {
                    cal2.add(Calendar.DATE, -4);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY5)) {
                    cal2.add(Calendar.DATE, -4);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY6)) {
                    cal2.add(Calendar.DATE, -5);
                    last11Day = sdf.format(cal2.getTime());
                }
                if (last11Day.equals(HOLIDAY7)) {
                    cal2.add(Calendar.DATE, -1);
                    last11Day = sdf.format(cal2.getTime());
                }
                System.out.println("last11Day=" + last11Day);

//
//                //3天前
//                cal.add(Calendar.DATE, -1);
//                String last3Day = sdf.format(cal.getTime());
//                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                    cal.add(Calendar.DATE, -1);
//                    last3Day = sdf.format(cal.getTime());
//                    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                        cal.add(Calendar.DATE, -1);
//                        last3Day = sdf.format(cal.getTime());
//                    }
//                }

                //非st 非科创 非创业
                // 今天9点16分分时涨幅大于9.5
                // 今天9点30分分时涨跌幅小于8
                // 今天9点30分分时涨跌幅大于0
                // 今天9点31分分时涨跌幅-今天9点30分分时涨跌幅大于-0.5
                // 昨天成交额大于3亿
                // 昨天涨停
                // 昨天成交额大于前天成交额的1.5倍
                // 2个交易日前未涨停
                // 3个交易日前未涨停
                // 4个交易日前未涨停
                // 5个交易日前未涨停
                // 6个交易日前未涨停
                // 7个交易日前未涨停
                // 8个交易日前未涨停
                // 9个交易日前未涨停
                // 10个交易日前未涨停
                // 11个交易日前未涨停

                sb.setLength(0);
                sb.append("\n\n");
                sb.append("非st 非科创 非创业 ");


                //选出强势的
                //sb.append(targetDay).append("9点15分分时涨幅大于9 ");
                //sb.append(targetDay).append("9点19分分时涨幅大于5.3 ");
                //竞价在可盈利区间
                //sb.append(targetDay).append("9点25分分时涨跌幅大于-0.3小于5.1 ");
                //开盘没有被砸太猛
                //sb.append(targetDay).append("9点31分分时涨跌幅-").append(targetDay).append("9点30分分时涨跌幅大于-1.5 ");
                //有人玩的
                sb.append(lastDay).append("成交额大于1.5亿 ");
                sb.append(lastDay).append("涨停或涨停被砸 ");
                //有新入资金
                sb.append(lastDay).append("成交额大于").append(last2Day).append("成交额的1.25倍 ");
                //前面盈利空间不能太大
                sb.append("(").append(last2Day).append("收盘价-").append(last4Day).append("收盘价)/").append(last4Day).append("收盘价<4.6% ");

                sb.append(last2Day).append("的最大涨幅小于9.9 ");
                sb.append(last3Day).append("的最大涨幅小于9.9 ");
                sb.append(last4Day).append("的最大涨幅小于9.9 ");
                sb.append(last5Day).append("的最大涨幅小于9.9 ");
                sb.append(last6Day).append("未涨停 ");
                sb.append(last7Day).append("未涨停 ");
                sb.append(last8Day).append("未涨停 ");
                sb.append(last9Day).append("未涨停 ");
                sb.append(last10Day).append("未涨停 ");
                sb.append(last11Day).append("未涨停 ");



//                sb.append(lastDay).append("非涨停 ");
//                sb.append(nextDay).append("跌幅大于1 ");
//                sb.append(nextDay).append("成交量大于").append(targetDay).append("成交量2倍 ");
//                sb.append(next2Day).append("涨幅大于1 ");

//                System.out.println("targetDay="+targetDay+" nextDay="+nextDay);

                sb.append("\n\n");
                list.add(sb.toString());

            }
        }

        writeDataToTxtFile(FILE_TEST, FILE_CHARSET, list);

    }


    /**
     * 将数据写入txt文件
     *
     * @param destPath 目标文件路径
     * @param charset  字符集
     * @param dataList 字符串数据列表
     * @return 文件
     */
    public static File writeDataToTxtFile(String destPath, String charset, List<String> dataList) {
        //参数校验
        if (!destPath.endsWith(".txt")) {
            return null;
        }

        //存在先删除
        try {
            Path path = Paths.get(destPath);
            boolean result = Files.deleteIfExists(path);
            System.out.println("是否删除成功："+result);
        } catch (Exception e) {

        }

        File txtFile = new File(destPath);//txt文件
        PrintWriter txtWriter = null;//txt输出流
        try {
            //如果文件不存在就新建文件
            if (!txtFile.exists()) {
                txtFile.createNewFile();
            }
            //获取文件输出流
            txtWriter = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(txtFile), charset));
            //遍历数据列表，输出数据到txt文件
            for (String data : dataList) {
                txtWriter.println(data);
            }
            txtWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭输出流
            if (txtWriter != null) {
                txtWriter.close();
            }
        }
        //返回
        return txtFile;
    }
}
