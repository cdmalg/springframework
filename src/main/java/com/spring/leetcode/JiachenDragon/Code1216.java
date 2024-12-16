package com.spring.leetcode.JiachenDragon;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Chars;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你一个字符串 s 。
 * <p>
 * 请你进行以下操作直到 s 为 空 ：
 * <p>
 * 每次操作 依次 遍历 'a' 到 'z'，如果当前字符出现在 s 中，那么删除出现位置 最早 的该字符（如果存在的话）。
 * 例如，最初 s = "aabcbbca"。我们执行下述操作：
 * <p>
 * 移除下划线的字符  s = "aabcbbca"。结果字符串为 s = "abbca"。
 * 移除下划线的字符  s = "abbca"。结果字符串为 s = "ba"。
 * 移除下划线的字符  s = "ba"。结果字符串为 s = ""。
 * 请你返回进行 最后 一次操作 之前 的字符串 s 。在上面的例子中，答案是 "ba"。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aabcbbca"
 * 输出："ba"
 * 解释：已经在题目描述中解释。
 * 示例 2：
 * <p>
 * 输入：s = "abcd"
 * 输出："abcd"
 * 解释：我们进行以下操作：
 * - 删除 s = "abcd" 中加粗加斜字符，得到字符串 s = "" 。
 * 进行最后一次操作之前的字符串为 "abcd" 。
 */
public class Code1216 {

    /**
     * 方案一：
     * 解题思路：筛选出出现次数最多的字母，然后根据字母的位置，由近及远拼接字符串。可利用jdk8，lambda表达式解决问题
     * 但过于利用封装方法，无法通过网站简单校验，实际应用中可以使用。
     *
     * @param s
     * @return
     */
    public String lastNonEmptyString1(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }

        List<Character> characterList = Chars.asList(s.toCharArray());

        Map<Character, Long> map = characterList.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        Long l = map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        List<Character> list = map.entrySet().stream().filter(en -> en.getValue().equals(l)).map(Map.Entry::getKey)
            .collect(Collectors.toList());

        StringBuilder result = new StringBuilder("");
        List<Integer> indexList = new ArrayList<>();
        for (Character c : list) {
            int index = characterList.lastIndexOf(c);
            indexList.add(index);
        }
        indexList.stream().sorted().forEach(a -> result.append(characterList.get(a)));

        return result.toString();
    }

    /**
     * 方案二:
     * 1、创建一个int类型数组，数组长度26，用来记录字符串中各字母出现次数。
     * 2、for循环循环字符串各字节，通过ascii码确定字符所在数组位置，并做自增处理。并且记录最大值。
     * 3、循环结束后，各字符出现次数都做了统计，最后一次出现的就是结果，所以创建一个新的数组用来记录字母出现次数。
     * 4、再次循环字符串，字符出现次数等于最大值时将其计入result字符串中。
     * 5、完成
     *
     * @param s
     * @return
     */
    public static String lastNonEmptyString2(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        int[] letter = new int[26];
        char a = 'a';
        int max = 0;
        for (char c : s.toCharArray()) {
            letter[c - a]++;
            max = Math.max(max, letter[c - a]);
        }

        StringBuilder result = new StringBuilder("");
        int[] compareLetter = new int[26];
        for (char c : s.toCharArray()) {
           compareLetter[c-a]++;
           if(compareLetter[c-a] == max){
               result.append(c);
           }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "abvvccd";

        System.out.println(lastNonEmptyString2(s));
    }
}