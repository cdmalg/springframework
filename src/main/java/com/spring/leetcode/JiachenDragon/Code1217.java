package com.spring.leetcode.JiachenDragon;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串数组 words 和一个字符串 target。
 * <p>
 * 如果字符串 x 是 words 中 任意 字符串的
 * 前缀
 * ，则认为 x 是一个 有效 字符串。
 * <p>
 * 现计划通过 连接 有效字符串形成 target ，请你计算并返回需要连接的 最少 字符串数量。如果无法通过这种方式形成 target，则返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入： words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
 * <p>
 * 输出： 3
 * <p>
 * 解释：
 * <p>
 * target 字符串可以通过连接以下有效字符串形成：
 * <p>
 * words[1] 的长度为 2 的前缀，即 "aa"。
 * words[2] 的长度为 3 的前缀，即 "bcd"。
 * words[0] 的长度为 3 的前缀，即 "abc"。
 * 示例 2：
 * <p>
 * 输入： words = ["abababab","ab"], target = "ababaababa"
 * <p>
 * 输出： 2
 * <p>
 * 解释：
 * <p>
 * target 字符串可以通过连接以下有效字符串形成：
 * <p>
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * 示例 3：
 * <p>
 * 输入： words = ["abcdef"], target = "xyz"
 * <p>
 * 输出： -1
 */
public class Code1217 {

//    public static int minValidStrings(String[] words, String target) {
//        if (target == null || "".equals(target)) {
//            return -1;
//        }
//
//        int result = 0;
//        boolean checkStatus;
//        while (target.length() > 0) {
//            int length = target.length();
//            checkStatus = false;
//            for (int i = length; i > 0; i--) {
//                String st = target.substring(0, i);
//                for (String s : words) {
//                    if (s.startsWith(st)) {
//                        result++;
//                        target = target.substring(i, length);
//                        checkStatus = true;
//                        break;
//                    }
//                }
//                if (checkStatus) {
//                    break;
//                }
//            }
//            if (!checkStatus) {
//                break;
//            }
//        }
//
//        return result > 0 && target.length() == 0 ? result : -1;
//    }

    public static int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] back = new int[n];
        for (String word : words) {
            int[] pi = prefixFunction(word, target);
            int m = word.length();
            for (int i = 0; i < n; i++) {
                back[i] = Math.max(back[i], pi[m + 1 + i]);
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, (int) 1e9);
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i + 1 - back[i]] + 1;
            if (dp[i + 1] > n) {
                return -1;
            }
        }
        return dp[n];
    }

    private static int[] prefixFunction(String word, String target) {
        String s = word + "#" + target;
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }


    public static void main(String[] args) {
        String[] s = new String[] {"b","ccacc","a"};
        String a = "cccaaaacba";
        System.out.println(minValidStrings(s, a));
        //        System.out.println(a.substring(4,8));
    }
}
