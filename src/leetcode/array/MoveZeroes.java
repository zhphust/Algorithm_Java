package leetcode.array;

import java.util.Arrays;

/**
 * 283. 移动零（简单）
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/21
 * @version 8.0
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        /*
         * 思想：
         * 保持一个指针位置，该位置之前的所有元素都非零；
         * 遍历一遍后，该位置之后的所有元素都置为0
         */
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pos++] = nums[i];
            }
        }
        for (int i = pos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new MoveZeroes().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
