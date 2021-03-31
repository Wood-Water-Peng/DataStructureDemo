package com.example.myapplication.tree;

import com.example.myapplication.TreeMultiNode;
import com.example.myapplication.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class TreeTest3 {
    /**
     * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
     * 二叉搜索树中的众数（出现频率最高的元素）
     *
     * @param root
     * @return </p>
     * 思路一：
     * 使用一个哈希表,然后中序遍历整棵树，得到有个有序的序列，然后用哈希表统一每一个数出现的次数
     * 缺点：需要额外的空间，中序遍历生成的非递减序列和哈希表
     * </p>
     * 思路二：
     * 在递归的过程中处理，可以省去外面的list和max的开销
     */
    public int[] findMode(TreeNode<Integer> root) {
        ArrayList<Integer> list = new ArrayList<>();
        _findMode(root, list);
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list) {
            ret[i++] = e;
        }
        return ret;
    }

    int base, count, maxCount = 0;

    //中序遍历
    private void _findMode(TreeNode<Integer> node, List<Integer> list) {
        if (node == null) return;
        _findMode(node.left, list);
        if (node.data == base) {
            count++;
        } else {
            base = node.data;
            count = 1;
        }
        if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(base);
        } else if (count == maxCount) {
            list.add(base);
        }
        _findMode(node.right, list);
    }

    /**
     * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
     * 二叉搜索树的最小绝对差
     * 节点值都为非负数
     *
     * @param root
     * @return 思路：
     * 二叉搜索的中序遍历是一个非递减序列
     */
    public int getMinimumDifference(TreeNode root) {
        _getMinimumDifference(root);
        return min;
    }

    int pre = -1;
    int min = Integer.MAX_VALUE;

    private void _getMinimumDifference(TreeNode<Integer> node) {
        if (node == null) return;
        _getMinimumDifference(node.left);
        if (pre == -1) {
            pre = node.data;
        } else {
            min = Math.min(min, node.data - pre);
            pre = node.data;
        }
        _getMinimumDifference(node.right);
    }

    /**
     * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
     * N叉树的最大深度
     *
     * @param root
     * @return 思路：
     * 在递的过程中，无法知道当前节点的高度
     * 在parent节点的高度=子节点高度+1;
     */
    public int maxDepth(TreeMultiNode<Integer> root) {
        return _maxDepth(root);
    }

    public int _maxDepth(TreeMultiNode<Integer> node) {
        if (node == null) return 0;

        List<TreeMultiNode<Integer>> children = node.children;
        List<Integer> heightList = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            heightList.add(_maxDepth(children.get(i)));
        }
        return Collections.max(heightList) + 1;
    }

    /**
     * https://leetcode-cn.com/problems/diameter-of-binary-tree/
     * 二叉树的直径
     * <p>
     * 注意：任意两个节点路径长度中的最大值
     * 不一定穿过根节点
     *
     * @param root
     * @return 思路:
     * 根节点路径=左节点路径+右节点路径+1
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return _diameterOfBinaryTree(root);
    }

    int maxWidth;

    public int _diameterOfBinaryTree(TreeNode node) {
        if (node == null) return 0;
        int left = _diameterOfBinaryTree(node.left);
        int right = _diameterOfBinaryTree(node.right);
        maxWidth = Math.max(left + right + 1, maxWidth);
        return 1 + Math.max(left, right);
    }

    /**
     * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     *
     * @param root
     * @return [2, 3.4, 1, 4.5]
     * 思路：
     * 使用一个栈来保存每一行的数据
     */
    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode<Integer>> stack = new ArrayDeque();
        List<Double> result = new ArrayList<>();
        stack.add(root);
//        _averageOfLevels(root, stack, result);
        return result;
    }

//    private void _averageOfLevels(TreeNode<Integer> node, Deque<TreeNode<Integer>> stack, List<Double> result) {
//        if (node == null) return;
//        int total = 0;
//        while (!stack.isEmpty()) {
//            int size = stack.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode<Integer> first = stack.pop();
//                total += first.data;
//                if (first.left != null) {
//                    stack.addLast(first.left);
//                }
//                if (first.right != null) {
//                    stack.addLast(first.right);
//                }
//            }
//            result.add((double) (total * 1.0f / size));
//            total = 0;
//        }
//
//    }

    /**
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
     * 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        Deque<TreeNode<Integer>> stack = new ArrayDeque();
        List<Integer> result = new ArrayList<>();
        stack.add(root);
        _averageOfLevels(root, stack, result);
        return result;
    }

    private void _averageOfLevels(TreeNode<Integer> node, Deque<TreeNode<Integer>> stack, List<Integer> result) {
        if (node == null) return;
        while (!stack.isEmpty()) {
            int size = stack.size();
            int max = 0;
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> first = stack.pop();
                max = Math.max(max, node.data);
                if (first.left != null) {
                    stack.addLast(first.left);
                }
                if (first.right != null) {
                    stack.addLast(first.right);
                }
            }
            max = 0;
        }

    }

    /**
     * https://leetcode-cn.com/problems/merge-two-binary-trees/
     * 合并二叉树
     *
     * @param t1
     * @param t2
     * @return 重合节点相加
     * 思路：
     * 两个数遍历的顺序保持一致
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return _mergeTrees(t1, t2);
    }

    private TreeNode<Integer> _mergeTrees(TreeNode<Integer> node1, TreeNode<Integer> node2) {

        TreeNode<Integer> newNode = new TreeNode<>(0);
        if (node1 == null && node2 == null) {
            return null;
        } else if (node1 == null && node2 != null) {
            newNode.data = node2.data;
        } else if (node2 == null && node1 != null) {
            newNode.data = node1.data;
        } else {
            newNode.data = (node1.data + node2.data);
        }
        newNode.left = _mergeTrees(node1.left, node2.left);
        newNode.left = _mergeTrees(node1.right, node2.right);
        return newNode;
    }

    /**
     * 二叉树中所有距离为 K 的结点
     *
     * @param root
     * @param target
     * @param K
     * @return 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * <p>
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     * 思路:
     * 1.找到每个节点到目标节点的距离(需要知道父节点到目标节点的距离)
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
return list;
    }

    List<Integer> list = new ArrayList<>();
    int lastIndex;

    private void _distanceK(TreeNode<Integer> node, TreeNode<Integer> target, int K) {
        if (node == null) return;

        if (node.data == target.data) {
            //找到了目标节点
            //分两步走
            //1.从该节点开始继续向下递归
            //2.向上归
            _distanceK2(node, K, 0);
        } else {
            _distanceK(node.left, target, K);
            _distanceK(node.right, target, K);
        }
        lastIndex++;


    }

    private void _distanceK2(TreeNode<Integer> node, int K, int lastDis) {
        if (node == null) return;
        if (lastDis + 1 == K) {
            //找到相距为K的节点
            list.add(node.data);
            //不需要继续向下找了
            return;
        }
        _distanceK2(node.left, K, lastDis + 1);
        _distanceK2(node.right, K, lastDis + 1);
    }

    private int _distanceK3(TreeNode<Integer> node, int K) {
        return 0;
    }


}
