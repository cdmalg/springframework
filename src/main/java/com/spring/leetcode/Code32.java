package com.spring.leetcode;


import com.spring.leetcode.util.ListNode;

/**
 * 反转一个单链表。
 *
 * 输入: 1->2->3->4->5->NULL
 *
 * 输出: 5->4->3->2->1->NULL
 */
public class Code32 {

  //最初想法，算是迭代思路的一种，但用了递归实现，效率较低
  public static ListNode reverseList(ListNode head, ListNode lastNode) {

    ListNode nextNode = head.next;
    head.next = lastNode;
    if (nextNode != null) {
      return reverseList(nextNode, head);
    } else {
      return head;
    }

  }

  //参考答案，递归实现
  //1->2->3->4->5
  //递归从最后一个开始往前数，5的指针为空，则直接返回
  //节点4中需要将节点5的指针指向节点4，所以用到head.next.next = head
  //并将本节点的指针清空作为最末节点处理并返回给节点3方法
  //再节点3中继续执行以上两行步骤，以此类推到节点1
  //反转单项链表完成
  public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode node = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return node;
  }

  //迭代方式实现
  public ListNode reverseList1(ListNode head){
    ListNode preNode = null;
    ListNode curNode = head;
    while (curNode != null){
      ListNode nextTemp = curNode.next;
      curNode.next = preNode;
      preNode = curNode;
      curNode = nextTemp;
    }
    return preNode;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    ListNode node = reverseList(node1, null);

    while (node.next != null) {
      System.out.println(node.val);
      node = node.next;
    }
  }
}

