package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II （中等，回溯算法）
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        Arrays.sort(nums);                                  // 排序
        backTrace(nums, result, temp, 0);
        return result;
    }

    private void backTrace(int[] nums, List<List<Integer>> result, List<Integer> temp, int index) {
        result.add(new ArrayList<>(temp));                 // 中间结果的副本添加到最终结果
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1])         // 相同元素，略过
                continue;
            temp.add(nums[i]);                            // 将当前元素添加到中间结果
            backTrace(nums, result, temp, i + 1);   // 递归，下一层次
            temp.remove(temp.size()-1);             // 中间结果去除最后一次添加的元素，回溯到上一层
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(new SubsetsII().subsetsWithDup(nums));
    }
}
