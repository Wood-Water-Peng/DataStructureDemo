package com.example.myapplication.linklist;

import com.example.myapplication.LinkNode;

public class LinkTest2 {
    /**
     * https://leetcode-cn.com/problems/add-two-numbers/
     * 两数相加
     *
     * @param l1 8->4->3   348
     * @param l2 5->6->2   265
     * @return 3->1->6   613
     * 注意进位问题
     * 思路：
     * 1.对长度较短的链表进行补零
     * 2.对齐后逐位相加
     */
    public LinkNode<Integer> addTwoNumbers(LinkNode<Integer> l1, LinkNode<Integer> l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        //找到短的链表
        int len1 = 0;
        LinkNode<Integer> tmp1 = l1;
        while (tmp1.pNext != null) {
            tmp1 = tmp1.pNext;
            len1++;
        }
        int len2 = 0;
        LinkNode<Integer> tmp2 = l2;
        while (tmp2.pNext != null) {
            tmp2 = tmp2.pNext;
            len2++;
        }

        if (len1 < len2) {
            for (int i = 0; i < (len2 - len1); i++) {
                LinkNode<Integer> node = new LinkNode<>(0);
                tmp1.pNext = node;
                tmp1 = tmp1.pNext;
            }
        } else {
            for (int i = 0; i < (len1 - len2); i++) {
                LinkNode<Integer> node = new LinkNode<>(0);
                tmp2.pNext = node;
                tmp2 = tmp2.pNext;
            }
        }

        //逐行相加
        LinkNode<Integer> lastNode = null;
        LinkNode<Integer> head = null;
        int carry = 0;
        while (l1!= null) {
            int sum = l1.data + l2.data + carry;
            carry = sum / 10;
            if (lastNode == null) {
                lastNode = new LinkNode<>(sum % 10);
                head = lastNode;
            } else {
                lastNode.pNext = new LinkNode<>(sum % 10);
                lastNode = lastNode.pNext;
            }
            l1 = l1.pNext;
            l2 = l2.pNext;
        }
        if (carry == 1) {
            lastNode.pNext = new LinkNode<>(1);
        }
        return head;
    }
}
