package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后（困难，回溯算法）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/3
 * @version 8.0
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        Integer[] temp = new Integer[n+1];
        temp[0] = 0;
        backTrace(n, result, temp, 1);
        return result;
    }

    private void backTrace(int n, List<List<String>> result, Integer[] temp, int row) {
        if (row > n) {
            List<String> list0 = new ArrayList<>();
            for (int row0 = 1; row0 <= n; row0++) {
                StringBuilder string0 = new StringBuilder();
                int queen = temp[row0];
                for (int col0 = 1; col0 <= n; col0++) {
                    if (col0 == queen)
                        string0.append('Q');
                    else
                        string0.append('.');
                }
                list0.add(string0.toString());
            }
            result.add(list0);
            return;
        }

        for (int col = 1; col <= n; col++) {
            temp[row] = col;
            if (canPlaced(temp, row)) {
                backTrace(n, result, temp, row + 1);
            }
        }
    }

    private boolean canPlaced(Integer[] temp, int row) {
        for (int i = 1; i < row; i++) {
            if (temp[i].equals(temp[row]) || Math.abs(row - i) == Math.abs(temp[row] - temp[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }
}
