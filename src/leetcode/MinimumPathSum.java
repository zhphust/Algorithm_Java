package leetcode;

/**
 * 64. 最小路径和（中等，动态规划）
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class MinimumPathSum {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m+1][n+1];

        for (int i = 0; i < m; i++) {
            sum[i+1][1] = sum[i][1] + grid[i][0];
        }
        for (int j = 0; j < n; j++) {
            sum[1][j+1] = sum[1][j] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i+1][j+1] = Math.min(sum[i+1][j], sum[i][j+1]) + grid[i][j];
            }
        }

        return sum[m][n];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid));
    }
}
