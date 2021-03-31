package com.example.myapplication.array;

/**
 * 排序工具
 */
public class SortUtil {

    public void quickSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        _doQuickSort(arr, 0, arr.length - 1);
    }

    private void _doQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partition(arr, start, end);
            _doQuickSort(arr, start, partition - 1);
            _doQuickSort(arr, partition + 1, end);
        }
    }

    /**
     * @param arr
     * @param start
     * @param end
     * @return 锚点在数组中的索引
     * 经过该操作之后，middle左边的元素比他小，middle右边的元素比他大
     * [1]
     */
    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        //注意：锚点选的最后一个元素，那么遍历的时候就不用比较end了
        int middle = start - 1;
        for (int i = start; i < end; i++) {
            //比锚点大的值,都往数组的右边移动
            if (arr[i] <= pivot) {
                middle++;
                swap(arr, i, middle);
            }
        }
        //[10,1,2,10,6,9]   此时end的坑位放的是锚点或者比锚点小的最大值
        //将middle+1的元素和end交换
        swap(arr, middle + 1, end);
        return middle + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
    }

    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
    }
}
