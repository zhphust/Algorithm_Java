package leetcode;

/**
 * 52. N皇后 II（困难，回溯算法）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/3
 * @version 8.0
 */
public class NQueensII {
    private int sum = 0;

    public int totalNQueens(int n) {
        if (n <= 0)
            return sum;
        int[] temp = new int[n+1];
        temp[0] = 0;
        backTrace(n, 0, temp, 1);
        return sum;
    }

    private void backTrace(int n, int sum, int[] temp, int row) {
        if (row > n) {
            this.sum++;
            return;
        }
        for (int col = 1; col <= n; col++) {
            temp[row] = col;
            if (canPlaced(temp, row)) {
                backTrace(n, sum + 1, temp, row + 1);
            }
        }
    }

    private boolean canPlaced(int[] temp, int row) {
        for (int i = 1; i < row; i++) {
            if (temp[i] == (temp[row]) || Math.abs(row - i) == Math.abs(temp[row] - temp[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new NQueensII().totalNQueens(8));
    }
}
