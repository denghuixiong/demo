package com.bruis.algorithminjava.algorithm.sort;

/**
 * @author LuoHaiYang
 */
public class InsertionSort {
    // 方式1
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                // 注意这里j-1没有越界，因为j > 0进行了判断
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    // 方式2
    public static void sort2(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                // 注意这里j-1没有越界，因为j > 0进行了判断
                swap(arr, j, j-1);
            }
        }
    }

    // 方式3，优化版
    public static void sort3(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 获取需要比较的元素
            int e = arr[i];
            int j = i;
            for (; j > 0 && e < arr[j-1] ; j--) {
                // 如果满足条件，则前一位元素复制给后一位元素
                arr[j] = arr[j-1];
            }
            // 跳出循环，则将需要比较的e元素替换到j位置，j位置即最终停留的位置
            arr[j] = e;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int [] arr = {6,2,1,5,4,3};
        sort2(arr);
        for (int n : arr) {
            System.out.println(n);
        }
    }
}
