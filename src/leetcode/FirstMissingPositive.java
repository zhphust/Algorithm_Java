package leetcode;

/**
 * 41. 缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int max = 0;                 // find max element in nums
        for (int i : nums) {
            if (i > max)
                max = i;
        }

        int[] arr = new int[max + 1];  // build a array, store all the positive element by ascend sort

        for (int num : nums) {
            if (num > 0)
                arr[num] = num;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != i)          // find the first index that arr[index] != index, this is what we are looking for
                return i;
        }
        return max + 1;              // the element in arr is [1,...,max], so next is max+1
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, -1, 7, 1};
        System.out.println(firstMissingPositive(nums));
    }
}
