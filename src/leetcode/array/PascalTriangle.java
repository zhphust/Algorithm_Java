package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角（简单）
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/19
 * @version 8.0
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            if (!result.isEmpty()) {
                List<Integer> lastRow = result.get(result.size() - 1);
                for (int j = 0; j < lastRow.size() - 1; j++) {
                    curRow.add(lastRow.get(j) + lastRow.get(j + 1));
                }
                curRow.add(1);
            }
            result.add(curRow);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle().generate(5));
    }
}
