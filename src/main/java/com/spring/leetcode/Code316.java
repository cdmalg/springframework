package com.spring.leetcode;


/**
 * 字符串压缩
 * <p>
 * 利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 假设字符串中只包含大小写英文字母（a至z）。
 */
public class Code316 {

    // 解题思路： 将字符串的每个子节跟下一个子节进行比较，如果一样，数量加1，不同则将上个子节的值和重复数量写入结果中，最后判断长度是否变小
    public static String compressString(String S) {

        if (S == null || "".equals(S)) {
            return "";
        }
        StringBuilder result = new StringBuilder(String.valueOf(S.charAt(0)));
        int index = 0;
        int count = 1;
        while (index < S.length() - 1) {
            char a = S.charAt(index);
            char b = S.charAt(index + 1);
            if (a == b) {
                count++;
            } else {
                result.append(count).append(b);
                count = 1;
            }
            index++;
        }
        result.append(count);
        return result.toString().length() >= S.length() ? S : result.toString();
    }

    public static void main(String[] args) {
        String a = "aabcccccaa";

        System.out.println(compressString(a));
    }
}
