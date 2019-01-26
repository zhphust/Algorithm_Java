package leetcode;

import java.util.Arrays;

/**
 * 75. 颜色分类（中等）
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums.length <= 1)
            return;

        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int color = 0;
        for (int i = 0; i < nums.length; i++) {
            while (count[color] == 0) {
                color++;
            }
            nums[i] = color;
            count[color]--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 2,0,2,1,1,0};
        new SortColors().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
