package com.spring.leetcode;

import java.util.*;

/**
 * 水壶问题
 * <p>
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的 一或两个 来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * 1.装满任意一个水壶
 * 2.清空任意一个水壶
 * 3.从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 示例：
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 */
public class Code321 {

    // 解题思路：
    // 数学方法，利用贝祖定理（裴蜀定理）
    // 我们认为，每次操作只会让桶里的水总量增加 x，增加 y，减少 x，或者减少 y。
    // 你可能认为这有问题：如果往一个不满的桶里放水，或者把它排空呢？那变化量不就不是 x 或者 y 了吗？接下来我们来解释这一点：
    // 首先要清楚，在题目所给的操作下，两个桶不可能同时有水且不满。因为观察所有题目中的操作，操作的结果都至少有一个桶是空的或者满的；
    // 其次，对一个不满的桶加水是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于直接从初始状态给这个桶加满水；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态分别给两个桶加满；
    // 再次，把一个不满的桶里面的水倒掉是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于回到初始状态；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态直接给另一个桶倒满。
    // 因此，我们可以认为每次操作只会给水的总量带来 x 或者 y 的变化量。因此我们的目标可以改写成：找到一对整数 a, b，使得ax+by=z
    // 而只要满足 z≤x+y，且这样的 a, b存在，那么我们的目标就是可以达成的。这是因为：
    // 若 a≥0,b≥0，那么显然可以达成目标。
    // 若 a<0，那么可以进行以下操作：
    // 往 y 壶倒水；
    // 把 y 壶的水倒入 x 壶；
    // 如果 y 壶不为空，那么 x 壶肯定是满的，把 x 壶倒空，然后再把 y 壶的水倒入 x 壶。
    // 重复以上操作直至某一步时 x 壶进行了 aa 次倒空操作，y 壶进行了 bb 次倒水操作。
    // 若 b<0，方法同上，x 与 y 互换。
    // 而贝祖定理告诉我们，ax+by=z 有解当且仅当 z 是 x, y的最大公约数的倍数。因此我们只需要找到 x, y 的最大公约数并判断 z 是否是它的倍数即可。
    public boolean canMeasureWater(int x, int y, int z) {

        if (x + y < z) {
            return false;
        }

        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }

        int gcb = 1;

        for (int i = Math.min(x, y); i > 1; i--) {
            if (x % i == 0 && y % i == 0) {
                gcb = i;
            }
        }

        if (z % gcb == 0) {
            return true;
        }
        return false;

    }

    // -------------------------------------------------------------------------
    // 解题思路：JAVA算法
    // 题解地址：
    // 大致意思是利用广度优先遍历，记录每个状态，迭代查询。
    // 具体题解 ： https://leetcode-cn.com/problems/water-and-jug-problem/solution/tu-de-yan-du-you-xian-bian-li-by-liweiwei1419/
    public boolean canMeasureWaterPro(int x, int y, int z) {
        // 特判
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }

        State initState = new State(0, 0);

        // 广度优先遍历使用队列
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        queue.offer(initState);
        visited.add(initState);

        while (!queue.isEmpty()) {
            State head = queue.poll();

            int curX = head.getX();
            int curY = head.getY();

            // curX + curY == z 比较容易忽略
            if (curX == z || curY == z || curX + curY == z) {
                return true;
            }

            // 从当前状态获得所有的可能的下一步的状态
            List<State> nextStates = getNextStates(curX, curY, x, y);
            // 调试代码
            // System.out.println(head + " => " + nextStates);
            for (State nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    // 添加到队列的时候，一定要马上加到哈希表里面，否则队列里会添加重复的结点
                    // 由于添加到队列里的结点一定会被访问到，马上加到哈希表里面是合理的
                    queue.offer(nextState);
                    visited.add(nextState);
                }
            }
        }
        return false;
    }

    private List<State> getNextStates(int curX, int curY, int x, int y) {
        List<State> nextStates = new ArrayList<>(8);

        // 以下两个状态，对应操作 1

        // 外部加水，使得 A 满
        State nextState1 = new State(x, curY);
        // 外部加水，使得 B 满
        State nextState2 = new State(curX, y);

        // 以下两个状态，对应操作 2

        // 把 A 清空
        State nextState3 = new State(0, curY);
        // 把 B 清空
        State nextState4 = new State(curX, 0);

        // 以下四个状态，对应操作 3

        // 从 A 到 B，使得 B 满，A 还有剩
        State nextState5 = new State(curX - (y - curY), y);
        // 从 A 到 B，此时 A 的水太少，A 倒尽，B 没有满
        State nextState6 = new State(0, curX);

        // 从 B 到 A，使得 A 满，B 还有剩余
        State nextState7 = new State(x, curY - (x - curX));
        // 从 B 到 A，此时 B 的水太少，B 倒尽，A 没有满
        State nextState8 = new State(curY, 0);

        // 以下判断是一些简单的剪枝操作，不难，但是有点烦

        if (curX < x) {
            nextStates.add(nextState1);
        }

        if (curY < y) {
            nextStates.add(nextState2);
        }

        nextStates.add(nextState3);
        nextStates.add(nextState4);

        if (curX - (y - curY) > 0) {
            nextStates.add(nextState5);
        }

        if (curY - (x - curX) > 0) {
            nextStates.add(nextState7);
        }

        if (curY == 0 && y > curX) {
            nextStates.add(nextState6);
        }

        if (curX == 0 && x > curY) {
            nextStates.add(nextState8);
        }
        return nextStates;
    }

    private class State {
        private int x;
        private int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "State{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
