package com.spring.leetcode;

import com.spring.leetcode.util.ListNode;

/**
 * 链表的中间节点
 * <p>
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 示例 ：
 * 输入：[1,2,3,4,5]
 * 输出：3 (序列化形式：[3,4,5])
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Code323 {

    // 解题思路：
    // 中间节点会根据链表长度逐渐右移，每经过两个节点，中间节点就会右移一位
    // 故初始化一个中间节点为头节点，顺序循环所有节点，每经过两个节点右移，最后就是中间节点
    public static ListNode middleNode(ListNode head) {

        int i = 0;
        ListNode middle = head;
        do {
            if (i % 2 != 0) {
                middle = middle.next;
            }
            head = head.next;
            i++;
        } while (head != null);
        return middle;
    }


    // 解题思路：
    // 链表的缺点在于不能通过下标访问对应的元素。因此我们可以考虑对链表进行遍历，同时将遍历到的元素依次放入数组 A 中。如果我们遍历到了 N 个元素，那么链表以及数组的长度也为 N，对应的中间节点即为 A[N/2]。
    public ListNode middleNode1(ListNode head) {
        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head != null) {
            A[t++] = head;
            head = head.next;
        }
        return A[t / 2];
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println(middleNode(node1).val);
    }

}

