package com.example.myapplication.tree;

import com.example.myapplication.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树迭代器
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {
    boolean hasNode = true;
    Stack<TreeNode<Integer>> stack;

    public BSTIterator(TreeNode<Integer> root) {
        stack = new Stack<>();
        _pushLeft(root);
    }

    /**
     * 将node和他的左节点，全部压入栈中
     * 此时栈顶的元素是最小的
     *
     * @param node
     */
    private void _pushLeft(TreeNode<Integer> node) {
        if (node == null) return;
        stack.push(node);
        _pushLeft(node.left);
    }

    public int next() {
        //弹出栈顶元素，肯定是最小的
        TreeNode<Integer> minNode = stack.pop();
        //判断该节点是否有右节点，因为下一个最小的值肯定是存在于右子树，且是右子树的最小值
        if (minNode.right != null) {
            _pushLeft(minNode.right);
        }
        return minNode.data;
    }


    public boolean hasNext() {
        return !stack.isEmpty();
    }



}
