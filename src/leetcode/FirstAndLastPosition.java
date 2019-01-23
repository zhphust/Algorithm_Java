package leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置（中等）
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class FirstAndLastPosition {
    public static int[] searchRange(int[] nums, int target) {
        // 思路：先用二分查找算法找到target的索引位置，然后向两侧查找边界
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target)
                break;
            if (nums[mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        if (lo > hi)
            return new int[]{-1, -1};
        int m = mid;
        int n = mid;
        while (m >= 0) {
            if (nums[m] != target)
                break;
            else
                m--;
        }
        while (n < nums.length) {
            if (nums[n] != target)
                break;
            else
                n++;
        }
        return new int[]{m+1, n-1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] result = searchRange(nums, 8);
        for (int i : result)
            System.out.print(i + " ");
    }
}
