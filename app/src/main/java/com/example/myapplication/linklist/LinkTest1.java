package com.example.myapplication.linklist;

import com.example.myapplication.LinkNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkTest1 {
    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     * 合并两个有序链表
     */
//    public LinkNode<Integer> mergeSortedList(LinkNode<Integer> a, LinkNode<Integer> b) {
//        if (a == null) return b;
//        if (b == null) return a;
//        LinkNode<Integer> curA = a;
//        LinkNode<Integer> curB = b;
//        LinkNode<Integer> rootNode = null;
//        LinkNode<Integer> newNode = null;
//        while (curA != null && curB != null) {
//            if (curA.data < curB.data) {
//                if (newNode == null) {
//                    newNode = curA;
//                    rootNode = curA;
//                } else {
//                    newNode.pNext = curA;
//                    newNode = newNode.pNext;
//                }
//                curA = curA.pNext;
//            } else {
//                if (newNode == null) {
//                    newNode = new LinkNode<>(curB.data);
//                    rootNode = newNode;
//                } else {
//                    newNode.pNext = new LinkNode<>(curB.data);
//                    newNode = newNode.pNext;
//                }
//                curB = curB.pNext;
//            }
//        }
//        if (curA == null) {
//            //将B中剩余的接上去
//            while (curB != null) {
//                newNode.pNext = new LinkNode<>(curB.data);
//                newNode = newNode.pNext;
//                curB = curB.pNext;
//            }
//        } else if (curB == null) {
//            while (curA != null) {
//                newNode.pNext = new LinkNode<>(curA.data);
//                newNode = newNode.pNext;
//                curA = curA.pNext;
//            }
//        }
//        return rootNode;
//    }

    LinkNode<Integer> root;

    /**
     * 采用递归的方式
     * 可以视作更改两个链表中元素的指针
     *
     * @param a
     * @param b
     * @return
     */
    public LinkNode<Integer> mergeSortedList2(LinkNode<Integer> a, LinkNode<Integer> b) {
        if (a == null) return b;
        if (b == null) return a;
        LinkNode<Integer> mergedHead;
        if (a.data < b.data) {
            mergedHead = a;
            mergedHead.pNext = mergeSortedList2(a.pNext, b);
        } else {
            mergedHead = b;
            mergedHead.pNext = mergeSortedList2(a, b.pNext);
        }
        return mergedHead;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * 删除排序链表中的重复元素
     *
     * @param head
     * @return
     */
    public LinkNode<Integer> deleteDuplicates(LinkNode<Integer> head) {
        LinkNode<Integer> current = head;
        while (current != null) {
            if (current.pNext == null) break;
            if (current.data.equals(current.pNext.data)) {
                current.pNext = current.pNext.pNext;
            } else {
                current = current.pNext;
            }
        }
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle/
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(LinkNode<Integer> head) {
        HashMap<LinkNode<Integer>, Boolean> map = new HashMap<>();
        boolean flag = false;
        while (head != null) {
            Boolean aBoolean = map.get(head);
            if (aBoolean != null) {
                flag = true;
                break;
            } else {
                map.put(head, true);
            }
            head = head.pNext;
        }
        return flag;
    }

    public LinkNode<Integer> hasCycle2(LinkNode<Integer> head) {
        HashMap<LinkNode<Integer>, Boolean> map = new HashMap<>();
        LinkNode<Integer> flag = null;
        while (head != null) {
            Boolean aBoolean = map.get(head);
            if (aBoolean != null) {
                flag = head;
                break;
            } else {
                map.put(head, true);
            }
            head = head.pNext;
        }
        return flag;
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * 相交链表
     *
     * @param headA
     * @param headB
     * @return 方法一：
     * 对于A中的每个元素，对B进行遍历  时间复杂度O(m*n) 空间O(1)
     * <p>
     * 方法二：
     * 遍历A，将元素保存在哈希表中，遍历B，去A的哈希表中寻找对应元素时间复杂度O(m+n) 空间O(m)
     * <p>
     * 方法三：
     * 找到长的那个链表，让他先走(m-n)步，然后再一起向前走，直到找到相同的节点
     */
    public LinkNode<Integer> getIntersectionNode(LinkNode<Integer> headA, LinkNode<Integer> headB) {
        LinkNode<Integer> curA = headA;
        LinkNode<Integer> curB = headB;
        LinkNode<Integer> intersectionNode = null;
        while (curA != null || curB != null) {
            if (curA.data.equals(curB.data)) {
                if (curA == headA && curB == headB) {
                    continue;
                } else {
                    //找到了相交节点
                    intersectionNode = curA;
                    break;
                }
            }
            if (curA.pNext != null) {
                curA = curA.pNext;
            }
            if (curB.pNext != null) {
                curB = curB.pNext;
            }
        }
        return intersectionNode;
    }

    /**
     * 反转链表
     * 1.是否改变原来链表的结构
     * 2.使用递归和迭代两种方式
     *
     * @param head
     * @return 反转后链表的head
     * <p>
     * 迭代方式-改变原来的链表
     */
    public LinkNode<Integer> reverseList1(LinkNode<Integer> head) {
        LinkNode<Integer> cur = head;
        LinkNode<Integer> newHead = null;
        LinkNode<Integer> pre = null;
        while (cur != null) {
            LinkNode<Integer> tmp = cur.pNext;
            cur.pNext = pre;
            pre = cur;
            //指向下一个
            newHead = cur;
            cur = tmp;
        }
        return newHead;
    }

    /**
     * 递归方式
     *
     * @param head
     * @return
     */

    LinkNode<Integer> newHead;

    public LinkNode<Integer> reverseList2(LinkNode<Integer> head) {
        _reverseList2(head);
        if (head == null) return null;
        head.pNext = null;
        return newHead;
    }

    public LinkNode<Integer> _reverseList2(LinkNode<Integer> head) {
        if (head == null) return null;
        LinkNode<Integer> result = _reverseList2(head.pNext);
        if (result == null) {
            newHead = head;
            return head;
        }
        result.pNext = head;
        return head;
    }

    /**
     * 删除链表中所有值为指定值的元素
     *
     * @param head
     * @param val
     * @return 递归方式解决
     */
    public LinkNode<Integer> removeElements(LinkNode<Integer> head, int val) {
        return _removeElements(head, val);
    }

    public LinkNode<Integer> _removeElements(LinkNode<Integer> head, int val) {
        if (head == null) return null;
        //在当前节点移除元素后的下一个节点
        LinkNode<Integer> result = _removeElements(head.pNext, val);
        head.pNext = result;
        if (head.data == val) {
            return head.pNext;
        } else {
            return head;
        }
    }

    /**
     * 删除链表中的指定节点
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     *
     * @param node 很取巧的一个题目，一般的删除节点操作需要知道被删除节点的pre，但是这里不能访问
     *             1.node不是尾结点
     *             2.node是链表中的有效节点
     */
    public void deleteNode(LinkNode<Integer> node) {
        node.data = node.pNext.data;
        node.pNext = node.pNext.pNext;
    }

    /**
     * 回文列表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     *
     * @param head
     * @return 1->2->2->1
     * 思路:
     * 1.将链表的值复制到数组中
     * 2.遍历链表，向数组中添加链表的值，并且比较(双指针)
     */
    public boolean isPalindrome(LinkNode<Integer> head) {
        LinkNode<Integer> cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.data);
            cur = cur.pNext;
        }
        int index = list.size() - 1;
        cur = head;
        while (cur != null) {
            if (cur.data.equals(list.get(index))) {
                index--;
                cur = cur.pNext;
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 给链表排序
     * https://leetcode-cn.com/problems/sort-list/
     *
     * @param head
     * @return 简单思路:
     * 1.将链表的值复制到数组  空间 O(n)
     * 2.对数组排序    O(nLogn)
     * <p>
     * 进阶思路:
     * 1.使用归并排序  nlogn
     * 2.自顶向下，空间O(logn)
     * <p>
     * 整体思路：
     * 先拆，拆到不能拆分之后(递归的终止条件),然后执行逻辑操作，比如排序，接着合并
     */
    public LinkNode<Integer> sortList(LinkNode<Integer> head) {
        //1.找到链表中心点，将其拆分

        LinkNode<Integer> sortedHead = _sortList(head, null);

        return sortedHead;
    }

    public LinkNode<Integer> _sortList(LinkNode<Integer> head, LinkNode<Integer> tail) {
        //递归的终止条件
        if (head == null || head.pNext == null) {
            //此时只有一个head节点
            return head;
        }
        LinkNode<Integer> centerNode = findCenterNode(head, tail);
        LinkNode<Integer> pNext = centerNode.pNext;
        centerNode.pNext=null;
        LinkNode<Integer> a = _sortList(head, centerNode);
        LinkNode<Integer> b = _sortList(pNext, tail);
        return mergeSortedList(a, b);
    }


    //找到链表中心点
    public LinkNode<Integer> findCenterNode(LinkNode<Integer> head, LinkNode<Integer> tail) {
        //使用快慢指针 快指针走到尾结点后，慢指针的位置就是中心节点
        LinkNode<Integer> slow = head;
        if (head.pNext == null) {
            return slow;
        }
        LinkNode<Integer> fast = head;
        while (fast != null) {
            if (fast == tail) {
                return slow;
            }
            if (fast.pNext == null) {
                //走到尾结点
                return slow;
            }
            if (fast.pNext == tail) {
                //走到尾结点
                return slow;
            }
            slow = slow.pNext;
            fast = fast.pNext.pNext;
        }
        return slow;
    }

    // 合并两个有序链表
    // 返回合并后的链表头
    private LinkNode<Integer> mergeSortedList(LinkNode<Integer> a, LinkNode<Integer> b) {
        return mergeSortedList2(a, b);
    }
}