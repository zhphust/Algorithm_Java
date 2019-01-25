package leetcode;

/**
 * 53. 最大子序和（简单）
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 说明：此例只需要求出最大和，无需构造和的构造，因此可以在一次遍历求出结果。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int ele : nums) {
            if (sum > 0)
                sum += ele;
            else
                sum = ele;
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,-1,-3};
        System.out.println(maxSubArray(nums));
    }
}
