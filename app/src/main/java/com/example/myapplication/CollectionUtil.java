package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//
//数组相关
//
public class CollectionUtil {
    private static final String TAG = "CollectionUtil";

    //数组中出现次数超过数组长度一半的数字
    //分析：
    //假设数组{2,4,3,3,3,3,5},长度7
    //如果满足上述条件，那么中位数的索引对应的那个数，必然就是
    //
    public int checkHalfCountNumber(int[] arr) {
        // 分析： 这个数字只有1或者0个
        int middle = arr.length >> 1;
//        int start = 0;
//        int end = arr.length - 1;
//        int index = partition(arr, start, end);
//        while (index != middle) {
//            if (index > middle) {
//                end = index - 1;
//                index = partition(arr, start, end);
//            } else {
//                start = index + 1;
//                index = partition(arr, start, end);
//            }
//        }
        fastSort(arr);
        int count = 0;
        int element = arr[middle];
        for (int i = 0; i < arr.length; i++) {
            if (element == arr[i]) {
                count++;
            }
        }
        if (count > middle) {
            return element;
        } else {
            return Integer.MIN_VALUE;
        }
    }


    //快速排序
    public void fastSort(int[] list) {
        doSort(list, 0, list.length - 1);
    }

    private void doSort(int[] list, int left, int right) {
        if (left < right) {
            int mid = partition3Temp(list, left, right);
            doSort(list, left, mid - 1);
            doSort(list, mid + 1, right);
        }
    }

    /**
     * @param arr
     * @param left  0-arr.length-1
     * @param right 0-arr.length-1
     * @return 基准数的index
     * <p>
     * 从左往右遍历
     * 这个方法可以看做  找到数组中某一个元素在数组的位置(第几大)
     */
    private int partition(int[] arr, int left, int right) {
        int middle, pivot;
        middle = (left - 1); //更小的元素的索引
        pivot = arr[right]; //选取最右边的元素为基准数

        for (int i = left; i < right; i++) {
            //比基准点小的元素交换到左边
            if (arr[i] <= pivot) {
                middle++; //指向当前比基准数小的元素索引
                //交换位置
                int tmp = arr[i];
                arr[i] = arr[middle];
                arr[middle] = tmp;
            }
        }

        //middle+1即为基准数的位置
        int tmp = arr[middle + 1];
        arr[middle + 1] = arr[right];
        arr[right] = tmp;
        return middle + 1;
    }

    //从右往左比较，假设基准点所在的位置是最大的，那么比基准点大的，就要放到基准点的右边，同时基准点向左偏移
    private int partition2(int[] arr, int left, int right) {
        int middle, pivot;
        middle = (right + 1);
        pivot = arr[left]; //选取最左边的元素为基准数

        for (int i = right; i > left; i--) {
            //比基准点大的元素交换到右边
            if (arr[i] >= pivot) {
                middle--;
                //交换位置
                int tmp = arr[i];
                arr[i] = arr[middle];
                arr[middle] = tmp;
            }
        }

        //middle-1即为基准数的位置
        int tmp = arr[middle - 1];
        arr[middle - 1] = arr[left];
        arr[left] = tmp;
        return middle - 1;
    }


    /**
     * @param arr
     * @param left
     * @param right
     * @return 采用双向遍历
     */
    private int partition3(int[] arr, int left, int right) {
        int pivot = arr[left]; //选取最左边的元素为基准数
        int i = left;
        int j = right;
        while (i != j) {

            while (arr[j] >= pivot && i < j) {
                //j会在<=基准点的元素索引停下
                j--;
            }
            while (arr[i] <= pivot && i < j) {
                //i会在>=基准点的元素索引停下
                i++;
            }

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        arr[left] = arr[i];
        arr[i] = pivot;

        return i;
    }

    /**
     * @param arr
     * @param k
     * @return 找到数组中第k大的元素 或者说第(arr.len-k+1)小的元素
     * 0<k<=arr.len
     * <p>
     * 对于数组{2，3，4} k从1-3
     * <p>
     * 而partition却是从0-2
     */
    public int findKElement(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return Integer.MIN_VALUE;
        }
        int start = 0;
        int end = arr.length - 1;
        int partition = partition(arr, start, end);

        while (partition != arr.length - k) {
            if (partition < arr.length - k) {
                //说明要找到的元素在partition的右边
                start = partition + 1;
                partition = partition(arr, start, end);
            } else if (partition > arr.length - k) {
                //左边
                end = partition - 1;
                partition = partition(arr, start, end);
            }
        }
        return arr[partition];
    }


    /**
     * @param arr
     * @return 最小的k个数
     * <p>
     * 比如输入{4,5,1,6,2,7,3,8}
     * <p>
     * 那么输出应该是{1,2,3,4}
     * <p>
     * 暴力方法：排序之后取出前k个，时间复杂度为O(nlogn)
     */
    public int[] findMinKCount(int[] arr, int k) {
        //根据找到第k大的数的思路，我们可以让比k小的数都在其左边，比他大的都在他的右边
        if (k > arr.length) return arr;
        int start = 0;
        int end = arr.length - 1;
        int index = partition(arr, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(arr, start, end);
            } else {
                start = index + 1;
                index = partition(arr, start, end);
            }
        }
        printArr(arr);
        int[] kInts = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            kInts[i] = arr[i];
        }
        return kInts;
    }

    /**
     * @param arr
     * @return 和最大的连续子数组
     */
    public int[] maxContinuousArray(int[] arr) {
        int sum = 0;
        int start = 0;
        int end = 0;
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            sum += value;
            if (sum < value) {
                start = i;
                sum = value;
            }

            if (sum > maxSum) {
                end = i;
            }
            maxSum = sum;
        }
        int[] max = new int[end + 1 - start];
        int count = 0;
        for (int i = start; i < end + 1; i++) {
            max[count] = arr[i];
            count++;
        }
        return max;
    }

    /**
     * @param arr
     * @return 把数据排列成一个最小的数
     * 例如输入{3,32,321}  输出应该为321323
     * <p>
     * 解法一：将所有的数进行排列，n个数共有n!个排列，然后找出排列中的最小值
     */
    public int rankMinNumber(int[] arr) {
        return -1;
    }

    public static void print(int arr) {
        Log.i(TAG, "print ->" +arr);
    }
    public static void printArr(int[] arr) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < arr.length; i++) {
            buffer.append(arr[i]);
            buffer.append(",");
        }
        buffer.append("}");
        Log.i(TAG, "printArr ->" + buffer.toString());
    }

    public void printList(List<List<Integer>> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append("[");
            for (int j = 0; j < list.get(i).size(); j++) {
                buffer.append(list.get(i).get(j));
                buffer.append(",");
            }
            buffer.append("]");
        }
        buffer.append("}");
        Log.i(TAG, "printList ->" + buffer.toString());
    }


    /**
     * 输入一个正数的数组，尝试着将其分成两个和相等的子数组
     * 如输入{1, 2, 3, 4}，返回true
     * {1, 4} & {2, 3}
     * <p>
     * 思路：暴力解法  假设数组的总和为S，那么找出和为S/2的的所有子数组
     */

    public boolean partitionEqualArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if ((sum >> 1) % 2 != 0) {
            //奇数
            return false;
        }

        return equalArrayRecur(arr, sum / 2, 0);
    }

    private boolean equalArrayRecur(int[] arr, int sum, int index) {
        return false;
    }

    /**
     * 双端遍历法
     *
     * @param arr
     * @param left
     * @param right
     */
    private int partition3Temp(int[] arr, int left, int right) {
        int i = left; //从左往右扫描，找到比基准值大的数
        int j = right; //从右往左扫描，找到比基准值小的数
        int pivot = arr[right];
        while (i < j) {
            while (arr[i] <= pivot && i < j) {
                i++;
            }

            while (arr[j] >= pivot && i < j) {
                j--;
            }

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        //问题:此时i是否等于j?
        arr[right] = arr[i];
        arr[i] = pivot;
        return i;
    }

    /**
     * 交换两个数组的某一个元素，使其和相等
     *
     * @param array1
     * @param array2
     * @return
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        int[] result = new int[2];
        if (array1.length == 0 || array2.length == 0) return result;
        int sum1 = 0;
        for (int i = 0; i < array1.length; i++) {
            sum1 += array1[i];
        }
        int sum2 = 0;
        for (int i = 0; i < array2.length; i++) {
            sum2 += array2[i];
        }
        int diff = sum1 - sum2;
        if (Math.abs(diff) % 2 != 0) {
            //奇数
            return new int[0];
        }

        //将某个数组映射为hash表，用空间换时间
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            map.put(array1[i], i);
        }
        boolean hit = false;
        for (int i = 0; i < array2.length; i++) {
            if (diff == 0) {
                //找到相同的数即可
                if (map.containsKey(array2[i])) {
                    result[0] = array1[i];
                    result[1] = array2[i];
                    hit = true;
                    break;
                }
            } else if (diff > 0) {
                //sum1>sum2
                if (map.containsKey(array2[i] + (diff >> 1))) {
                    result[0] = array1[map.get(array2[i] + (diff >> 1))];
                    result[1] = array2[i];
                    hit = true;
                    break;
                }
            } else {
                //sum1<sum2
                if (map.containsKey(array2[i] - (-diff >> 1))) {
                    result[0] = array1[map.get(array2[i] - (-diff >> 1))];
                    result[1] = array2[i];
                    hit = true;
                    break;
                }
            }
        }
        if (hit) {
            return result;
        } else {
            return new int[0];
        }
    }

    /**
     * 和为n的k个数的组合
     *
     * @param k
     * @param n
     * @return 如k=3,n=7
     * [[1,2,4]]
     * <p>
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * <p>
     * 思路：
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        return null;
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * @param n
     * @param k
     * @return 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    List<List<Integer>> total = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combineRecur(n, 0, k);
        printList(total);
        return total;
    }

    /**
     * @param index 当前递归的索引
     * @param k     总的路径数   相当于数的深度
     */
    private void combineRecur(int n, int index, int k) {
        if (k == list.size()) {
            total.add(new ArrayList<>(list));
            return;
        }

        //从index到n选一个
        for (int i = index; i < n; i++) {
            list.add(i + 1);
            combineRecur(n, i + 1, k);
            list.remove(list.size() - 1);
        }
    }

}
