package leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * “字典序”：用数组中的元素可组成n!个排列，将所有排列按升序排列后，当前排列的下一个排列即为所求排列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {       // 找到一个元素，该元素小于其后一个元素
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {      // 找到一个元素，该元素是大于上述元素的最小元素
                j--;
            }
            swap(nums, i, j);                           // 交换这两个元素
        }
        reverse(nums, i + 1, nums.length - 1);     // 翻转该元素后面的所有元素，使其按升序排列（原来按降序排列）
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
