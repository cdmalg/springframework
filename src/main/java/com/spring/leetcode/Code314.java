package com.spring.leetcode;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * <p>
 * 输出: 4
 * <p>
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 */
public class Code314 {

    // 解题思路：循环每个元素，通过递归的方式算出每个元素组成最长上升子序列的值，对比选出最大值，注意要记录一下路径，否则会很耗时。

    /**
     * 该方法为递归方法，目的为了获取当前元素可生成的最大上升子序列的长度
     * @param nums      题干中的数组
     * @param k         当前元素下标
     * @param record    路径记录数组
     * @return
     */
    public static int calculate(int[] nums, int k, int[] record) {
        // 如果当前下标为数组最后一位，直接返回1
        if (k == nums.length - 1) {
            return 1;
        }
        // 如果路径数组中有该节点最大值，则直接返回下标值
        if(record[k] > 0){
            return record[k];
        }
        // j 用来记录下一个元组的下标
        int j = k + 1;
        // max 用于选取该元素下所有路径的最大值
        int max = 1;
        // 循环数组中该元素之后的下标，对每个下标所在元素进行递归计算
        while (j <= nums.length - 1) {
            // 如果下一个元素并不符合上升序列条件，直接跳过
            if (nums[k] < nums[j]) {
                int m = calculate(nums, j, record);
                // 比较获取上升序列路径长度最大值
                max = Math.max(max, m + 1);
            }
            j++;
        }
        // 记录该节点路径长度最大值
        record[k] = max;
        return max;
    }

    public static int lengthOfLIS(int[] nums) {

        int max = 0;
        int[] record = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, calculate(nums, i, record));
        }

        return max;

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        System.out.println(Arrays.binarySearch(a,0,1,2));
//        System.out.println(lengthOfLIS(a));
    }

    public int lengthOfLISPro(int[] nums) {
        int len = 0;
        int[] dp= new int[nums.length];
        for (int num:nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i<0) {
                i= -(i+1);
            }
            dp[i] = num;
            if (i==len) {
                len++;
            }
        }
        return len;
    }


    public int lengthOfLISMax(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
}
