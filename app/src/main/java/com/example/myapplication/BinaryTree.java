package com.example.myapplication;

import android.util.Log;

public class BinaryTree<T extends Comparable<? super T>> {
    private static final String TAG = "BinaryTree";
    int size;
    TreeNode<T> root;

    public boolean putNode(TreeNode<T> node) {
        if (node == null) return false;
        if (root == null) {
            root = node;
        } else {
            TreeNode<T> t = root;
            TreeNode<T> parent = null;
            while (t != null) {
                parent = t;
                if (node.data.compareTo(t.data) < 0) {
                    t = t.left;
                } else if (node.data.compareTo(t.data) > 0) {
                    t = t.right;
                } else {
                    return false;
                }
            }

            //当t==null
            if (parent.data.compareTo(node.data) < 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        }
        size++;
        return true;
    }

    public boolean deleteNode(TreeNode<T> node) {
        return false;
    }

    public TreeNode<T> findMin() {
        TreeNode<T> curNode = root;
        TreeNode<T> parent = null;

        while (curNode != null) {
            parent = curNode;
            curNode = curNode.left;
        }
        return parent;
    }

    public TreeNode<T> findMax() {
        TreeNode<T> curNode = root;
        TreeNode<T> parent = null;

        while (curNode != null) {
            parent = curNode;
            curNode = curNode.right;
        }
        return parent;
    }

    public int getSize() {
        return size;
    }

    public int getLevel() {
        return -1;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    //前序遍历  根-左-右
    public void preOderRecur(TreeNode node) {
        if (node == null) return;
        print(node);
        preOderRecur(node.left);
        preOderRecur(node.right);
    }

    /**
     * 层级遍历
     * 用一个链表辅助
     *
     * @param node
     */
    public void levelOrderRecur(TreeNode node) {

    }

    public void print(TreeNode node) {
        Log.i(TAG, "data-> " + node.data);
    }
}
