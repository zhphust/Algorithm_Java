package leetcode;

/**
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/24
 * @version 8.0
 */
public class TrappingWater {
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0)
            return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;
        right[n-1] = 0;

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i-1], height[i-1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], height[i+1]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            int level = Math.min(left[i], right[i]);
            water += Math.max(0, level - height[i]);
        }
        return water;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(nums));
    }
}
