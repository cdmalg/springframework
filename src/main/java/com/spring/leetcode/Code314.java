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
     *
     * @param nums   题干中的数组
     * @param k      当前元素下标
     * @param record 路径记录数组
     * @return
     */
    public static int calculate(int[] nums, int k, int[] record) {
        // 如果当前下标为数组最后一位，直接返回1
        if (k == nums.length - 1) {
            return 1;
        }
        // 如果路径数组中有该节点最大值，则直接返回下标值
        if (record[k] > 0) {
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


    public int lengthOfLISPro(int[] nums) {
        int len = 0;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] a = {10, 9, 2, 5, 3, 7, 21, 18};
//        System.out.println(Arrays.binarySearch(a,0,1,2));
        System.out.println(lengthOfLISMax(a));
    }


    // TODO:动态规划+二分法实现（效率最高，leetcode官方推荐题解）
    // 解题思路：
    // 我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们要保证在筛选最长上升子序列时最后加上的那个数尽可能的小。
    // 维护一个列表 tails，其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值。
    // 在遍历计算每个 tails[k]，不断更新长度为 [1,k] 的子序列尾部元素值，始终保持每个尾部元素值最小。
    // （设常量数字 N，和随机数字 x，我们可以容易推出：当 N 越小时，N<x 的几率越大。例如：N=0 肯定比 N=1000 更可能满足 N<x。）
    public static int lengthOfLISMax(int[] nums) {
        // tails[k] 的值代表 长度为 k+1 子序列 的尾部元素值。
        int[] tails = new int[nums.length];
        // 设 res 为 tails 当前长度，代表直到当前元素的最长上升子序列长度。
        int res = 0;
        // 遍历数组每个元素
        for (int num : nums) {
            // 设 j∈[0,res)，考虑每轮遍历 nums[k] 时，通过二分法遍历 [0,res) 列表区间，找出 nums[k] 的大小分界点，会出现两种情况：
            // 1.区间中存在 tails[i] > nums[k]： 将第一个满足 tails[i] > nums[k] 执行 tails[i]=nums[k] ；因为更小的 nums[k] 后更可能接一个比它大的数字（前面分析过）。
            // 2.区间中不存在 tails[i] > nums[k]： 意味着 nums[k] 可以接在前面所有长度的子序列之后，因此肯定是接到最长的后面（长度为 res ），新子序列长度为 res + 1。
            int i = 0, j = res;
            // 二分法实现
            while (i < j) {
                // 获取中间下标值
                int m = (i + j) / 2;
                // 如果该中间值比当前元素小，则将起始下标更新为下一位（右分），继续进行二分法查询出第一个比当前值小的元素下标
                // 如果该中间值比当前元素大，则将终点下标更新为中间值下标（左分），继续进行二分法查询第一个比当前值小的元素下标
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            // 经过上面的二分法查询，筛选出tails[]中比当前元素小的最大值下标
            tails[i] = num;
            // 如果res == j 则说明tails[]中不存在比当前元素小的，则最长上升子序列长度+1
            // （ps：要想走出while循环有两种情况：
            // 第一种是数组中存在比当前元素小的值，while循环中的else肯定会被触发或者比当前元素小的值刚好在数组尾部，导致i<j不成立，跳出循环；
            // 第二种是数组中不存在比当前元素小的值，while循环中的else不会被触发，i不断增加直到等于j后跳出循环，所以j不会变）
            if (res == j) {
                res++;
            }
        }
        return res;
    }


    // TODO：动态规划（效率较高，比我一开始想的要好很多，官网推荐题解之一）
    // 解题思路：遍历数组中每个元素，再从第一个元素开始查找以当前元素为终点的最长上升序列
    // dp[j] 代表 nums[0…j] 中以nums[j]结尾的最长上升子序列长度
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        // 从第二个元素开始遍历，比较下标从0-i之间的数是否存在比当前元素小的值，如果存在则选出最长的dp[j]，加1后为以当前元素为结尾的最长上升子序列长度。
        // 与maxans对比获得整个数组最长子序列长度最大值
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
