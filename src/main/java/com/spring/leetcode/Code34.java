package com.spring.leetcode;

/**
 * 腐烂的橘子
 *
 * 给定一个3*3的网格，每个单元格可以有以下三个值之一：
 *
 * 0 代表空单元格； 1 代表新鲜橘子； 2 代表腐烂的橘子。
 *
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 */
public class Code34 {

  // 最初想法，遍历每个橘子，如果是正常的，检查周边是否存在腐烂的橘子，存在则腐烂，将该位置记录下来，等第一波感染结束后统一替换并次数+1
  public static int orangesRotting(int[][] grid) {

    // 统计总共所需次数
    int time = 0;
    // 开关控制循环，防止存在不可能感染的橘子
    boolean control = true;
    while (control) {
      // 当前一轮感染中未感染橘子数量（数量为0时代表已全部感染）
      int unRottingCount = 0;
      // 当前一轮感染中已被感染橘子数量（数量为0时代表没有橘子感染，全员健康，返回-1）
      int rottingCount = 0;
      // 用于记录当前一轮感染中，新添被感染的橘子位置
      int[][] gridCopy = new int[grid.length][grid[0].length];
      // 遍历每个橘子
      for (int m = 0; m < grid.length && control; m++) {
        for (int n = 0; n < grid[m].length; n++) {
          // 判断如果是正常橘子则是否会被感染
          if (grid[m][n] == 1) {
            unRottingCount++;
            // 当前橘子上下左右的橘子，对四个橘子的值进行逻辑或
            int left = n >= 1 ? grid[m][n - 1] : 0;
            int right = n < grid[m].length - 1 ? grid[m][n + 1] : 0;
            int top = m >= 1 ? grid[m - 1][n] : 0;
            int bottom = m < grid.length - 1 ? grid[m + 1][n] : 0;
            int result = left | right | top | bottom;
            // 0 表示四周没有橘子，则不可能全部感染，返回-1，并将未感染数清零使其直接跳出while循环
            if (result == 0) {
              time = -1;
              control = false;
              unRottingCount = 0;
              break;
            }
            // 2或3 表示当前橘子周边存在腐烂的橘子，记录该橘子的位置，这轮过后它将被感染
            if (result == 3 || result == 2) {
              gridCopy[m][n] = 2;
            }
          }
        }
      }
      // 将这轮感染的橘子统一感染，并统计感染数量（rottingCount）
      for (int j = 0; j < gridCopy.length && control; j++) {
        for (int k = 0; k < gridCopy[j].length; k++) {
          if (gridCopy[j][k] == 2) {
            grid[j][k] = 2;
            rottingCount++;
          }
        }
      }
      // 感染数量为零代表已经没有可以感染的了，跳出while循环
      if (unRottingCount == 0) {
        control = false;
      } else if (rottingCount == 0) {
        control = false;
        time = -1;
      } else {
        time++;
      }
    }
    return time;
  }

  // leetcode上效率最高的方案
  // 解题思路：
  // 第一遍循环利用递归方式将橘子按时间顺序全部腐烂，并用腐烂值来区分先后顺序
  // 第二遍循环所有橘子，如果存在未腐烂的橘子，返回-1，不存在则去最大腐烂值-2为腐烂时间
  public int orangesRotting1(int[][] grid) {
    boolean has2 = false;
    for (int i = 0; i < grid.length; i++) {//第一次遍历，对每个初始腐烂橘子进行dfs
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 2) {
          has2 = true;
          dfs(grid, i, j, 2);
        }
      }
    }

    int res = 0;
    for (int i = 0; i < grid.length; i++) {//第二次遍历，找出最大腐烂值，如果有新鲜橘子直接返回-1
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          return -1;
        }
        res = Math.max(res, grid[i][j]);
      }
    }

    if (!has2) {
      return 0;
    }
    return res - 2;
  }

  private void dfs(int[][] grid, int x, int y, int val) {//val表示腐烂值，每传播一层的腐烂值加1
    //是新鲜橘子直接腐烂，如果之前已经腐烂但这次腐烂的距离更近（val更小），重新赋腐烂值
    grid[x][y] = val;
    if (x < grid.length - 1 && (grid[x + 1][y] == 1 || grid[x + 1][y] > val + 1)) {
      dfs(grid, x + 1, y, val + 1);
    }
    if (x >= 1 && (grid[x - 1][y] == 1 || grid[x - 1][y] > val + 1)) {
      dfs(grid, x - 1, y, val + 1);
    }
    if (y < grid[0].length - 1 && (grid[x][y + 1] == 1 || grid[x][y + 1] > val + 1)) {
      dfs(grid, x, y + 1, val + 1);
    }
    if (y >= 1 && (grid[x][y - 1] == 1 || grid[x][y - 1] > val + 1)) {
      dfs(grid, x, y - 1, val + 1);
    }
  }

  public static void main(String[] args) {
    int[][] a = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
//    int[][] a = {{2},{2},{1},{0},{1},{1}};
    System.out.println(orangesRotting(a));
//    int[][] b = new int[2][];
//    b[1][1] = 1;
  }
}
