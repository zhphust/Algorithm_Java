package leetcode;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/25
 * @version 8.0
 */
public class SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int k = 1;
        // for循环处理n为偶数的情况，n为奇数的唯一特例单独处理
        for (int row = 0; row < n / 2; row++) {
            for (int j = row; j <= n - row - 1; j++) {        // 行不变，列的右边界是n-row-1
                matrix[row][j] = k++;
            }
            for (int i = row + 1; i <= n - row - 1; i++) {        // 列不变n-row-1，行的下边界是n-row-1
                matrix[i][n - row - 1] = k++;
            }
            for (int j = n - row - 1 - 1; j >= row; j--) {      // 行不变n-row-1，列的左边界是row
                matrix[n - row - 1][j] = k++;
            }
            for (int i = n - row - 1 - 1; i >= row + 1; i--) {       // 列不变row，行的上边界是row+1
                matrix[i][row] = k++;
            }
        }

        // n为奇数的情况
        if (n % 2 != 0) {
            matrix[n/2][n/2] = k;
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }
}
