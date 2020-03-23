package com.spring.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 使数组唯一的最小增量
 * <p>
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 */
public class Code322 {

    // 因时间超时未通过，但思路应该正确
    // 解题思路：筛选出未重复的和重复的，然后再重复的中遍历加1
    public static int minIncrementForUnique(int[] A) {
        List<Integer> list = new ArrayList<>();
        LinkedList<Integer> repeatList = new LinkedList<>();
        for (Integer i : A) {
            if (!list.contains(i)) {
                list.add(i);
            } else {
                repeatList.add(i);
            }
        }
        int result = 0;
        while (repeatList.size() > 0) {
            Integer repeat = repeatList.poll();
            while (list.contains(repeat)) {
                repeat++;
                result++;
            }
            list.add(repeat);
        }
        return result;
    }


    // 解题思路：先排序，然后对前后两个值进行比较，如果后一个小与等于前一个，则增量为差值+1
    public static int minIncrementForUnique1(int[] A) {
        Arrays.sort(A);
        int result = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i + 1] <= A[i]) {
                int sub = A[i] - A[i + 1] + 1;
                A[i + 1] += sub;
                result+=sub;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 2, 1, 7};
        System.out.println(minIncrementForUnique1(a));
    }
}
