package com.okhttp.demo.utils;

import java.util.concurrent.CountDownLatch;

/**
 * desc: 手写冒泡，选择，插入排序
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/12/12 18:52
 * contact: jason1211241203@gmail.com
 **/
public class AlgorithmUtils5 {

    public static void bubbleSort(int[] numbers) {
        int size = numbers.length;
        int temp;

        boolean flag = true;

        for (int i = 0; i < size - 1 && flag; i++) {
            flag = false;
            for (int j = 0; j < size - 1 - i; j++) {//这里j从0开始
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    flag = true;
                }
            }
        }

    }

    public static void selectSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        int k;//最小数索引
        for (int i = 0; i < size - 1; i++) {
            k = i;//默认是第一个最小
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }

            //交换
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }


    public static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        int j;
        for (int i = 1; i < size; i++) {
            temp = numbers[i];//拿出去先，看下放在前面哪个位置
            for (j = i; j > 0 && temp < numbers[j - 1]; j--) {
                numbers[j] = numbers[j - 1];//后移
            }
            numbers[j] = temp;//j已经减了
        }
    }


   static class MyRunnable implements Runnable {

        private CountDownLatch countDownLatch;

        private CountDownLatch await;

        public MyRunnable(CountDownLatch countDownLatch, CountDownLatch await) {
            this.countDownLatch = countDownLatch;
            this.await = await;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println("子线程" +Thread.currentThread().getName()+ "处理自己事情");
                Thread.sleep(1000);
                await.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);

        for (int i=0; i< 5; i++) {
            new Thread(new MyRunnable(countDownLatch, await)).start();
        }

        System.out.println("主线程处理自己事情");
        Thread.sleep(3000);
        countDownLatch.countDown();
        System.out.println("主线程处理结束");
        await.await();
        System.out.println("子线程处理完毕啦");
    }

}
