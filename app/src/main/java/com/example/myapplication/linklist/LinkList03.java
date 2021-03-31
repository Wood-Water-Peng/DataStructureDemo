package com.example.myapplication.linklist;

import com.example.myapplication.LinkNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jacky.peng
 * @Date 2021/3/10 4:45 PM
 * @Version 1.0
 */
public class LinkList03 {
    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * @param head
     * @return 思路一：
     * 使用辅助空间
     * <p>
     * 思路二：
     * 不使用辅助空间，双重循环
     */
    public LinkNode<Integer> removeDuplicateNodes(LinkNode<Integer> head) {
        List<Integer> buff = new ArrayList<>();
        LinkNode<Integer> cur = head;
        if (head == null) return null;
        buff.add(cur.data);
        while (cur != null) {
            //当前节点的下一个节点有效，继续移动
            if (cur.pNext != null) {
                if (buff.contains(cur.pNext.data)) {
                    cur.pNext = cur.pNext.pNext;
                } else {
                    buff.add(cur.pNext.data);
                    cur = cur.pNext;
                }
            } else {
                break;
            }
        }
        return head;
    }

    /**
     * 使用双重循环
     * 输入：[1, 2, 3, 3, 2, 1]
     * 输出：[1, 2, 3]
     *
     * @param head
     * @return
     */
    public LinkNode<Integer> removeDuplicateNodes2(LinkNode<Integer> head) {
        LinkNode<Integer> cur = head;
        if (head == null) return null;
        while (cur != null) {
            //移除所有和当前节点值相同的节点
            LinkNode<Integer> copy = cur;
            while (copy.pNext != null) {
                if (copy.pNext.data == cur.data) {
                    cur.pNext = copy.pNext.pNext;
                } else {
                    copy = copy.pNext;
                }
            }
            cur = cur.pNext;

        }
        return head;
    }

    /**
     * 返回倒数第K个节点
     *
     * @param head
     * @param k
     * @return 思路：
     * <p>
     * 双指针，让其中一个指针先走k步，然后两个指针同时走，直到先走的指针走到尾部
     */
    public int kthToLast(LinkNode<Integer> head, int k) {
        LinkNode<Integer> tail = head;

        for (int i = 0; i < k; i++) {
            tail = tail.pNext;
        }
        while (tail != null) {
            tail = tail.pNext;
            head = head.pNext;
        }
        return head.data;
    }

    /**
     * 判断链表是否相交
     *
     * @param headA
     * @param headB
     * @return 相交表示节点的引用相同，而非节点值
     * <p>
     * 思路:先计算出两个链表的长度
     */
    public LinkNode<Integer> getIntersectionNode(LinkNode<Integer> headA, LinkNode<Integer> headB) {
        LinkNode<Integer> curA = headA;
        int lenA = 0;
        while (curA != null) {
            lenA++;
            curA = curA.pNext;
        }
        curA = headA;
        LinkNode<Integer> curB = headB;
        int lenB = 0;
        while (curB != null) {
            lenB++;
            curB = curB.pNext;
        }
        curB = headB;

        //让长的链表先走
        int step = lenA - lenB;
        if (step > 0) {
            for (int i = 0; i < step; i++) {
                curA = curA.pNext;
            }
        } else {
            step = -step;
            for (int i = 0; i < step; i++) {
                curB = curB.pNext;
            }
        }

        LinkNode<Integer> result = null;
        //同时走，找到相同的节点
        while (curA != null) {
            if (curA == curB) {
                //找到了一个相同的
                if (result == null) {
                    result = curA;
                }
            } else {
                result = null;
            }
            curA = curA.pNext;
            curB = curB.pNext;
        }
        return result;
    }

    /**
     * (7 -> 1 -> 6)
     * (5 -> 9)
     * (2->1->7)
     * <p>
     * 617+95=712
     *
     * @param l1
     * @param l2
     * @return
     */
    public LinkNode<Integer> addTwoNumbers(LinkNode<Integer> l1, LinkNode<Integer> l2) {
        LinkNode<Integer> cur1 = l1;
        LinkNode<Integer> cur2 = l2;
        LinkNode<Integer> head = null;
        LinkNode<Integer> cur = null;
        int carry = 0;
        int value = 0;
        while (cur1 != null || cur2 != null || carry > 0) {
            int total = 0;
            if (cur1 != null && cur2 != null) {
                total = carry + (cur1.data + cur2.data);
                cur1 = cur1.pNext;
                cur2 = cur2.pNext;
            } else if (cur1 != null) {
                total = carry + cur1.data;
                cur1 = cur1.pNext;
            } else if (cur2 != null) {
                total = carry + cur2.data;
                cur2 = cur2.pNext;
            }
            carry = total / 10;
            value = total % 10;
            if (head == null) {
                head = new LinkNode<>(value);
                cur = head;
            } else {
                cur.pNext = new LinkNode<>(value + carry);
                cur = cur.pNext;
            }
        }
        return head;
    }

    /**
     * 回文链表
     *
     * @param head
     * @return 思路一：将链表翻转，然后逐一比较，完全相同就是回文链表
     * <p>
     * 思路二：使用递归，将单链表变成双链表
     */
    public boolean isPalindrome(LinkNode<Integer> head) {
        if (head == null) return true;
        if (head.pNext == null) return true;
        LinkNode<Integer> cur = head;
        _reverseList(cur);

        while (head != null) {
            if (head.data != reversedHead.data) {
                return false;
            }
            head = head.pNext;
            reversedHead = reversedHead.pNext;
        }
        return true;
    }

    LinkNode<Integer> reversedHead;
    LinkNode<Integer> cur;

    public void _reverseList(LinkNode<Integer> head) {
        if (head == null) return;

        _reverseList(head.pNext);
        if (head.pNext == null) {
            cur = new LinkNode<>(head.data);
            reversedHead = cur;
        } else {
            cur.pNext = new LinkNode(head.data);
            cur = cur.pNext;
        }

    }

    /**
     * 检测环路
     *
     * @param head
     * @return 思路一：
     * 哈希表  将走过的节点记录下来    空间辅助度0(n)
     */
    public LinkNode<Integer> detectCycle(LinkNode<Integer> head) {

        LinkNode<Integer> one = head;
        LinkNode<Integer> pos;
        LinkNode<Integer> second = head;
        return null;

    }

}
