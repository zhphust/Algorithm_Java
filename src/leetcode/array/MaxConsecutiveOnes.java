package leetcode.array;

/**
 * 485. 最大连续1的个数（简单）
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/21
 * @version 8.0
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cur++;
            } else {
                if (cur > count) {
                    count = cur;
                }
                cur = 0;
            }
        }
        // 以1结束的情况
        if (cur > count)
            count = cur;
        return count;
    }
}
