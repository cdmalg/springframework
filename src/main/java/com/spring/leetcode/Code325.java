package com.spring.leetcode;

/**
 * 三维形体的表面积
 * <p>
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * 请你返回最终形体的表面积。
 * <p>
 * 示例：
 * 输入：[[2]]
 * 输出：10
 * <p>
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * <p>
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 */
public class Code325 {

    // 解题思路：
    // 我们单独计算每一个 v = grid[i][j] 所贡献的表面积，再将所有的 v 值相加就能得到最终形体的表面积：
    // 对于顶面和底面的表面积，如果 v > 0，那么顶面和底面各贡献了 1 的表面积，总计 2 的表面积；
    // 对于四个侧面的表面积，只有在相邻位置的高度小于 v 时，对应的那个侧面才会贡献表面积，且贡献的数量为 v - nv，其中 nv 是相邻位置的高度。我们可以将其写成 max(v - nv, 0)。
    // 对于每个 v = grid[r][c] > 0，计算 ans += 2，对于 grid[r][c] 四个方向的每个相邻值 nv 还要加上 max(v - nv, 0)。
    public static int surfaceArea(int[][] grid) {

        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        int N = grid.length;
        int ans = 0;

        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] > 0) {
                    ans += 2;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        int nv = 0;
                        if (0 <= nr && nr < N && 0 <= nc && nc < N)
                            nv = grid[nr][nc];

                        ans += Math.max(grid[r][c] - nv, 0);
                    }
                }

        return ans;
    }

    public static void main(String[] args) {
        int[][] a = {{1,0},{0,2}};
        System.out.println(surfaceArea(a));
    }
}
