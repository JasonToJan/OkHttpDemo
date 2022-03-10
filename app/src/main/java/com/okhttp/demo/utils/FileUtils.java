package com.okhttp.demo.utils;


import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

    public static SimpleDateFormat sdf = new SimpleDateFormat("M月d日");

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {

            cal.set(Calendar.MONTH, i);
            //这个月第一天
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //这个月最后一天
            int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            System.out.println("firstDay=" + firstDay + " endDay=" + endDay);

            for (int j = endDay; j >= firstDay; j--) {
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
                sb.append(targetDay).append("9点16分分时涨幅大于9.5 ");
                sb.append(targetDay).append("今天9点30分分时涨跌幅小于8 ");
                sb.append(targetDay).append("9点30分分时涨跌幅大于0 ");
                sb.append(targetDay).append("9点31分分时涨跌幅-").append(targetDay).append("9点30分分时涨跌幅大于-0.5 ");
                sb.append(lastDay).append("成交额大于3亿 ");
                sb.append(lastDay).append("涨幅大于7 ");
                sb.append(lastDay).append("成交额大于").append(last2Day).append("成交额的1.5倍 ");
                sb.append(last2Day).append("未涨停 ");
                sb.append(last3Day).append("未涨停 ");
                sb.append(last4Day).append("未涨停 ");
                sb.append(last5Day).append("未涨停 ");
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
