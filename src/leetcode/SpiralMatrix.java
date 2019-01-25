package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵（中等）
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/25
 * @version 8.0
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int M = matrix.length;
        if (M == 0)
            return list;
        int N = matrix[0].length;

        for (int row = 0; row <= (M - 1) / 2; row++) {

            /*
             每一次循环的起点坐标（左上边界）是(row,row)，
             右上边界是(row, N - 1 - row)，
             右下边界是(M - 1 - row, N - 1 - row)，
             左下边界是(M - 1 - row, row)
             */
            // 1.处理上面一行, 列范围(row, N - 1 - row)
            if (row > N - 1 - row)
                return list;
            for (int j = row; j <= N - 1 - row; j++) {
                list.add(matrix[row][j]);
            }

            // 2.处理右边一列，行范围（row+1, M - 1 - row)
            if (row + 1 > M - row - 1)                        // 遇到单行，数据已处理完，直接返回
                return list;
            for (int i = row + 1; i <= M - 1 - row; i++) {
                list.add(matrix[i][N - 1 - row]);
            }

            // 3.处理下面一行，列范围(row, N - 1 - row - 1)
            if (N - 1 - row - 1 < row)                       // 遇到单列，数据已处理完，直接返回
                return list;
            for (int j = N - 1 - row - 1; j >= row; j--) {
                list.add(matrix[M - 1 - row][j]);
            }

            // 4.处理左边一列，行范围(row+1, M - 1 - row - 1)
            // 边界条件自然满足
            for (int i = M - 1 - row - 1; i >= row + 1; i--) {
                list.add(matrix[i][row]);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 10},
                {2, 20},
                {3, 30},
                {4, 40},
                {5, 50},
                {6, 60}
                };
        System.out.println(spiralOrder(matrix));
    }

}
