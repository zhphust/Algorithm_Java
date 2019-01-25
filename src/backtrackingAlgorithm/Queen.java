package backtrackingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 八皇后问题（回溯算法）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class Queen {
    private List<Integer> solution;         // 解的集合
    private Integer[] x;                     // 当前解，x[i]=j表示在第i行，j列上放置皇后
    private int N;                         // 皇后个数
    private int sum = 0;                   // 解的总个数

    public Queen(int n) {
        solution = new ArrayList<>();
        x = new Integer[n+1];                  // 索引为0的不使用
        x[0] = 0;
        N = n;
        backTrace(1);
    }

    /**
     * 回溯算法
     * @param row 在第row行摆放第row个皇后
     */
    private void backTrace(int row) {
        if (row > N) {
            sum++;
            solution.addAll(Arrays.asList(x));
        } else {
            // 第i行放置皇后，遍历所有列
            for (int col = 1; col <= N; col++) {
                x[row] = col;                      // 在当前位置放置一个皇后
                if (canPlaced(row)) {             // 检测该行放置的元素是否可行
                    backTrace(row+1);         // 则，摆放下一个皇后
                }
            }
        }
    }

    private boolean canPlaced(int row) {
        for (int r = 1; r < row; r++) {
            // 前面所有行和当前行是否在一列上，或者在对角线上
            if (x[r].equals(x[row]) || Math.abs(row - r) == Math.abs(x[row] - x[r]))
                return false;
        }
        return true;
    }

    public int sum() {
        return sum;
    }

    public void printSolution() {
        for (int i = 0; i < sum; i++) {
            for (int j = 0; j < N+1; j++) {
                int k = solution.get(j + i * (N+1));
                if (k != 0) {
                    System.out.print(k + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queen q = new Queen(4);
        System.out.println(q.sum());
        q.printSolution();
    }
}
