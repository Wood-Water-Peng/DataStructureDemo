package com.example.myapplication;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LinkListUtil {
    public static final String TAG = "LinkListUtil";

    /**
     * @param node
     * @param k
     * @return 链表中的倒数第k个节点
     * <p>
     * 思路一：遍历两遍链表，
     * 第一遍得出链表的长度
     * 第二遍遍历到list.size()-k个节点
     * <p>
     * 思路二：只遍历一次链表
     * <p>
     * p1和p2,想让p1向前走k步，然后p1和p2同时向前走，当p1到达尾结点时，p2正好处在倒数第k个节点的位置
     */
    public MainActivity.Node<Integer> kThNodeBackwards(MainActivity.Node<Integer> node, int k) {
        if (node == null) return null;
        //倒数第k个节点就是正数第list.size()-k个
        MainActivity.Node<Integer> p1 = node;
        MainActivity.Node<Integer> p2 = node;
        for (int i = 0; i < k; i++) {
            p1 = p1.pNext;
            if (p1 == null) {
                return null;
            }
        }
        while (p1.pNext != null) {
            p1 = p1.pNext;
            p2 = p2.pNext;
        }
        return p2;
    }

    /**
     * 反转链表
     *
     * @param root
     * @return
     */
    public MainActivity.Node<Integer> reverseLinkList(MainActivity.Node<Integer> root) {
        MainActivity.Node<Integer> pre = null;
        MainActivity.Node<Integer> cur = root;
        MainActivity.Node<Integer> reverseHead = null;

        //需要一个指针来保存旧的下一个节点
        while (cur != null) {
            MainActivity.Node<Integer> next = cur.pNext;
            if (next == null) {
                reverseHead = cur;
            }
            cur.pNext = pre;
            pre = cur;
            cur = next;
        }
        return reverseHead;
    }

    /**
     * 合并两个排序的链表
     *
     * @param root1
     * @param root2
     * @return
     */
    public MainActivity.Node<Integer> mergeSortedLinkList(MainActivity.Node<Integer> root1, MainActivity.Node<Integer> root2) {

        MainActivity.Node<Integer> mergeHead = null;

        if (root1 == null) return root2;
        if (root2 == null) return root1;

        if (root1.data < root2.data) {
            mergeHead = root1;
            mergeHead.pNext = mergeSortedLinkList(root1.pNext, root2);
        } else {
            mergeHead = root2;
            mergeHead.pNext = mergeSortedLinkList(root1, root2.pNext);
        }
        return mergeHead;
    }

    /**
     * 复制复杂链表
     *
     * @return 思路一：使用哈希表来保存pSibling指向的节点
     */
    public LinkNode<Integer> copyComplexLinkList(LinkNode<Integer> head) {
        if (head == null) return null;
        LinkNode<Integer> cur = head;
        Map<LinkNode<Integer>, LinkNode<Integer>> map = new HashMap<>();

        while (cur != null) {
            map.put(cur, new LinkNode<>(cur.data));
        }
        //为新创建的节点指针复制
        while (cur != null) {
            LinkNode<Integer> newNode = map.get(cur);
            newNode.pNext = map.get(cur.pNext);
            newNode.pSibling = map.get(cur.pSibling);
        }
        return map.get(head);
    }

    /**
     * 复制复杂链表
     *
     * @return 思路二：扩展原始列表
     */
    public void copyComplexLinkList02(LinkNode<Integer> head) {
        if (head == null) return;
        LinkNode<Integer> cur = head;
        while (cur != null) {
            LinkNode<Integer> newNode = new LinkNode<>(cur.data);
            newNode.pNext = cur.pNext;
            cur.pNext = newNode;
            cur = newNode.pNext;
        }
        cur = head;
        while (cur != null) {
            cur.pNext.pSibling = cur.pSibling.pNext;
            cur = cur.pNext.pNext;
        }

        //将复制的链表拆分出来
        cur = head;
        printLinkList(cur);
        //根据奇偶，将链表拆分
        int count = 0;
        LinkNode<Integer> node = cur.pNext;
        while (cur != null) {
            if (count % 2 != 0 && count > 1) {
                node.pNext = cur;
                node = cur;
            }
            cur = cur.pNext;
            count++;
        }
        printLinkList(head.pNext);
    }


    public void printLinkList(LinkNode<Integer> head) {
        if (head == null) return;
        StringBuilder builder = new StringBuilder();
        while (head != null) {
            builder.append(head.toString() + "-");
            head = head.pNext;
        }
        Log.i(TAG, "printLinkList->" + builder.toString());
    }

    /**
     * 从尾到头打印链表
     * <p>
     * 注意点：
     * 1.是否能改变原来的数据结构
     * <p>
     * 思路:
     * <p>
     * 1.遍历链表，将节点依次压入栈中
     * <p>
     * 2.使用递归
     */
    public void printListFromEnd2Start(LinkNode<Integer> head) {
        StringBuilder builder = new StringBuilder();
        visitNode(head, builder);
        Log.i(TAG, "printListFromEnd2Start->" + builder.toString());
    }

    private void visitNode(LinkNode<Integer> node, StringBuilder sb) {
        if (node==null) {
            return;
        }
        visitNode(node.pNext, sb);
        //归的过程
        sb.append(node.data);
    }


}
