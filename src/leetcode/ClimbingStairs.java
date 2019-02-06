package leetcode;

/**
 * 70. 爬楼梯（简单，动态规划）
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/6
 * @version 8.0
 */
public class ClimbingStairs {
    int[] memo;
    public int climbStairs(int n) {
        memo = new int[n + 1];
        return get(n);
    }

    private int get(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        if (memo[n] == 0) {
            memo[n] = get(n - 1) + get(n - 2);
        }
        return memo[n];
    }
}
