package com.spring.leetcode;

import com.alibaba.fastjson.JSON;

/**
 * 分糖果
 *
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 *
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 *
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 *
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。
 *
 * 注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 *
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 */
public class Code35 {

  // 初始想法，较为简单，但效率很差
  public static int[] distributeCandies(int candies, int num_people) {
    int[] ans = new int[num_people];
    int init = 1;
    while (candies > 0) {
      for (int i = 0; i < num_people; i++) {
        init = Math.min(candies, init);
        ans[i] += init;
        candies -= init;
        init++;
      }
    }
    return ans;
  }

  // 上一方法改进版，利用取余来获取ans下标
  public int[] distributeCandiesPro(int candies, int num_people) {
    int[] ans = new int[num_people];
    int i = 0;
    while (candies != 0) {
      ans[i % num_people] += Math.min(candies, i + 1);
      candies -= Math.min(candies, i + 1);
      i += 1;
    }
    return ans;
  }

  // 利用等差数列求和公式解题
  // 假设p为等差数列
  public int[] distributeCandiesProMax(int candies, int num_people) {
    int n = num_people;
    // how many people received complete gifts
    int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
    int remaining = (int) (candies - (p + 1) * p * 0.5);
    int rows = p / n, cols = p % n;

    int[] d = new int[n];
    for (int i = 0; i < n; ++i) {
      // complete rows
      d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
      // cols in the last row
      if (i < cols) {
        d[i] += i + 1 + rows * n;
      }
    }
    // remaining candies
    d[cols] += remaining;
    return d;
  }

  public static void main(String[] args) {

    System.out.println(JSON.toJSONString(distributeCandies(10, 3)));
  }
}
