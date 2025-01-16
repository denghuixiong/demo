package com.bruis.algorithminjava.algorithm.sort;

/**
 *
 * 原地堆排序！ 不需要额外的空间
 *
 * 这里构造出来的是一个最小堆
 *
 * @author LuoHaiYang
 */
public class HeapSort02 {

    public static void sort(int[] arr) {
        int n = arr.length;

        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引 = n-1
        // 其实这里 i = (n-1) 也行
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            siftDown2(arr, n, i);
        }

        // [a.....v,k]
        // [.......]k
        // [.....] ba
        for (int i = n-1; i > 0; i--) {
            // 由于上面执行过下沉操作，所以已经是最大堆（但没有排序完）。所以此时swap就将最大值替换到数组末尾。
            swap(arr, 0, i);
            // 由于siftDown中是判断 2*k+1 < n ，所以就是对n-1进行下沉操作；
            siftDown2(arr, i, 0);
        }
    }

    // 下浮
    public static void siftDown(int[] arr, int n, int k) {

        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j+1] > arr[j]) {
                j += 1;
            }
            if (arr[k] >= arr[j]) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    /**
     * 优化下沉过程, 不适用swap交换，通过赋值来代替。
     *
     * @param arr
     * @param n
     * @param k
     */
    private static void siftDown2(int[] arr, int n, int k) {

        int e = arr[k];

        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j++;
            }

            if (e >= arr[j]) {
                break;
            }
            // 此时说明arr[j] > arr[k]; 所以让大值上浮;
            arr[k] = arr[j];
            k = j;
        }
        // 将最小元素替换到k的位置
        arr[k] = e;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
/*
        int n = 100;
        int[] test = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            test[i] = random.nextInt(1000);
        }
*/
        int n = 10;
        int[] test = {10, 41, 30, 28, 16, 22, 13, 19, 17, 15};

        sort(test);

        for (int i = 1; i < n; i++) {
            if (test[i-1] > test[i]) {
                throw new IllegalArgumentException("Error!");
            }
        }
    }
}
