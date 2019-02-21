package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字（简单）
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/21
 * @version 8.0
 */
public class FindAllNumbersDisappearedArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] b = new int[nums.length];
        for(int n : nums) {
            b[n-1]++;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < b.length;i++){
            if(b[i] == 0)
                res.add(i+1);
        }
        return res;
    }
}
