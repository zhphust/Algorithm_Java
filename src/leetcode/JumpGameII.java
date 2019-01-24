package leetcode;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class JumpGameII {
    public static int jump(int[] nums) {
        // the length is 1, so no need to jump
        if (nums.length == 1)
            return 0;
        int pos = 0;                                       // current index position
        int steps = 0;                                     // steps have jumped
        while (pos < nums.length) {
            int range = nums[pos];
            if (pos + range >= nums.length - 1)            // can arrive at the end by one step
                return steps + 1;

            // at least two steps needed
            int maxRange = nums[pos] + nums[pos + range];   // how long the distance can be by two step
            int nextPos = pos + range;                      // next position index

            // greedy algorithm, select the most distance by two steps
            for (int j = pos + 1; j < pos + range; j++) {
                int tempRange = j - pos + nums[j];
                if (tempRange > maxRange) {
                    maxRange = tempRange;
                    nextPos = j;
                }
            }
            pos = nextPos;     // update position
            steps += 1;
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
