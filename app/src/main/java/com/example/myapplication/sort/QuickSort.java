package com.example.myapplication.sort;

/**
 * @author jacky.peng
 * 快速排序
 * <p>
 * 基本思路：
 * <p>
 * 1.找到数组中的锚点
 * 2.以这个锚点为中心，将数组分为两段，左边的<=锚点，右边的>=锚点
 * 3.重复步骤一和步骤二，直到数组不能被分割
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int start = 0;
        int end = arr.length - 1;
        doQuickSort(arr, start, end);

    }

    private static void doQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partition(arr, start, end);
            doQuickSort(arr, start, partition - 1);
            doQuickSort(arr, partition + 1, end);
        }
    }

    /**
     * 扫描将数组均分，并返回锚点的索引
     *
     * @param arr
     * @return 锚点的索引
     * <p>
     * 选取第一个数为锚点
     */
    private static int partition(int[] arr, int start, int end) {
        //1.选定锚点
        int pivot = arr[start];
        //2.定义比锚点大的坑位
        int middle = end + 1;
        //3.遍历数组，交换元素
        for (int i = end; i > start; i--) {
            //找到比锚点大的元素
            if (arr[i] > pivot) {
                middle--;
                swap(arr, i, middle);
            }
        }
        //此时坑位中和坑位右边的元素都比锚点大
        //将锚点和坑位左边一位的元素交换
        swap(arr, middle - 1, start);

        return middle - 1;
    }

    /**
     * 扫描将数组均分，并返回锚点的索引
     *
     * @param arr
     * @return 锚点的索引
     * <p>
     * 选取最右边的元素作为锚点
     */
    private static int partition3(int[] arr, int start, int end) {
        //1.选定锚点
        int pivot = arr[end - 1];
        //2.定义比锚点小的元素的坑位
        int middle = start - 1;
        //3.扫描数组，找到比锚点小的元素，和当前坑位的元素交换
        for (int i = start; i <= end; i++) {
            if (arr[i] < pivot) {
                middle++;

                swap(arr, middle, i);
            }
        }
        //4.此时middle左边(包括middle)的元素<pivot,右边的元素>=pivot
        //5.确定pivot的位置
        swap(arr, middle + 1, end - 1);
        return middle + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
