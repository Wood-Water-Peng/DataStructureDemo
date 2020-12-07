package com.example.myapplication.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆排序
 * Binary Heap data structure
 * 最大堆   父节点的值>子节点
 * 最小堆   父节点的值<子节点
 * <p>
 * 利用数组创建最大堆和最小堆
 */
public class HeapSort<E extends Comparable<E>> {
    List<E> maxList = new ArrayList<>();

    /**
     * 1.定义出heapify方法 最大堆
     * <p>
     * 能够对一个节点进行heapify操作的前提是他的子节点已经满足max-heap或者min-heap结构
     *
     * @param n 堆的大小，通过他和i的结合才能正确获取tree和arr的对应
     * @param i 需要heapify的元素在arr中的索引
     *          <p>
     *          对于n,i的二叉堆和数据的对应关系
     *          i  左节点       2 * i + 1   右节点    2 * i + 2
     *          <p>
     *          该方法的作用:将i元素下沉到适当位置
     */

    public void heapifyMax(int[] arr, int n, int i) {
        //1.假定该节点是最大的节点
        int largest = i;
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        //分别和左右子节点比较
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        //如果最大值更改过
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            //继续位置为largest的节点进行heapifyMax操作
            heapifyMax(arr, n, largest);
        }
    }

    /**
     * 将输入的数组以最大堆方式排序
     *
     * @param arr
     */
    public void maxHeadSort(int[] arr) {
        //问题：第一步该如何入手？
        //因为完全二叉树的特性，并且heapifyMax一个元素的条件是他的子节点都已经被heapifyMax过了,那么这里要采用bottom-up的方式
        //找到树中最深的非叶节点
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyMax(arr, n, i);
        }

    }


    // Returns position of parent
    private int parent(int pos) {
        return pos / 2;
    }

    // Below two functions return left and
    // right children.
    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * 插入元素
     *
     * @param e
     */
    public void insert(E e) {
        if (e == null) throw new IllegalArgumentException("e cannot be null");
        if (maxList.isEmpty()) {
            maxList.add(e);
        }
        int current = maxList.size();
        //找到最后一个节点
        maxList.add(current, e);
        //将最后一个节点和父节点比较
        while (e.compareTo(maxList.get(parent(current))) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * 将最大的元素弹出
     *
     * @return
     */
    public E pop() {
        E max = maxList.get(0);
        E last = maxList.remove(maxList.size() - 1);
        maxList.set(0, last);
        heapifyMax(maxList.size(), 0);
        return max;
    }

    public void heapifyMax(int n, int i) {
        //1.假定该节点是最大的节点
        int largest = i;
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        //分别和左右子节点比较
        if (l < n && maxList.get(l).compareTo(maxList.get(largest)) > 0) {
            largest = l;
        }
        if (r < n && maxList.get(r).compareTo(maxList.get(largest)) > 0) {
            largest = r;
        }
        //如果最大值更改过
        if (largest != i) {
            E swap = maxList.get(i);
            maxList.set(i, maxList.get(largest));
            maxList.set(largest, swap);
            //继续位置为largest的节点进行heapifyMax操作
            heapifyMax(n, largest);
        }
    }

    private void swap(int i, int j) {
        E tmp = maxList.get(i);
        maxList.set(i, maxList.get(j));
        maxList.set(j, tmp);
    }
}
