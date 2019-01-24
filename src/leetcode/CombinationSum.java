package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数
 * 解集不能包含重复的组合。
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> sumList = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();

        Arrays.sort(candidates);                     // 对数组进行排序
        backTrace(sumList, curList, candidates, target, 0);
        return sumList;
    }

    public static void backTrace(List<List<Integer>> sumList,        // 结果列表
                                 List<Integer> curList,              // 当前树路径的列表
                                 int[] candidates,                   // 数组，不变
                                 int residue,                        // 剩余目标值，递减变化
                                 int index) {                        // 数组中元素的索引
        //递归的终点
        if (residue == 0) {                             // 当前列表中的元素和恰好与目标值相等
            sumList.add(curList);
            return;
        }
        // 从当前索引开始（不从0开始，是为了避免重复添加结果），查找是否有匹配的元素
        for (int i = index; i < candidates.length; i++) {
            if (residue < candidates[i])         // 剩余目标值小于当前索引元素，则小于后续所有元素，故直接返回
                return;

            //深拷贝
            List<Integer> list = new ArrayList<>(curList);
            list.add(candidates[i]);              // 将当前元素添加到当前列表中

            //递归运算，将当前索引传递至下一次运算是为了避免结果重复。
            backTrace(sumList, list, candidates, residue - candidates[i], i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum(nums, target);
        System.out.println(result);
    }
}
