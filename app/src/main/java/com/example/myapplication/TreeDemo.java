package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 二叉树相关
 */
public class TreeDemo {
    private static final String TAG = "TreeDemo";

    /**
     * 翻转一棵二叉树
     *
     * @param root
     * @return 输入：
     * <p>
     * 4
     * /   \
     * 2     7
     * / \   / \
     * 1   3 6   9
     * 输出：
     * <p>
     * 4
     * /   \
     * 7     2
     * / \   / \
     * 9   6 3   1
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeRecur(root);
        return root;
    }

    private void invertTreeRecur(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left == null) {
            root.left = root.right;
            root.right = null;
        } else if (root.right == null) {
            root.right = root.left;
            root.left = null;
        } else {
            TreeNode tmp = left;
            root.left = right;
            root.right = tmp;
        }

    }

    /**
     * @param root
     * @return 每一层平均数的集合
     */
    public List<Double> averageOfLevels(TreeNode root) {
        //保存   dept-average
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        averageOfLevelsRecur(root, map, size, 0);
        List<Double> list = new ArrayList<>();
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer depth = iterator.next();
            Double total = map.get(depth);
            list.add(total * 1.0 / (size.get(depth)));
        }
        return list;
    }

    private void averageOfLevelsRecur(TreeNode<Integer> node, Map<Integer, Double> map, Map<Integer, Integer> size, int depth) {
        if (node == null) return;
        Integer integer = size.get(depth);
        if (integer == null) {
            size.put(depth, 1);
        } else {
            size.put(depth, size.get(depth) + 1);
        }
        if (node.left != null) {
            averageOfLevelsRecur(node.left, map, size, depth + 1);
        }
        if (node.right != null) {
            averageOfLevelsRecur(node.right, map, size, depth + 1);
        }
        //计算出当前层级的
        Double value = map.get(depth);
        if (value != null) {
            map.put(depth, (node.data + value.doubleValue()));
        } else {
            map.put(depth, node.data.doubleValue());
        }
    }

    /**
     * @param root1
     * @param root2
     * @return 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
     * <p>
     * 思路:
     * </p>
     * 1.对树采用中序遍历
     * 2.使用归并排序合并两个子序列
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        middleTraverse(root1, list1);
        middleTraverse(root2, list2);
        if (list1.size() == 0 && list2.size() == 0) {
            return list1;
        } else if (list1.size() == 0) {
            return list2;
        } else if (list2.size() == 0) {
            return list1;
        }
        //使用归并排序
        int i = 0;
        int j = 0;
        while (i <= list1.size() && j <= list2.size()) {

            if (i == list1.size() && j == list2.size()) {
                break;
            }
            if (i == list1.size() && j < list2.size()) {
                result.add(list2.get(j));
                j++;
            } else if (j == list2.size() && i < list1.size()) {
                result.add(list1.get(i));
                i++;
            } else if (list1.get(i) < list2.get(j)) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }
        return result;
    }

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode<Integer> root, int sum) {
        List<List<Integer>> total = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumRecur(root, sum, path, total);
        printList(total);
        return total;
    }

    private void pathSumRecur(TreeNode<Integer> node, int rest, List<Integer> path, List<List<Integer>> total) {
        if (node == null) return;
        if (node.data == rest && node.left == null && node.right == null) {
            path.add(node.data);
            total.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        if (node.data != rest) {
            return;
        }

        //选择一：向左走
        path.add(node.data);
        pathSumRecur(node.left, rest - node.data, path, total);
        path.remove(path.size() - 1);
        //选择二：向右走
        path.add(node.data);
        pathSumRecur(node.right, rest - node.data, path, total);
        path.remove(path.size() - 1);
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode<Integer> root) {

        return isValidBSTRecur(root,root.data,root.data);
    }

    private boolean isValidBSTRecur(TreeNode<Integer> node, int minRight, int maxLeft) {
        boolean flag = true;
        if (node == null) return flag;
        if (node.left != null) {
            if (node.data < node.left.data || node.left.data > maxLeft) {
                return false;
            }
        }
        if (node.right != null) {
            if (node.data > node.right.data || node.right.data < minRight) {
                return false;
            }
        }
        if (node.left != null) {
            flag = isValidBSTRecur(node.left, Math.min(node.data, minRight), Math.max(node.data, maxLeft));
        }
        if (!flag) {
            return flag;
        }
        if (node.right != null) {
            flag = isValidBSTRecur(node.right, Math.min(node.data, minRight), Math.max(node.data, maxLeft));
        }
        return flag;
    }

    //前序遍历
    private boolean preTraverse(TreeNode<Integer> node) {
        if (node == null) return true;
        boolean flag = isValidBSTRecur(node, node.data, node.data);
        if (!flag) {
            return flag;
        }
        if (node.left != null) {
            flag = preTraverse(node.left);
        }
        if (!flag && node.right != null) {
            flag = preTraverse(node.right);
        }
        return flag;
    }


    //中序遍历
    private void middleTraverse(TreeNode<Integer> node, List<Integer> list) {
        if (node == null) return;
        if (node.left != null) {
            middleTraverse(node.left, list);
        }
        list.add(node.data);
        if (node.right != null) {
            middleTraverse(node.right, list);
        }
    }

    public <T> void printList(List<List<T>> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append("[");
            for (int j = 0; j < list.get(i).size(); j++) {
                buffer.append(list.get(i).get(j));
                buffer.append(",");
            }
            buffer.append("]");
            buffer.append("\n");
        }
        buffer.append("}");
        Log.i(TAG, "printList ->" + buffer.toString());
    }

    public void printArray(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
            buffer.append(",");
        }
        buffer.append("}");
        Log.i(TAG, "printArray ->" + buffer.toString());
    }

}
