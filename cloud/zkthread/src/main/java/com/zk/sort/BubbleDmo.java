package com.zk.sort;/**
 * Created by husky on 2018/11/16.
 */

import sun.security.util.Length;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * 冒泡排序
 * 原理：每次对比相邻的2个数,将大的或者小的向后移动,保证一次循环过后,最后面的以为是最大的或者最小的数
 * 2:2层循环,外层循环控制总体的循环次数,内层循环来做数据的对比和交换。
 *
 * @author zhangkai
 * @create 2018-11-16 16:51
 **/
public class BubbleDmo {

    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                //从小到大进行排序
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {3,4,1,6,3,7,3,0,9,6,5};
        BubbleDmo.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
