package leetcode;

/**
 * 74. 搜索二维矩阵（中等，二分查找）
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class SearchInMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        if (M == 0)
            return false;
        int N = matrix[0].length;
        int lo = 0;
        int hi = M * N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int row = mid / N;
            int col = mid % N;
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50},
        };
        System.out.println(new SearchInMatrix().searchMatrix(matrix, 3));
    }
}
