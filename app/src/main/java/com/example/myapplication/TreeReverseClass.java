package com.example.myapplication;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TreeReverseClass {
    private static final String TAG = "TreeReverseClass";
    static int count = 0;
    static LinkedList<Integer> list = generateLinkList();

    //先遍历左节点
    public static int reverseLeft(TreeNode node) {
        if (node == null) return count;
        Log.i(TAG, "reverseLeft count:" + count + "--data:" + node.data);
        count++;
        reverseLeft(node.left);
        reverseLeft(node.right);
        return count;
    }


    public static TreeNode<Integer> createBinaryTree(TreeNode<Integer> head) {
        if (head == null) return null;

        if (list == null || list.isEmpty()) return head;
        Integer data = list.remove();
        if (data == null) return null;
        if (data.intValue() > head.data.intValue()) {
            TreeNode<Integer> right = new TreeNode<>(data);
            head.right = createBinaryTree(right);
        } else {
            TreeNode<Integer> left = new TreeNode<>(data);
            head.left = createBinaryTree(left);
        }
        return head;
    }


    public static LinkedList<Integer> generateLinkList() {
        LinkedList linkedList = new LinkedList();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int nextInt = random.nextInt(100);
            linkedList.add(new Integer(nextInt));
        }
        return linkedList;
    }

    //生成元素不相同的列表
    public static LinkedList<Integer> generateLinkListDifferent(int len) {
        LinkedList linkedList = new LinkedList();
        Random random = new Random();
        int count = 0;
        do {
            int nextInt = random.nextInt(100);
            if (!linkedList.contains(nextInt)) {
                linkedList.add(new Integer(nextInt));
                count++;
            }
        } while ((count < len));
        return linkedList;
    }


    // 后续遍历  左-右-根
    // 某一序列输入的数字都不相同
    // 判断某一序列是不是二叉树后续遍历的结果
    // 将这个序列拆分为多个小的二叉树，递归判断，如果所有子序列都满足二叉树条件，那么整个序列就满足后续遍历二叉树条件
    public static boolean isPostReverseTree(LinkedList<Integer> list, int length) {
        if (list == null) return false;
        int root = list.getLast();
        //在二叉搜索数中，左数的节点小于根节点
        int i = 0;
        for (; i < length - 1; i++) {
            //找到右节点
            if (list.get(i) > root)
                break;
        }
        int j = i;
        for (; j < length - 1; j++) {
            //右节点不能比根节点值小
            if (list.get(j) < root) return false;
        }
        boolean left = true;
        if (i > 0) {
            isPostReverseTree(list, i);
        }
        boolean right = true;
        if (i < length - 1) {
            right = isPostReverseTree(list, length - i - 1);
        }
        return left && right;
    }
}
