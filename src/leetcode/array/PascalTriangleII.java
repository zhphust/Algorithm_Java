package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II（简单）
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 注：杨辉三角公式：第n行第i个数字为组合数C(n - 1, m - 1)
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/19
 * @version 8.0
 */
public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {        // 杨辉三角索引行从0开始，第0行有一个数字，以此类推
        List<Integer> result = new ArrayList<>();
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        for (int i = 0; i < rowIndex / 2 + 1; i++) {
            result.add(compute(rowIndex, i));
        }
        int size = result.size();
        if (rowIndex % 2 == 1) {
            result.add(result.get(size - 1));
        }
        for (int i = size - 2; i >= 0; i--) {
            result.add(result.get(i));
        }
        return result;
    }

    private int compute(int n, int m) {
        long result = 1;
        int k = 1;
        while (k <= m) {
            result = result * n / k;
            n--;
            k++;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangleII().getRow(5));
    }
}
