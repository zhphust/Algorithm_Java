package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集（中等，回溯算法）
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backTrace(nums, result, temp, 0);
        return result;
        /*
        List<List<Integer>> subset = new ArrayList<>();
        subset.add(new ArrayList<>());                     // add an empty list into the subset

        for (int i : nums) {                               // for every element in the array
            List<List<Integer>> temp = new ArrayList<>();  // make a copy for the current subset
            for (List<Integer> arr : subset) {
                temp.add(new ArrayList<>(arr));            // copy an list in the subset
                arr.add(i);                                // add element i in all the list in the subset
            }
            for (List<Integer> arr : temp) {
                subset.add(new ArrayList<>(arr));
            }
        }
        return subset;
        */
    }

    private void backTrace(int[] nums, List<List<Integer>> result, List<Integer> temp, int index) {
        result.add(new ArrayList<>(temp));
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            backTrace(nums, result, temp, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Subsets().subsets(nums));
    }
}
