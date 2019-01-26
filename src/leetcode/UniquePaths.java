package leetcode;

/**
 * 62. 不同路径（中等，动态规划）
 * 一个机器人位于一个 m x n 网格的左上角
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
 * 问总共有多少条不同的路径？
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        // 自底向上动态规划
        int[][] path = new int[m+1][n+1];
        for (int i = 1; i < m+1; i++) {
            path[i][1] = 1;
        }
        for (int j = 1; j < n+1; j++) {
            path[1][j] = 1;
        }
        for (int i = 2; i < m+1; i++) {
            for (int j = 2; j < n+1; j++) {
                path[i][j] = path[i-1][j] + path[i][j-1];
            }
        }
        return path[m][n];

        /*
        // 递归，速度较慢
        if (m == 1 && n == 1)
            return 0;
        if (m == 1 || n == 1)
            return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        */
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }
}
