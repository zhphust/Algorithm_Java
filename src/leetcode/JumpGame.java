package leetcode;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/25
 * @version 8.0
 */
public class JumpGame {
    public static boolean canJump(int[] nums) {
        // 贪心算法
        int pos = nums.length - 1;
        for (int i = pos; i >= 0; i--) {
            if (i + nums[i] >= pos) {
                pos = i;
            }
        }
        return pos == 0;

        // 动态规划
        /*
        int max = 0;
        for(int i = 0;i < nums.length;i++){
            if(i > max){
                return false;
            }
            max = Math.max(nums[i] + i,max);
        }
        return true;
        */

        /*
        for (int pos = nums.length - 1; pos >= 0; pos--) {
            if (nums[pos] == 0) {
                int i = pos - 1;
                for (; i >= 0; i--) {
                    if (nums[i] + i > pos) {
                        pos = i;
                        break;
                    }
                }
                if (i < 0 && pos != nums.length - 1)
                    return false;
            }
        }
        return true;
        */
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 0, 2};
        System.out.println(canJump(nums));
    }
}
