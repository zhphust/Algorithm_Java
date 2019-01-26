package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(nums);

        perm(list,nums, 0, nums.length - 1);
        return list;
    }

    private void perm(List<List<Integer>> list, int[] nums, int L, int R){
        if(L==R){   //只有一位的时候，直接set
            list.add(set(nums));
        }else{
            int i=L;
            while (i <= R) {
                int tmp=nums[i];
                int j=i;
                while(j<nums.length&&nums[j]==tmp){
                    j++;
                }
                swap(nums,L, i);//将第i个数放到第一位固定，然后去排后面的，这样就不用使用hash表来保存使用了多少数字，以及哪些数字没有使用了
                int[] a2=nums.clone();
                perm(list,a2, L + 1, R);
                i=j;

            }
        }
        return;
    }

    private List<Integer> set(int[] nums){
        List<Integer> list=new ArrayList<>();
        for(int i  =0; i < nums.length; i++){
            list.add(nums[i]);
        }
        return list;
    }
    private void swap(int[] nums,int L,int R){
        int tmp=nums[L];
        nums[L]=nums[R];
        nums[R]=tmp;
    }
}
