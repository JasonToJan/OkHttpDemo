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

    private static String FILE_TEST = "E:\\test.txt";
    private static String FILE_CHARSET = "utf-8";
    private static final String TAG = "FileUtils";

    public static SimpleDateFormat sdf = new SimpleDateFormat("M月d日");

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 11; i >= 7; i--) {

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

                if (i == 11 && j == 31) continue;//最后一天算了


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
                cal2.set(Calendar.YEAR, 2021);
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
                //0个交易日前创新高
                // 0个交易日前收盘价大于0个交易日前开盘价
                // 0个交易日前涨幅大于1.5
                // 0个交易日前成交额大于3亿
                // 0个交易日前成交量大于1个交易日前成交量的0.95倍
                // 0个交易日前成交量小于1个交易日前成交量的2倍
                // 1个交易日前涨幅大于-1.5
                // 1个交易日前最高价大于2个交易日前最高价
                // 2个交易日前涨幅大于-1.5
                // 2个交易日前最高价大于3个交易日前最高价
                sb.setLength(0);
                sb.append("\n\n");
                sb.append("非st 非科创 非创业 ");
                sb.append("(").append(targetDay).append("开盘价-").append(lastDay).append("收盘价)/").append(lastDay).append("收盘价)>1% ");
                sb.append("(").append(targetDay).append("收盘价-").append(targetDay).append("开盘价)/").append(targetDay).append("开盘价)>1% ");
                sb.append("(").append(targetDay).append("最低价-").append(lastDay).append("收盘价)/").append(lastDay).append("收盘价)>-1% ");
                sb.append(lastDay).append("成交额大于1.5亿 ").append(targetDay).append("成交额大于3亿 ").append(targetDay).append("成交额小于40亿 ");
                sb.append(targetDay).append("成交量小于").append(lastDay).append("成交量的3倍 ");
                sb.append(lastDay).append("涨幅大于-2.2%小于5% ");
                sb.append(last3Day).append("没有涨停 ");
                sb.append(targetDay).append("涨停被砸或涨停 ").append(targetDay).append("涨幅大于5% ");

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
