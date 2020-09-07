package com.example.myapplication;

/**
 * 最大二叉堆----父节点的值大于等于它的两个子节点
 */
public class MaxBinaryHeap<Key extends Comparable<Key>> {
    // 存储元素的数组
    private Key[] pq;
    // 当前 Priority Queue 中的元素个数
    private int N = 0;

    public MaxBinaryHeap(int cap) {
        // 索引 0 不用，所以多分配一个空间
        pq = (Key[]) new Comparable[cap + 1];
    }

    /* 返回当前队列中最大元素 */
    public Key max() {
        return pq[1];
    }

    /* 交换数组的两个元素 */
    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 下沉第 k 个元素，以维护最大堆性质
     * 下沉需要和左右两个child分别比较
     */
    private void sink(int k) {

    }

    /**
     * @param k 上浮只需要和父节点比较
     */
    private void swim(int k) {
        // 如果浮到堆顶，就不能再上浮了
        while (k > 1 && less(parent(k), k)) {
            // 如果第 k 个元素比上层大
            // 将 k 换上去
            exch(parent(k), k);
            k = parent(k);
        }
    }

    // 父节点的索引
    int parent(int root) {
        return root / 2;
    }

    // 左孩子的索引
    int left(int root) {
        return root * 2;
    }

    // 右孩子的索引
    int right(int root) {
        return root * 2 + 1;
    }
}
