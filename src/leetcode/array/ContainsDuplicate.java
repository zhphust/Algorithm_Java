package leetcode.array;

import java.util.Arrays;

/**
 * 217. 存在重复元素（简单）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/20
 * @version 8.0
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if(nums.length <= 1)
            return false;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
