package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II（简单）
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/20
 * @version 8.0
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        /*
        if (nums.length <= 1)
            return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j - i <= k; j++) {
                if (nums[j] == nums[i])
                    return true;
            }
        }
        return false;
        */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                int index = map.get(nums[i]);
                if (i - index <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}
