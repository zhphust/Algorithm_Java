package leetcode.array;

import java.util.Arrays;

/**
 * 532. 数组中的K-diff数对（简单）
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
 * 这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/21
 * @version 8.0
 */
public class KDiffPairsArray {
    public int findPairs(int[] nums, int k) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i >= 1 && nums[i] == nums[i-1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    if (nums[j] == nums[j-1] && j - 1 != i)
                        break;
                    count++;
                } else if (nums[j] - nums[i] > k) {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 1, 5};
        System.out.println(new KDiffPairsArray().findPairs(nums, 2));
    }
}
