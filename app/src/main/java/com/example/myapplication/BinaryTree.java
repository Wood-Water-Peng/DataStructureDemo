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

    public void preOderRecur(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#");
            return;
        }
        print(node);
        sb.append(node.data);
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


    /**
     * @param root
     * @return 序列化一个二叉树
     * <p>
     * 思路：二叉树的遍历，在遍历的同时对节点进行操作
     */

    public String serialize(TreeNode<Integer> root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    void serialize(TreeNode<Integer> root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.data).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }


    /**
     * @param arr
     * @param target
     * @return 命中则返回索引  未命中则返回-1
     * <p>
     * 1.确定搜索范围
     * 2.在搜索范围不符合条件后，结束搜索
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1); //可以避免(left+right)/2的溢出
            if (arr[middle] == target) {
                //命中
                return middle;
            } else if (arr[middle] < target) {
                //右边搜索
                left = middle + 1;
            } else if (arr[middle] > target) {
                //左边搜索
                right = middle - 1;
            }
        }
        return -1;
    }

    /**
     * @param arr
     * @param target
     * @return 返回左边界
     * {1,2,2,2,3}  target=2  返回1
     */
    public int binarySearchWithLeft(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1); //可以避免(left+right)/2的溢出
            if (arr[middle] == target) {
                right = middle;
            } else if (arr[middle] < target) {
                //右边搜索
                left = middle + 1;
            } else if (arr[middle] > target) {
                //左边搜索
                right = middle;
            }
        }
        return right;
    }

    /**
     * @param arr
     * @param target
     * @return 返回右边界
     * {1,2,2,2,3}  target=2  返回3
     */
    public int binarySearchWithRight(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1); //可以避免(left+right)/2的溢出
            if (arr[middle] == target) {
                left = middle + 1;
            } else if (arr[middle] < target) {
                //右边搜索
                left = middle + 1;
            } else if (arr[middle] > target) {
                //左边搜索
                right = middle;
            }
        }
        return left - 1;
    }


    /**
     * @param root   代表当前查找的节点
     * @param target 目标节点
     * @return
     */
    public int commonBinarySearch(TreeNode<Integer> root, TreeNode<Integer> target) {
        //base condition
        if (root == null) return -1;
        if (root.data == target.data) return root.data;
        if (root.data < target.data) {
            //对下一个节点做操作，即压入一个方法栈，传入当前条件，期待一个返回值
            return commonBinarySearch(root.right, target);
        } else {
            return commonBinarySearch(root.left, target);
        }
    }


    public TreeNode<Integer> generateTree(int[] list) {
        for (int i = 0; i < list.length; i++) {
            TreeNode<Integer> node = new TreeNode<>(list[i]);
            putNode((TreeNode<T>) node);
        }
        return (TreeNode<Integer>) root;
    }

    public BinaryTree() {
    }

    public BinaryTree(int[] array) {
        root = createBinaryTreeByArray(array, 0);
    }

    private TreeNode createBinaryTreeByArray(int[] array, int index) {
        TreeNode tn = null;
        if (index < array.length) {
            int value = array[index];
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(array, 2 * index + 1);
            tn.right = createBinaryTreeByArray(array, 2 * index + 2);
            return tn;
        }
        return tn;
    }



}
