package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 73. 矩阵置零（中等）
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        boolean firstRowZero = false;             // record the first row statement, the element will changed
        boolean firstColZero = false;             // similar to above
        for (int i = 0; i < M; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int j = 0; j < N; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for (int i = 0; i < M; i++) {              // reflect the element to first column and first row
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < M; i++) {            // according to the first column and row, set the each element state
            for (int j = 1; j < N; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // deal with the first column and row
        if (firstColZero) {
            for (int i = 0; i < M; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRowZero) {
            for (int j = 0; j < N; j++) {
                matrix[0][j] = 0;
            }
        }
        /*
        ArrayList<Integer> list = new ArrayList<>();             // 列表用来保存元素为0的坐标
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    list.add(i);
                    list.add(j);
                }
            }
        }
        for (int i = 0; i < list.size();) {
            int row = list.get(i++);
            int col = list.get(i++);
            for (int k = 0; k < M; k++) {
                matrix[k][col] = 0;
            }
            for (int k = 0; k < N; k++) {
                matrix[row][k] = 0;
            }
        }
        */
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5},
        };
        new SetMatrixZeroes().setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
