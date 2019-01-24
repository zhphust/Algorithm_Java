package leetcode;

import java.util.*;

/**
 * 40. 组合总和 II（回溯算法）
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> sumList = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();

        Arrays.sort(candidates);                   // 排序
        backTrace(sumList, curList, candidates, target, -1);   // 当前未处理任何元素，故索引为-1
        return sumList;
    }

    private static void backTrace(List<List<Integer>> sumList,
                                  List<Integer> curList,
                                  int[] candidates,
                                  int residue,
                                  int index) {
        if (residue == 0)
            sumList.add(curList);

        for (int i = index + 1; i < candidates.length; i++) {     // i表示当前元素的下一元素索引
            if (residue < candidates[i])
                return;
            ArrayList<Integer> list = new ArrayList<>(curList);   // 拷贝当前列表的元素
            list.add(candidates[i]);

            backTrace(sumList, list, candidates, residue - candidates[i], i);

            // 处理重复元素
            int sameElementIndex = i + 1;
            while (sameElementIndex < candidates.length && candidates[sameElementIndex] == candidates[i]) {
                sameElementIndex++;
            }
            if (sameElementIndex > i + 1) {
                i = sameElementIndex - 1;                // 将i定位到相同元素的最后一个索引
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> result = combinationSum2(nums, target);
        System.out.println(result);
    }
}
