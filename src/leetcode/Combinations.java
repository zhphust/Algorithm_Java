package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合（中等，回溯算法）
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/5
 * @version 8.0
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || n < k)
            return result;

        List<Integer> temp = new ArrayList<>();
        backTrace(n, k, result, temp, 1);

        return result;
    }

    private void backTrace(int n, int k, List<List<Integer>> result, List<Integer> temp, int index) {
        if (k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i <= n - k + 1; i++) {
            temp.add(i);
            backTrace(n, k - 1, result, temp, i + 1);     // 回溯
            temp.remove(temp.size() - 1);                   // 剪枝
        }
    }

    public static void main(String[] args) {
        System.out.println(new Combinations().combine(4, 2));
    }
}
