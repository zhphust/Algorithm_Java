package leetcode;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class SearchArray {
    public static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        return search(nums, target, lo, hi);
    }

    private static int search(int[] nums, int target, int lo, int hi) {
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {                             // 中位数比目标小的情况下：
            // 低位小于中位（左侧有序），或者低位大于目标（左侧无序），可能的位置一定在右半部分
            if (nums[lo] <= nums[mid] || nums[lo] > target)
                return search(nums, target, mid + 1, hi);
            else
                return search(nums, target, lo, mid - 1);
        } else {                                             // 中位数比目标大的情况下：
            // 高位大于中位（右侧有序），或者高位小于目标（右侧无序）， 可能的位置一定在左半部分
            if (nums[hi] >= nums[mid] || nums[hi] < target)
                return search(nums, target, lo, mid - 1);
            else
                return search(nums, target, mid + 1, hi);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 50, 60, 70, 0, 10, 20, 30, 40};
        System.out.println(search(nums, 70));
        System.out.println();
    }
}
