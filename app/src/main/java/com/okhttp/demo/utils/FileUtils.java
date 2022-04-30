package com.okhttp.demo.utils;


import android.text.TextUtils;
import android.util.ArrayMap;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Key存放日期，Value存放需要减去的日期天数，因为放假，不会交易
     */
    public static final Map<String, Integer> HOLIDAY_MAP = new HashMap<>();

    static {
        HOLIDAY_MAP.put("2021年2月17日", -7);
        HOLIDAY_MAP.put("2021年4月5日", -3);
        HOLIDAY_MAP.put("2021年5月5日", -5);
        HOLIDAY_MAP.put("2021年6月14日", -3);
        HOLIDAY_MAP.put("2021年9月21日", -4);
        HOLIDAY_MAP.put("2021年10月7日", -7);
        HOLIDAY_MAP.put("2022年1月3日", -3);
        HOLIDAY_MAP.put("2022年2月4日", -7);
        HOLIDAY_MAP.put("2022年4月5日", -4);
        HOLIDAY_MAP.put("2022年5月4日", -5);
        HOLIDAY_MAP.put("2022年6月3日", -1);
        HOLIDAY_MAP.put("2022年9月12日", -3);
        HOLIDAY_MAP.put("2022年10月7日", -7);
    }

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");

    //---------------------------设置下面几个参数就可以了----------------------------------------------//
    /**
     * 当前年份
     */
    public static final int CURRENT_YEAR = 2022;
    /**
     * 想要开始的月份
     */
    public static final int BEGIN_MONTH = 1;
    /**
     * 想要结束的月份
     */
    public static final int MONTH_END = 2;
    //---------------------------设置上面几个参数就可以了----------------------------------------------//

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, CURRENT_YEAR);

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = MONTH_END - 1; i >= BEGIN_MONTH - 1; i--) {

            cal.set(Calendar.YEAR, CURRENT_YEAR);
            cal.set(Calendar.MONTH, i);
            //这个月第一天
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //这个月最后一天
            int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            System.out.println("firstDay=" + firstDay + " endDay=" + endDay);

            firstTag:
            for (int j = endDay; j >= firstDay; j--) {

                //最后一天好像是有问题的
                if (j == 31 && i == 11) {
                    continue;
                }

                cal.set(Calendar.YEAR, CURRENT_YEAR);
                cal.set(Calendar.MONTH, i);
                cal.set(Calendar.DATE, j);
                String targetDay = sdf.format(cal.getTime());

                System.out.println("targetDay=" + targetDay);

                //判断上一个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (targetDay.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        //这里过滤循环
                        cal.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        int lastTradeYear = cal.get(Calendar.YEAR);
                        int lastTradeMonth = cal.get(Calendar.MONTH);
                        int lastTradeDay = cal.get(Calendar.DATE);
                        if (lastTradeYear == CURRENT_YEAR) {
                            i = lastTradeMonth;
                            j = lastTradeDay + 1;
                            System.out.println("上个交易日月份=" + lastTradeMonth);
                            System.out.println("上个交易日日期=" + lastTradeDay);
                        }
                        continue firstTag;
                    }
                }

                //判断target是否是周末，是就+1
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    continue;
                }



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

                //判断上一个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (lastDay.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        lastDay = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("lastDay=" + lastDay);

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

                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last2Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last2Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last2Day=" + last2Day);

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

                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last3Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last3Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last3Day=" + last3Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last4Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last4Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last4Day=" + last4Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last5Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last5Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last5Day=" + last5Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last6Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last6Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last6Day=" + last6Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last7Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last7Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last7Day=" + last7Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last8Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last8Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last8Day=" + last8Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last9Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last9Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last9Day=" + last9Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last10Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last10Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last10Day=" + last10Day);

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
                //判断这个交易日是否是假期
                for (String date: HOLIDAY_MAP.keySet()) {
                    if (last10Day.equals(date) && HOLIDAY_MAP.get(date) != null) {
                        cal2.add(Calendar.DATE, HOLIDAY_MAP.get(date));
                        last10Day = sdf.format(cal2.getTime());
                    }
                }
                //System.out.println("last11Day=" + last11Day);

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
                sb.append(targetDay).append("9点15分分时涨幅大于9 ");
                sb.append(targetDay).append("9点19分分时涨幅大于5.3 ");
                //竞价在可盈利区间
                sb.append(targetDay).append("9点25分分时涨跌幅大于-0.3小于5.1 ");
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
