package com.example.myapplication.sort;

/**
 * 分治思想
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        doMergeSort(arr, 0, arr.length - 1);
    }

    private static void doMergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int middle = l+(r - l) / 2;
            doMergeSort(arr, l, middle);
            doMergeSort(arr, middle + 1, r);
            merge(arr, l, middle, r);
        }
    }

    /**
     * 合并数组中的两个子数组，直接合并到原数组
     *
     * @param arr
     * @param l
     * @param middle
     * @param r
     */
    private static void merge(int[] arr, int l, int middle, int r) {
        //创建两个临时数组
        int[] tmpLeft = new int[middle - l + 1];
        int[] tmpRight = new int[r - middle];
        for (int i = l; i <= middle; i++) {
            tmpLeft[i - l] = arr[i];
        }

        for (int i = middle + 1; i <= r; i++) {
            tmpRight[r - middle-1] = arr[i];
        }

        //两个数组都是有序的数组
        int start = l;
        int i = 0;
        int j = 0;
        while (i < tmpLeft.length && j < tmpRight.length) {
            //找到左右数组中小的那个元素
            if (tmpLeft[i] < tmpRight[j]) {
                arr[start] = tmpLeft[i];
                i++;
            } else {
                arr[start] = tmpRight[j];
                j++;
            }
            start++;
        }
        //将剩下的部分copy过去
        if (tmpLeft.length > i) {
            for (int k = i; k < tmpLeft.length; k++) {
                arr[start] = tmpLeft[k];
                start++;
            }
        } else if (tmpRight.length > j) {
            for (int k = j; k < tmpRight.length; k++) {
                arr[start] = tmpLeft[k];
                start++;
            }
        }

    }
}
