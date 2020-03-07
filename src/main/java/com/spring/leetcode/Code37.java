package com.spring.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值
 * <p>
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class Code37 {

    public int a = 1;


    // 解题思路：利用List链表，简单粗暴，但效率低
    class MaxQueue {
        private LinkedList<Integer> q;

        public MaxQueue() {
            q = new LinkedList<Integer>();
        }

        public int max_value() {
            if (q.size() > 0) {
                List<Integer> copy = new ArrayList<>(q);
                copy.sort((o1, o2) -> {
                    if (o1 <= o2) {
                        return 1;
                    }
                    return -1;
                });
                return copy.get(0);
            }
            return -1;
        }

        public void push_back(int value) {
            q.addFirst(value);
        }

        public int pop_front() {
            return q.size() == 0 ? -1 : q.poll();
        }
    }


    // leetcode上效率最高的代码，解题思路就是对象之间的引用
    // 有几个点需要注意：
    // 1.该思路创建了一个内部类Node，参数中包含对下一个节点的引用
    // 2.在MaxQueuePro初始化的过程中，创建了root节点，并将max和tail两个对象的引用同时指向了root
    // 3.在push_back过程中，代码tail.next = tmp 将tail的子节点指向了新建的临时对象tmp，又因为max和tail指向的内存地址为root对象，所以实际改变了root.next的值为新建的Node对象
    // 4.紧接着tail = tmp 是将tail的内存地址指向了临时创建的Node对象，这样，既保存了队列的顺序在root节点中，有将尾部节点保存了下来
    // 5.然后判断是否是最大值，是的话替换max节点的引用
    // 6.理解了push_back方法，pop_front就好理解了，就是如果取出的节点为max，则递归查询剩下节点中的最大值，并更新max节点
    class MaxQueuePro {

        private Node max;
        private Node root;
        private Node tail;

        class Node {
            int val;
            Node next = null;

            public Node(int val) {
                this.val = val;
            }
        }

        public MaxQueuePro() {
            this.root = new Node(-1);
            root.next = null;
            this.max = root;
            this.tail = root;
        }

        public int max_value() {
            if (root == tail) {
                return -1;
            }
            return max.val;
        }

        public void push_back(int value) {
            Node tmp = new Node(value);
            tail.next = tmp;
            tail = tmp;

            if (max == root || max.val <= value) max = tmp;
        }

        public int pop_front() {
            if (tail == root) return -1;

            root = root.next;//root并不代表实际节点
            if (root == max) {//最大值要出去了
                Node head = root.next;//head-tail才代表实际的队列
                max = head;
                while (head != null) {
                    max = max.val <= head.val ? head : max;
                    head = head.next;
                }
            }
            max = (max == null) ? root : max;

            return root.val;
        }
    }


    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<Integer>();
//        list.addFirst(1);
//        list.addFirst(2);
//        list.addFirst(3);
//        list.addFirst(4);
//        list.addFirst(5);
//
//        System.out.println(JSON.toJSONString(list));

//        Code37.MaxQueuePro code = new Code37().new MaxQueuePro();
//        code.push_back(1);
//        code.push_back(3);
//        code.push_back(2);
//        code.push_back(5);
//
//        int param_1 = code.max_value();
//        System.out.println("param_1==" + param_1);
//        System.out.println(code.pop_front());
//        System.out.println(code.pop_front());
//        System.out.println(code.pop_front());
//        System.out.println(code.pop_front());

        Code37 c = new Code37();
        c.a = 1;
        Code37 c1 = c;

        System.out.println(c.a);
        c1.a += 1;
        System.out.println(JSON.toJSONString(c));

    }

}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
