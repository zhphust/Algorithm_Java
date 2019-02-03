package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II（中等，回溯算法）
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0)
            return list;
        Arrays.sort(nums);                                 // 排序
        boolean marked[] = new boolean[nums.length];       // 标记该元素是否被访问过
        backTrace(list, new ArrayList<>(), nums, marked);  //（类似深度优先搜索）
        return list;
    }

    public void backTrace(List<List<Integer>> list, List<Integer> res, int nums[], boolean marked[]) {
        if (res.size() == nums.length) {
            list.add(new ArrayList<>(res));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(marked[i] || (i >= 1 && nums[i] == nums[i-1] && !marked[i-1]))
                continue;
            res.add(nums[i]);
            marked[i] = true;
            backTrace(list, res, nums, marked);
            res.remove(res.size() - 1);
            marked[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(new PermutationsII().permuteUnique(nums));
    }
}
