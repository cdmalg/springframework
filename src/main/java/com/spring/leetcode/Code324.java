package com.spring.leetcode;

/**
 * 按摩师
 * <p>
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 */
public class Code324 {

    public static int massage(int[] nums) {

        int[] record = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int m = calculate(nums, record, i);
            max = Math.max(max, m);
        }
        return max;
    }

    public static int calculate(int[] nums, int[] record, int index) {
        if (record[index] > 0) {
            return record[index];
        }
        if (index + 2 >= nums.length) {
            record[index] = nums[index];
            return nums[index];
        }
        int max = 0;
        for (int i = index + 2; i < nums.length; i++) {
            int n = calculate(nums, record, i);
            max = Math.max(max, n + nums[index]);
        }
        record[index] = max;
        return max;
    }

    public static void main(String[] args) {
        int[] a = {2,1,4,5,3,1,1,3};
        System.out.println(massage(a));
    }
}
