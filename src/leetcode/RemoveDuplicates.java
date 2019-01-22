package leetcode;

/**
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/22
 * @version 8.0
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        /*
          思路：保持两个指针，一个表示有效长度，一个表示当前待比较的元素
         */
        int len = 1;
        int element = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != element) {
                nums[len++] = nums[i];
                element = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }
}
