package leetcode;

import java.util.ArrayList;
import java.util.List;


/**
 * 22. 括号生成（中等，回溯算法）
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String temp = "";
        backTrace(n, result, temp, 0, 0);
        return new ArrayList<>(result);
    }

    private void backTrace(int n, List<String> result, String temp, int left, int right) {
        if (temp.length() == 2 * n) {
            result.add(temp);
            return;
        }
        if (left < n) {
            backTrace(n, result, temp+'(', left+1, right);
        }
        if (right < left) {
            backTrace(n, result, temp+')', left, right+1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }
}
