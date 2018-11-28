package com.zk.sort;/**
 * Created by husky on 2018/11/16.
 */

/**
 * 二分查找
 * 也叫折半查找,要求线性表必须顺序结构存储
 *
 * @author zhangkai
 * @create 2018-11-16 11:28
 **/
public class BinaryDemo {
    public static  int search(int[] arr, int num) {
        int low = 0;
        int height = arr.length - 1;
        while (low <= height) {
            int middle = (low + height) / 2;
            if (num == arr[middle]) {
                return middle;
            } else if (num > arr[middle]) {
                low = middle + 1;
            } else if (num < arr[middle]) {
                height = middle - 1;
            }
        }
        return -1;
    }


    public static  int recursionSearch(int[] arr, int num,int beginIndex,int endIndex) {
        int middleIndex = (beginIndex+endIndex)/2;
        if(num < arr[beginIndex] || num > arr[endIndex] || beginIndex > endIndex){
            return  -1;
        }

        if(num < arr[middleIndex]){
              return recursionSearch(arr,num,beginIndex,middleIndex-1);
        }else if(num > arr[middleIndex]){
            return recursionSearch(arr,num,middleIndex+1,endIndex);
        }else{
            return  middleIndex;
        }
    }




    public static void main(String[] args) {
          int[] arr = {1,2,3,4,5,6,7,8,9};

          System.out.println(BinaryDemo.search(arr,4));
          System.out.println(BinaryDemo.recursionSearch(arr,4,0,arr.length -1));
    }

}
