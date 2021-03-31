package com.example.myapplication.tree;

import com.example.myapplication.CollectionUtil;
import com.example.myapplication.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeTest2 {


    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     * 验证二叉树
     *
     * @param root
     * @return 思路：二叉搜索树的中序遍历是一个升序序列
     */
    public boolean isValidBST(TreeNode<Integer> root) {
        _isValidBST(root);
        return isValidBST;
    }

    Stack<Integer> stack = new Stack<>();

    private void _isValidBST(TreeNode<Integer> node) {
        if (node == null) return;
        _isValidBST(node.left);
        if (isValidBST) {
            if (stack.isEmpty()) {
                stack.push(node.data);
            } else {
                Integer pop = stack.pop();
                if (node.data <= pop) {
                    isValidBST = false;
                } else {
                    stack.push(node.data);
                }
            }
        }
        _isValidBST(node.right);
    }


    boolean isValidBST = true;


    /**
     * https://leetcode-cn.com/problems/path-sum-iii/
     * 路径总和
     * 1.路径不需要从根节点开始
     * 2.路径不需要在叶节点结束
     * 3.路径必须从父到子
     *
     * @param root
     * @param sum
     * @return 思路：
     * 双递归
     * 1.当前节点为起点的所有路径
     * 2.当前节点的左节点为起点的所有路径
     * 3.当前节点的右节点为起点的所有路径
     */

    List<List<Integer>> pathList = new ArrayList<>();

    public int pathSum(TreeNode root, int sum) {
        _pathSum(root, sum);
        int total = 0;
        for (int i = 0; i < pathList.size(); i++) {
            List<Integer> list = pathList.get(i);
            for (int j = 0; j < list.size(); j++) {
                total += list.get(j);
            }
        }
//        CollectionUtil.printList3(pathList);
        return total;
    }

    private void _pathSum(TreeNode<Integer> node, int sum) {
        if (node == null) return;
        List<Integer> pathList = new ArrayList<>();
        _path(node, pathList, sum);
        _pathSum(node.left, sum);
        _pathSum(node.right, sum);
    }

    private void _path(TreeNode<Integer> node, List<Integer> list, int sum) {
        if (node == null) return;
        list.add(node.data);
        if (sum - node.data == 0) {
            pathList.add(CollectionUtil.cloneIntegerList(list));
        }
        _path(node.left, list, sum - node.data);
        if (node.left != null) {
            list.remove(list.size() - 1);
        }
        _path(node.right, list, sum - node.data);
        if (node.right != null) {
            list.remove(list.size() - 1);
        }
    }


    private String parseToString(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));
            builder.append("-");
        }
        return builder.toString();
    }


    /**
     * https://leetcode-cn.com/problems/delete-node-in-a-bst/
     * 删除二叉搜索树中的一个指定节点，并维持树的状态不变
     *
     * @param root
     * @param key
     * @return 1.找到这个节点
     * 2.确定该节点是否为叶节点
     * 3.确定该节点是否有右节点
     * 4.确定该节点是否有左节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        _deleteNode(root, key);
        return root;
    }

    /**
     * 这是一个递归操作
     * 每一次的删除操作会有一个返回值
     * 递归的终止条件是找到了要被删除的节点
     *
     * @param node
     */
    private TreeNode<Integer> _deleteNode(TreeNode<Integer> node, int key) {
        if (node == null) return null;
        if (node.data < key) {
            //走右边
            node.right = _deleteNode(node.right, key);
        } else if (node.data > key) {
            //走左边
            node.left = _deleteNode(node.left, key);
        } else {
            //找到了要删除的节点
            //1.该节点是一个叶节点
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.right != null) {
                //2.该节点有右节点，那么我们需要找右树中最小的节点来交换值
                TreeNode<Integer> minNode = _findMinNode(node);
                node.data = minNode.data;
                //继续执行删除操作
                node.right = _deleteNode(node.right, node.data);
            } else {
                //3.该节点有左节点，那么我们需要找左树中最大的节点来交换值
                TreeNode<Integer> maxNode = _findMaxNode(node);
                node.data = maxNode.data;
                //继续执行删除操作
                node.left = _deleteNode(node.left, node.data);
            }

        }
        return node;
    }


    /**
     * 第二种解法
     * <p>
     * 递归+迭代
     *
     * @param node
     * @param key
     * @return
     */
    private TreeNode<Integer> _deleteNode2(TreeNode<Integer> node, int key) {
        if (node == null) return null;
        if (node.data < key) {
            //走右边
            node.right = _deleteNode(node.right, key);
        } else if (node.data > key) {
            //走左边
            node.left = _deleteNode(node.left, key);
        } else {
            //找到了要删除的节点
            //1.该节点是一个叶节点
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left != null && node.right != null) {
                _findMinNode(node).left = node.left;
                return node.right;
            } else if (node.right != null) {
                //2.该节点有右节点，那么我们需要找右树中最小的节点来交换值
                //继续执行删除操作
                return node.right;
            } else {
                //3.该节点有左节点，那么我们需要找左树中最大的节点来交换值
                return node.left;
            }

        }
        return node;
    }

    /**
     * 在二叉搜索树种，找到该节点之后的最小节点
     * 1.找到该节点的右节点
     * 2.从右节点一直向左找，最后找到的节点就是最小节点
     *
     * @param node
     * @return
     */
    private TreeNode<Integer> _findMinNode(TreeNode<Integer> node) {
        if (node == null) return null;
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 在二叉搜索树种，找到该节点之前的最大节点
     * 1.找到该节点的左节点
     * 2.从左节点一直向右找，最后找到的节点就是最大节点
     *
     * @param node
     * @return
     */
    private TreeNode<Integer> _findMaxNode(TreeNode<Integer> node) {
        if (node == null) return null;
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private TreeNode<Integer> _findNode(TreeNode<Integer> node, int key) {
        if (node == null) return null;
        if (node.data == key) {
            return node;
        } else if (node.data > key) {
            return _findNode(node.left, key);
        } else {
            return _findNode(node.right, key);
        }
    }
}
