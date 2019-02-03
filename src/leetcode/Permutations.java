package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列（中等，回溯算法）
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0)
            return result;
        List<Integer> temp = new ArrayList<>();
        backTrace(nums, result, temp);
        return result;
    }

    private void backTrace(int[] nums, List<List<Integer>> result, List<Integer> temp) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (canInsert(temp, nums[i])) {
                temp.add(nums[i]);
                backTrace(nums, result, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean canInsert(List<Integer> temp, int ele) {
        for (Integer i : temp) {
            if (ele == i)
                return false;
        }
        return true;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Permutations().permute(nums));
    }
}
