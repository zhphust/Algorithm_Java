package leetcode;

/**
 * 48. 旋转图像（中等）
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class RotateImage {
    public static void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int row = 0;  row < N / 2; row++) {
            for (int col = row; col < N - 1 - row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[N - 1 - col][row];
                matrix[N - 1 - col][row] = matrix[N - 1 - row][N - 1 - col];
                matrix[N - 1 - row][N - 1 - col] = matrix[col][N - 1 - row];
                matrix[col][N - 1 - row] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 10, 20, 10},
                          {5, 6, 7, 8, 30, 40, 20},
                          {9, 10, 11, 12, 50, 60, 30},
                          {13, 14, 15, 16, 70, 80, 40},
                          {17, 18, 19, 20, 90, 100, 50},
                          {21, 22, 23, 24, 110, 120, 60},
                          {25, 26, 27, 28, 130, 140, 70}};
        rotate(matrix);
        System.out.println();
    }
}
