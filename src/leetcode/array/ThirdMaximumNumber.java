package leetcode.array;

/**
 * 414. 第三大的数（简单）
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/21
 * @version 8.0
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int first = nums[0];
        int second = Integer.MIN_VALUE;
        int i = 1;
        int count = 1;           // 不同数字的个数
        while (i < nums.length && second == Integer.MIN_VALUE) {
            if (nums[i] > first) {
                second = first;
                first = nums[i];
                count++;
            } else if (nums[i] < first) {
                second = nums[i];
                count++;
            }
            i++;
        }
        int third = Integer.MIN_VALUE;
        for (; i < nums.length; i++) {
            if (nums[i] > third) {
                if (nums[i] < second) {
                    third = nums[i];
                    count++;
                } else if (nums[i] > second && nums[i] < first) {
                    third = second;
                    second = nums[i];
                    count++;
                } else if (nums[i] > first) {
                    third = second;
                    second = first;
                    first = nums[i];
                    count++;
                }
            } else if (nums[i] == third) {
                count++;
            }
        }

        if (count < 3) {
            return first;
        } else {
            return third;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, Integer.MIN_VALUE, 4, 5};
        System.out.println(new ThirdMaximumNumber().thirdMax(nums));
    }
}
