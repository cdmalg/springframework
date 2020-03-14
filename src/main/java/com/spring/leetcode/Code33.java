package com.spring.leetcode;

import java.util.Arrays;

/**
 * 合并排序的数组
 *
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n.
 *
 * 输入: A = [1,2,3,0,0,0], m = 3   B = [2,5,6], n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Code33 {

  // 最初想法，将b数组添加到a数组中，利用Arrays.sort()进行排序
  public static void merge(int[] A, int m, int[] B, int n) {
    for (int i = 0; i < n; i++) {
      A[m + i] = B[i];
    }
    Arrays.sort(A);
  }


  // 网上效率较高的方案，理解起来比较繁琐
  public static void merge1(int[] A, int m, int[] B, int n) {
    if (m >= 0) {
      // 先将A数组从第n位开始,复制m位到后面(即先将A右移到末尾)，[1,2,3,0,0,0] --> [1,2,3,1,2,3]
      System.arraycopy(A, 0, A, n, m);
    }

    int i = n, j = 0;
    int k = 0;
    // 循环比较，从A数组第n位到第n+m位（其实就是A数组第0位到第m位）分别和B数组进行比较，谁小(等于算A的)谁与A数组第K++位替换，并且顺序+1
    while (i < (m + n) && j < n) {
      if (A[i] <= B[j]) {
        A[k++] = A[i++];
      } else {
        A[k++] = B[j++];
      }
    }
    // 如果经过上述比较后存在i < m,说明A数组中还有较大的数，将该数依次与A数组k++进行替换
    while (i < m) {
      A[k++] = A[i++];
    }
    // 如果经过上述比较后存在j < n,说明B数组中还有较大的数，将概述一次与A数组k++进行替换
    while (j < n) {
      A[k++] = B[j++];
    }
    //PS:因为while (i < (m + n) && j < n)限制，后两个while方法只有一个可以进入。
  }

  public void mergePro(int[] A, int m, int[] B, int n) {
    // 先确保将其中一个数组中的数字遍历完
    while (m > 0 && n > 0) {
      // 对比选出较大的数放在 m + n - 1 的位置，并将选出此数的指针向前移动
      A[m + n - 1] = A[m - 1] > B[n - 1] ? A[m-- - 1] : B[n-- - 1];
    }
    // 剩下的数都比已经遍历过的数小
    // 如果 m 不为 0，则 A 没遍历完，都已经在 A 中不用再管
    // 如果 n 不为 0，则 B 没遍历完，直接全移到 A 中相同的位置
    while (n > 0) {
      A[n - 1] = B[n - 1];
      n--;
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] b = {2, 5, 6};
    int n = 3;

    merge1(a, 3, b, 3);

  }

}
