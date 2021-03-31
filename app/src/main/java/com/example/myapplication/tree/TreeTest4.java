package com.example.myapplication.tree;

import com.example.myapplication.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.FutureTask;

/**
 * @Author jacky.peng
 * @Date 2021/3/8 10:10 AM
 * @Version 1.0
 */
public class TreeTest4 {
    TreeNode pre;

    /**
     * 将二叉树展开为链表
     *
     * @param root 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     *             展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *             <p>
     *             思路:
     *             针对一个完整节点树来分析
     *             1. 将根节点的右节点=根节点的左节点
     *             2. 左节点的右节点=根节点的右节点
     */
    public void flatten(TreeNode root) {
        _postTraverseFlatten(root);
    }

    public void _postTraverseFlatten(TreeNode node) {
        if (node == null) return;
        _postTraverseFlatten(node.right);
        _postTraverseFlatten(node.left);
        node.right = pre;
        node.left = null;
        pre = node;
    }


    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * @param root
     * @return 思路：
     * <p>
     * a.广度搜索优先
     * <p>
     * 1.层级遍历
     * 2.找到每层最右边的数
     * <p>
     * b.深度搜索优先
     * 1.根节点-右节点-左节点
     * 2.用数组记录
     */
    public List<Integer> rightSideView(TreeNode<Integer> root) {
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode<Integer>> level = new ArrayDeque<>();
        if (root == null) {
            return levelList;
        }
        level.add(root);
        while (!level.isEmpty()) {
            int size = level.size();
            levelList.add(level.element().data);
            for (int i = 0; i < size; i++) {
                //从左到右
                TreeNode<Integer> poll = level.poll();
                if (poll.right != null) {
                    level.add(poll.right);
                }
                if (poll.left != null) {
                    level.add(poll.left);
                }
            }
        }
        return levelList;
    }

    /**
     * b.深度搜索优先
     * 1.根节点-右节点-左节点
     * 2.用数组记录
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        _rightSideView2(root, map, 0);
        Iterator<Integer> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    private void _rightSideView2(TreeNode<Integer> node, Map<Integer, Integer> map, int level) {
        if (node == null) return;
        if (map.get(level) == null) map.put(level, node.data);

        _rightSideView2(node.right, map, level + 1);
        _rightSideView2(node.left, map, level + 1);

    }

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * @param root
     * @return 思路：
     * 1.使用一个map,key为当前的层级，value为值
     */
    List<String> pathList = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        _binaryTreePaths(root, map, 0);
        return pathList;
    }

    public void _binaryTreePaths(TreeNode<Integer> node, Map<Integer, Integer> map, int level) {
        if (node == null) return;
        map.put(level, node.data);
        _binaryTreePaths(node.left, map, level + 1);
        _binaryTreePaths(node.right, map, level + 1);
        if (node.left == null && node.right == null) {
            Iterator<Integer> iterator = map.values().iterator();
            StringBuilder builder = new StringBuilder();
            while (iterator.hasNext()) {
                builder.append(iterator.next());
                if (iterator.hasNext()) {
                    builder.append("->");
                }
            }
            pathList.add(builder.toString());
        }
        map.remove(level);
    }

    /**
     * 计算给定二叉树的所有左叶子之和。
     *
     * @param root
     * @return 思路：
     * 1.首先定义左叶子   该节点某某一节点的左子节点，且该节点为叶节点
     */
    int leftSum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        _sumOfLeftLeaves(root, true);
        return leftSum;
    }

    public void _sumOfLeftLeaves(TreeNode<Integer> node, boolean isLeft) {
        if (node == null) return;
        _sumOfLeftLeaves(node.left, true);
        _sumOfLeftLeaves(node.right, false);
        if (isLeft && node.left == null && node.right == null) {
            leftSum += node.data;
        }
    }

    /**
     * 给定一个二叉树，计算 整个树 的坡度
     *
     * @param root
     * @return 1.所有节点的坡度和
     * <p>
     * 一个节点的坡度=|左子树的坡度-右子树的坡度|
     */
    int tiltSum = 0;

    public int findTilt(TreeNode root) {
        _findTilt(root);
        return tiltSum;
    }

    //返回当前节点的和
    public int _findTilt(TreeNode<Integer> node) {
        if (node == null) return 0;
        int left = _findTilt(node.left);
        int right = _findTilt(node.right);

        int abs = Math.abs(left - right);
        tiltSum += abs;
        return node.data + left + right;
    }

    /**
     * 一棵树是否为另一颗树的子树
     *
     * @param s
     * @param t
     * @return s是否包含和t结构相同的子树
     */

    boolean isSubtree = false;

    public boolean isSubtree(TreeNode<Integer> s, TreeNode<Integer> t) {
        if (s != null && t == null) return true;
        if (s == null && t != null) return false;
        if (s == null && t == null) return false;

        //在s中找到t的根节点
        _isSubtree(s, t);
        return isSubtree;
    }

    public void _isSubtree(TreeNode<Integer> s, TreeNode<Integer> t) {
        if (s == null || t == null) return;
        if (s.data == t.data) {
            //找到了头结点
            boolean result = _isSubtreeEqual(s, t);
            if (result) {
                isSubtree = true;
                return;
            }

        }
        _isSubtree(s.left, t);
        _isSubtree(s.right, t);
    }

    public boolean _isSubtreeEqual(TreeNode<Integer> s, TreeNode<Integer> t) {
        boolean isEqual = true;
        if (s == null && t != null) return false;
        if (s != null && t == null) return false;
        if (s != null && t != null) {
            isEqual = (s.data == t.data);
        } else {
            return true;
        }
        boolean left = _isSubtreeEqual(s.left, t.left);
        boolean right = _isSubtreeEqual(s.right, t.right);
        return isEqual && left && right;
    }

    /**
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * <p>
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     *
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        //TODO
        return null;
    }
}
