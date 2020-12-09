package com.example.myapplication.structure;

import com.example.myapplication.TreeNode;
import com.example.myapplication.TreeUtil;

import java.util.List;

/**
 * 二叉树
 */
public class BinaryTreeStructure {
    //中序遍历
    public <T> void inOrderTraversal(TreeNode<T> node) {
        if (node == null) return;
        TreeUtil.printNode(node);
        inOrderTraversal(node.left);
        inOrderTraversal(node.right);
    }

    static int rootIndex = 0;

    /**
     * 根据前序和中序构建一颗二叉树
     * 前序遍历的第一个元素肯定是root
     *
     * @param inorderSequence  中序遍历
     * @param preorderSequence 前序遍历
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> buildTree(List<T> inorderSequence, List<T> preorderSequence) {
        //1.找到前序遍历的第一个元素，作为根节点

        //2.找到该root在中序序列中的索引

        //3.继续调用buildTree，indexOf左边的返回值作为root的左节点，indexOf右边的返回值作为root的右节点
        TreeNode<T> tree = _buildTree(preorderSequence, inorderSequence, 0, inorderSequence.size() - 1);
        return tree;
    }

    private static <T> TreeNode<T> _buildTree(List<T> preorderSequence, List<T> inorderSequence, int start, int end) {
        if (start > end) {
            //无效节点
            return null;
        }
        T t = preorderSequence.get(rootIndex++);
        TreeNode<T> root = new TreeNode<>(t);
        if (start == end) {
            //叶节点
            return root;
        }
        int indexOf = inorderSequence.indexOf(t);
        if (indexOf == -1) {
            //没有找到
            throw new IllegalStateException("传入的序列不匹配");
        }
        root.left = _buildTree(preorderSequence, inorderSequence, start, indexOf - 1);
        root.right = _buildTree(preorderSequence, inorderSequence, indexOf + 1, end);
        return root;
    }

    /**
     * 根据后序和中序构建一颗二叉树
     * 后序遍历的第一个元素肯定是root，可以作为上一个元素的右节点
     *
     * @param inorderSequence   中序遍历
     * @param postorderSequence 后序遍历
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> buildTree2(List<T> inorderSequence, List<T> postorderSequence) {
        rootIndex = postorderSequence.size() - 1;
        //1.找到前序遍历的最后一个元素，作为根节点

        //2.找到该root在中序序列中的索引

        //3.继续调用buildTree，indexOf左边的返回值作为root的左节点，indexOf右边的返回值作为root的右节点
        TreeNode<T> tree = _buildTree2(postorderSequence, inorderSequence, 0, inorderSequence.size() - 1);
        return tree;
    }

    private static <T> TreeNode<T> _buildTree2(List<T> postorderSequence, List<T> inorderSequence, int start, int end) {
        if (start > end) {
            //无效节点
            return null;
        }
        T t = postorderSequence.get(rootIndex--);
        TreeNode<T> root = new TreeNode<>(t);
        if (start == end) {
            //叶节点
            return root;
        }
        int indexOf = inorderSequence.indexOf(t);
        if (indexOf == -1) {
            //没有找到
            throw new IllegalStateException("传入的序列不匹配");
        }
        //注意：这里的顺序必须是先右后左
        root.right = _buildTree2(postorderSequence, inorderSequence, indexOf + 1, end);
        root.left = _buildTree2(postorderSequence, inorderSequence, start, indexOf - 1);
        return root;
    }
}
