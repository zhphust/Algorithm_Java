package leetcode;

/**
 * 35. 搜索插入位置（简单）
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class InsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        while (i < nums.length && nums[i] < target) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 0));
    }
}
