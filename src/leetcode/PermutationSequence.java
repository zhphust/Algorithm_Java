package leetcode;

/**
 * 60. 第k个排列（中等，回溯算法）
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 给定 n 和 k，返回第 k 个排列。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/5
 * @version 8.0
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder result = new StringBuilder();
        boolean[] marked = new boolean[n+1];
        int[] nums = new int[n+1];
        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            nums[i] = i * nums[i-1];
        }

        find(n, n, k, result, marked, nums);

        return result.toString();
    }

    private void find(int n, int index, int k, StringBuilder result, boolean[] marked, int[] nums) {
        int bit = k / nums[index-1];
        int res = k % nums[index-1];
        if (res == 0) {
            for (int i = 1; i <= n; i++) {
                if (!marked[i] && --bit == 0) {
                    result.append(i);
                    marked[i] = true;
                    break;
                }
            }
            for (int i = n; i >= 1; i--) {
                if (!marked[i]) {
                    result.append(i);
                    marked[i] = true;
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (!marked[i] && bit-- == 0) {
                    result.append(i);
                    marked[i] = true;
                    break;
                }
            }
            find(n, index - 1, res, result, marked, nums);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation(4, 5));
    }

}
