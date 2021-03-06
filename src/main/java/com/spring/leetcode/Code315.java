package com.spring.leetcode;

/**
 * 岛屿的最大面积
 * 给定一个包含了一些 0 和 1的非空二维数组 grid 。
 * 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,（1,0,1）,0,0],
 * [0,1,0,0,1,1,0,0,（1,1,1）,0,0],
 * [0,0,0,0,0,0,0,0,（0,0,1）,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * <p>
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 */
public class Code315 {


    // 解题思路： 类似橘子腐烂问题，第一层循环遍历每个元素，如果是1的话，进行递归获取与该元素不断链接的1，相加后比较大小。
    // 注意引用copy数组在防止多次计算
    public static int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[][] copy = new int[grid.length][grid[0].length];

        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 1 && copy[i][j] == 0) {
                    int result = calculation(i, j, grid, copy);
                    max = Math.max(max, result);
                }

            }
        }
        return max;
    }

    public static int calculation(int x, int y, int[][] grid, int[][] copy) {
        int result = grid[x][y];
        if (result == 0) {
            return 0;
        }
        copy[x][y] = result;
        // 向左
        if (x > 0 && copy[x - 1][y] == 0) {
            result += calculation(x - 1, y, grid, copy);
        }
        // 向右
        if (x < grid.length - 1 && copy[x + 1][y] == 0) {
            result += calculation(x + 1, y, grid, copy);
        }
        // 向上
        if (y > 0 && copy[x][y - 1] == 0) {
            result += calculation(x, y - 1, grid, copy);
        }
        // 向下
        if (y < grid[0].length - 1 && copy[x][y + 1] == 0) {
            result += calculation(x, y + 1, grid, copy);
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] a = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println(maxAreaOfIsland(a));
    }
}
