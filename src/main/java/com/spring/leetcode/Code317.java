package com.spring.leetcode;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.Map;

/**
 * 拼写单词
 * <p>
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * <p>
 * 示例：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 */
public class Code317 {


    // 本人解题思路：没思路，硬解，效率及消耗都很大，不建议使用，请看下一个解题思路
    public static int countCharacters(String[] words, String chars) {

        int result = 0;

        for (String word : words) {
            int n = 0;
            int count = 0;
            String wordCopy = word;
            String charsCopy = chars;
            while (wordCopy.length() > 0 && n < charsCopy.length()) {
                char x = wordCopy.charAt(0);
                char y = charsCopy.charAt(n);
                if (x == y) {
                    wordCopy = wordCopy.replaceFirst(String.valueOf(x), "");
                    charsCopy = charsCopy.replaceFirst(String.valueOf(y), "");
                    count++;
                    n = 0;
                } else {
                    n++;
                }
            }
            if (wordCopy.length() == 0) {
                result += count;
            }
        }

        return result;
    }

    // 解题思路：我们既统计“字母表”中字母出现的次数，也统计单词中字母出现的次数。如果单词中每种字母出现的次数都小于等于字母表中字母出现的次数，那么这个单词就可以由字母表拼出来。
    public static int countCharactersPro(String[] words, String chars) {
        int[] chars_count = count(chars); // 统计字母表的字母出现次数
        int res = 0;
        for (String word : words) {
            int[] word_count = count(word); // 统计单词的字母出现次数
            if (contains(chars_count, word_count)) {
                res += word.length();
            }
        }
        return res;

    }

    // 检查字母表的字母出现次数是否覆盖单词的字母出现次数
    public static boolean contains(int[] chars_count, int[] word_count) {
        for (int i = 0; i < 26; i++) {
            if (chars_count[i] < word_count[i]) {
                return false;
            }
        }
        return true;
    }

    // 统计 26 个字母出现的次数
    public static int[] count(String word){
        int[] counter = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            counter[c-'a']++;
        }
        return counter;
    }

    public static void main(String[] args) {

        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars));
    }

}
