package com.spring.leetcode;

import java.util.Arrays;

/**
 * 卡牌分组
 * <p>
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * <p>
 * 示例 2：
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * <p>
 * 示例 3：
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 */
public class Code327 {

    // 解题思路：
    // 最大公约数，先统计各数字出现次数，对大于零的数进行最大公约数计算，如果大于等于2，则返回true，反之返回false
    public boolean hasGroupsSizeX(int[] deck) {

        int[] a = new int[10000];
        for (int i : deck) {
            a[i]++;
        }
        int n = -1;
        for (int i : a) {
            if (i > 0) {
                if (n == -1) {
                    n = i;
                } else {
                    n = gcd(i, n);
                }

            }
        }

        return n >= 2;
    }

    // 最大公约数计算公式
    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
