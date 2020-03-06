package com.spring.leetcode;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 输入：target = 9
 *
 * 输出：[[2,3,4],[4,5]]
 */
public class Code36 {

  // 解题思路，等差数列逆向思维，要求输出连续的正整数序列，那么公差就是1
  // 如题：利用等差数列等差数列公式：（首项+末项）* 项数 = target * 2
  // 将上述公式中（首项+末项）转化成（首项 * 2） + （项数 * 公差）
  // 设项数为p，首项为x，target为t，则公式为：2x + p - 1  = 2t / p
  // 项数p的筛选条件：1.正整数；2.能被target * 2整除；3.小于等于target/2（题目要求至少含有两个数，项数大于一半相加不可能等于target）
  public static int[][] findContinuousSequence(int target) {

    List<int[]> list = new ArrayList<int[]>();
    int n = 0;
    //筛选项数p的值
    for (int i = target / 2; i >= 2; i--) {
      if (target * 2 % i == 0 && (target * 2 / i - i + 1) % 2 == 0 && target * 2 / i - i + 1 > 0) {
        int x = (target * 2 / i - i + 1) / 2;
        int[] res = new int[i];
        for (int k = 0; k < i; k++) {
          res[k] = x++;
        }
        list.add(res);
      }
    }
    return list.toArray(new int[0][]);
  }

  public static void main(String[] args) {

    System.out.println(JSON.toJSONString(findContinuousSequence(15)));
  }


}
