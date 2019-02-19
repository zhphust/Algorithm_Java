package leetcode.array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组（简单）
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/19
 * @version 8.0
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos1 = m - 1;
        int pos2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (pos1 < 0 && pos2 >= 0) {
                nums1[i] = nums2[pos2--];
            } else if (pos2 < 0 && pos1 >= 0) {
                break;
            } else {
                if (nums1[pos1] >= nums2[pos2]) {
                    nums1[i] = nums1[pos1--];
                } else {
                    nums1[i] = nums2[pos2--];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] num1 = {4, 5, 6, 0, 0, 0};
        int[] num2 = {1, 2, 3};
        new MergeSortedArray().merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }
}
