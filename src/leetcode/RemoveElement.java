package leetcode;

/**
 * 27. 移除元素（简单）
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/23
 * @version 8.0
 */
public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int len = 0;                                    // 记录当前数组的有效长度
        for (int i = 0; i < nums.length; i++) {         // 一次遍历
            if (nums[i] != val) {
                nums[len++] = nums[i];                  // 将当前元素复制到数组的下一有效位置
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        System.out.println(removeElement(nums, 2));
    }
}
