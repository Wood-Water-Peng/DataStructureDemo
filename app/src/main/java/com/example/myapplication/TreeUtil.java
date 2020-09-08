package com.example.myapplication;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeUtil {
    public static final String TAG = "TreeUtil";

    /**
     * @param root
     * @param subTree
     * @return subTree是否为root的子结构
     * <p>
     * 思路：
     * <p>
     * 1.在root中找到subTree的根节点
     * <p>
     * 2.判断根节点下的每个节点是都相同
     */
    public boolean isSubTree(TreeNode<Integer> root, TreeNode<Integer> subTree) {
        //在root中找到subTree的根节点 注:可能存在多个与根节点相同的值
        boolean result = false;
        if (root != null && subTree != null) {
            if (root.data == subTree.data) {
                result = isNodeEqual(root, subTree);
            }
            if (!result) {
                isSubTree(root.left, subTree);
            }
            if (!result) {
                isSubTree(root.right, subTree);
            }
        }
        return result;
    }

    private boolean isNodeEqual(TreeNode<Integer> node, TreeNode<Integer> subNode) {
        if (subNode == null) return true;
        if (node == null) return false;
        if (node.data != subNode.data) {
            return false;
        }
        return isSubTree(node.left, subNode.left) && isSubTree(node.right, subNode.right);
    }

    /**
     * 从上到下打印二叉树
     */
    public void printTreeFromTop2Bottom(TreeNode<Integer> root) {
        //辅助容器
        Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
            recurTree(queue);
        }
    }

    private void recurTree(Queue<TreeNode<Integer>> queue) {
        TreeNode<Integer> node = queue.poll();
        if (node == null) return;
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
        printNode(node);
        //下次递归的节点从队列中获取
        recurTree(queue);
    }

    private void printNode(TreeNode<Integer> node) {
        if (node != null) {
            Log.i(TAG, "node ->" + node.data);
        }
    }


}
