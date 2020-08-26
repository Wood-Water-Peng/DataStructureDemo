package com.example.myapplication;

//
//数组相关
//
public class CollectionUtil {
    //数组中出现次数超过数组长度一半的数字
    //分析：
    //假设数组{2,4,3,3,3,3,5},长度7
    //如果满足上述条件，那么中位数的索引对应的那个数，必然就是
    //
    public int checkHalfCountNumber(int[] arr) {
        // 分析： 这个数字只有1或者0个
        int middle = arr.length >> 1;
        int element = findKElement(arr, middle);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (element == arr[i]) {
                count++;
            }
        }
        if (count > middle) {
            return middle;
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
            int mid = partition3(list, left, right);
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
        middle = (left - 1);
        pivot = arr[right]; //选取最左边的元素为基准数

        for (int i = left; i < right; i++) {
            //比基准点大的元素交换到右边
            if (arr[i] <= pivot) {
                middle++;
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
                j--;
            }
            while (arr[i] <= pivot && i < j) {
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


}
