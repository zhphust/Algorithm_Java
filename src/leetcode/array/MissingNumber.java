package leetcode.array;

/**
 * 268. 缺失数字（简单）
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/20
 * @version 8.0
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        // 未出现的数字等于1~n的数列求和减去数组中的元素和
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        return n * (n + 1) / 2 - sum;
    }
}
