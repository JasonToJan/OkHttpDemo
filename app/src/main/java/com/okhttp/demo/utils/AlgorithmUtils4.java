package com.okhttp.demo.utils;

/**
 * desc:
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/12/12 18:27
 * contact: jason1211241203@gmail.com
 **/
public class AlgorithmUtils4 {

    public static void main(String[] args) {
        int[] ints = new int[]{3, 5, 1, 2, 4};
        bubbleSort(ints);
    }

    /**
     * 冒泡排序
     *
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {

        int temp = 0;
        int size = numbers.length;

        boolean flag = true;

        //少遍历一次
        for (int i = 0; i < size - 1 && flag; i++) {
            flag = false;
            //选第一大的，选第二大的，第三大的。。。
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {

        int size = numbers.length;
        int temp = 0;

        for (int i = 0; i < size - 1; i++) {
            //选出最小的K索引
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            //放在最前面
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    /**
     * 插入排序
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        int j = 0;

        for (int i = 1; i < size; i++) {
            temp = numbers[i];

            //假如temp比前面的值还小
            for (j = i; j > 0 && temp < numbers[j - 1]; j++) {
                numbers[j] = numbers[j-1];
            }
            numbers[j] = temp;
        }

    }



}
