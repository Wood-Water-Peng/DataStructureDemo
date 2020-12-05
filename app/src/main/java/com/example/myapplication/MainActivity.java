package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.sort.MergeSort;
import com.example.myapplication.sort.QuickSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>();
        TreeNode<Integer> node_10 = new TreeNode<>(10);
        TreeNode<Integer> node_5 = new TreeNode<>(5);
        TreeNode<Integer> node_15 = new TreeNode<>(15);
        TreeNode<Integer> node_6 = new TreeNode<>(6);
        TreeNode<Integer> node_20 = new TreeNode<>(20);
        binaryTree1.root = node_10;
        binaryTree1.root.left = node_5;
        binaryTree1.root.right = node_15;
        node_15.left = node_6;
        node_15.right = node_20;

//        binaryTree.putNode(new TreeNode<Integer>(8));
//        binaryTree.putNode(new TreeNode<Integer>(6));
//        binaryTree.putNode(new TreeNode<Integer>(10));
//        binaryTree.putNode(new TreeNode<Integer>(5));
//        binaryTree.putNode(new TreeNode<Integer>(7));
//        binaryTree.putNode(new TreeNode<Integer>(9));
//        binaryTree.putNode(new TreeNode<Integer>(11));
//        binaryTree.putNode(new TreeNode<Integer>(3));
//        binaryTree.putNode(new TreeNode<Integer>(12));
//        int[] ints = {1,2,3};
//        int index = binaryTree.binarySearchWithLeft(ints, 2);
//        Log.i(TAG, "binaryTree binarySearch index->" + index);
//        binaryTree.preOderRecur(binaryTree.root);
//
//        binaryTree.print(binaryTree.findMin());
//        binaryTree.print(binaryTree.findMax());
//
//        char[] chars = new char[3];
//        chars[0] = 'a';
//        chars[1] = 'b';
//        chars[2] = 'c';
//
//        StringUtil.printAllCharWords(chars, 0);

//        NumberUtil numberUtil = new NumberUtil();
        CollectionUtil collectionUtil = new CollectionUtil();
//        collectionUtil.fastSort(new int[]{8,9,6,1,4,5,10,8});
//        int halfCountNumber = collectionUtil.checkHalfCountNumber(ints);
//        Log.i(TAG, "halfCountNumber->" + halfCountNumber);
//        numberUtil.minPathSum(new int[][]{{1,3,1}, {1,5,1}, {4,2,1}});
//        numberUtil.arrangeArrayToMiniNumber(new int[]{21,32,12,6,1});

//        NumberUtil numberUtil = new NumberUtil();
//        LinkedList<int[][]> ints = numberUtil.queueArrange(4);
//        numberUtil.findAllMethods(new int[]{1, 1, 1}, 3);

        LinkListUtil linkListUtil = new LinkListUtil();
        LinkNode<Integer> head = new LinkNode<>(1);
        LinkNode<Integer> cur = head;
        for (int i = 2; i < 5; i++) {
            LinkNode<Integer> node = new LinkNode<>(i);
            cur.pNext = node;
            cur = node;
        }
//        cur=head;
//        Random random = new Random();
//        while (cur!=null){
//            cur.pSibling=list.get(random.nextInt(list.size()));
//            cur=cur.pNext;
//        }
//        linkListUtil.copyComplexLinkList02(head);
//        StringUtil stringUtil = new StringUtil();
//        stringUtil.printStringCombinations("abc");

//        NumberUtil numberUtil = new NumberUtil();
//        numberUtil.threeNumSum(new int[]{0,0,0,0});
//        linkListUtil.printListFromEnd2Start(head);
//        collectionUtil.combine(4,3);

//        BacktrackingDemo backtrackingDemo = new BacktrackingDemo();
//        backtrackingDemo.combinationSum(new int[]{1,3,5},6);
//        backtrackingDemo.solveNQueens(5);


        //=================================树相关=================================//
        TreeDemo treeDemo = new TreeDemo();
//        binaryTree1.generateTree(new int[]{5,4,2,8,6});
//        binaryTree2.generateTree(new int[]{1,0,3});
//        BinaryTree<Integer> binaryTree = new BinaryTree<>(new int[]{5, 4, 8, 11, 13, 4, 7, 2, 5, 1});
//        treeDemo.pathSum(binaryTree.root, 22);
//        treeDemo.isValidBST(binaryTree1.root);

        //快速排序
//        int[] arr = {10, 80, 30, 90, 40, 50, 70};
//        QuickSort.quickSort(arr);
//        CollectionUtil.printArr(arr);

        //归并排序
        int[] arr2 = {38, 27, 43, 3, 9, 82,10};
        MergeSort.mergeSort(arr2);
        CollectionUtil.printArr(arr2);
    }


    private Node<Integer> generateLinkList() {
        Node<Integer> root = new Node<Integer>(1);
        Node<Integer> cur = root;
        for (int i = 3; i < 8; i += 2) {
            cur.pNext = new Node(i);
            cur = cur.pNext;
        }
        return root;
    }

    private Node<Integer> generateLinkList2() {
        Node<Integer> root = new Node<Integer>(2);
        Node<Integer> cur = root;
        for (int i = 4; i < 9; i += 2) {
            cur.pNext = new Node(i);
            cur = cur.pNext;
        }
        return root;
    }

    //反转链表
    private Node reverseLinkList() {
        Node head;
        Node a = new Node("a");
        Node b = new Node("b");
        a.pNext = b;
        Node c = new Node("c");
        b.pNext = c;
        Node d = new Node("d");
        c.pNext = d;
        head = a;

        Node pReversedHead = null;
        Node pNode = head;
        Node pPre = null;

        while (pNode != null) {
            Node pNext = pNode.pNext;
            if (pNext == null) {
                //到了尾结点,这个尾结点就是反转后的head
                pReversedHead = pNode;
            }
            //执行反转
            pNode.pNext = pPre;
            pPre = pNode;
            pNode = pNext;
        }
        return pReversedHead;
    }

    class Node<T> {
        public Node(T data) {
            this.data = data;
        }

        T data;
        Node pNext;
    }


    /**
     * a，b都为有序列表
     * 合并两个有序的链表，生成一个有序的
     */

    private Node<Integer> mergeTwoList(Node<Integer> a, Node<Integer> b) {
        Node head;
        if (a == null) {
            head = b;
        } else if (b == null) {
            head = a;
        } else {
            if (a.data < b.data) {
                head = a;
                head.pNext = mergeTwoList(a.pNext, b);
            } else {
                head = b;
                head.pNext = mergeTwoList(a, b.pNext);
            }
        }
        return head;

    }

    //遍历树
    private TreeNode generateTree2() {
        //另一个树
        TreeNode<Integer> roo2 = new TreeNode<>(8);
        //第二层
        TreeNode<Integer> left3 = new TreeNode<>(9);
        TreeNode<Integer> right3 = new TreeNode<>(2);
        return roo2;
    }

    private TreeNode generateTree1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        //第二层
        TreeNode<Integer> left = new TreeNode<>(8);
        TreeNode<Integer> right = new TreeNode<>(7);
        root.left = left;
        root.right = right;
        //第三层
        TreeNode<Integer> left1 = new TreeNode<>(9);
        TreeNode<Integer> right1 = new TreeNode<>(2);
        left.left = left1;
        left.right = right1;
        //第四层
        TreeNode<Integer> left2 = new TreeNode<>(4);
        TreeNode<Integer> right2 = new TreeNode<>(7);
        return root;
    }

    /**
     * @param root1
     * @param root2
     * @return 递归需要终止条件
     */
    private boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.data == root2.data) {
                result = doesTree1HasTree2(root1, root2);
            }
            if (!result) {
                result = hasSubtree(root1.left, root2);
            }

            if (!result) {
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    //tree2是否为tree1的子树
    private boolean doesTree1HasTree2(TreeNode tree1, TreeNode tree2) {
        if (tree2 == null) {
            return true;
        }
        if (tree1 == null) {
            return false;
        }

        if (tree1.data != tree2.data) {
            return false;
        }

        return doesTree1HasTree2(tree1.left, tree2.left) && doesTree1HasTree2(tree2.right, tree2.right);
    }

    //输出二叉树的镜像
    private void mirrorTreeNode(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) return;
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        if (node.left != null) {
            mirrorTreeNode(node.left);
        }
        if (node.right != null) {
            mirrorTreeNode(node.right);
        }
    }

}
