package com.spring.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * <p>
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 本题有点费脑子，详细解题思路参照网址：https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
 */
public class Code38 {

    // 解题思路：递归从数组第一位开始，计算硬币可能出现次数区间，在此区间进行递归，依次遍历出各种情况，选出最小值并返回
    // for循环套递归，如果数组中数值较多，则运行起来相当耗时
    public static int coinChange(int[] coins, int amount) {
        return calculation(0, coins, amount);
    }

    public static int calculation(int index, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount > 0 && index < coins.length) {
            //最大使用次数
            int maxUsing = amount / coins[index];
            int minUsing = Integer.MAX_VALUE;
            for (int i = 0; i <= maxUsing; i++) {
                int res = calculation(index + 1, coins, amount - coins[index] * i);
                if (res != -1) {
                    minUsing = Math.min(minUsing, res + i);
                }
            }
            return minUsing == Integer.MAX_VALUE ? -1 : minUsing;
        }
        return -1;
    }

    // 动态规划：自上而下，耗时较短
    // 还是递归，但用数组记录了每次剩余amount所需的最少个数，减少了重复计算
    public static int coinChangePro(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private static int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        System.out.println(JSON.toJSONString(count));
        return count[rem - 1];
    }

    // 动态规划：自上而下
    public int coinChangeProMax(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        System.out.println(coinChangePro(coins, 3));

    }
}
