package leetcode;

/**
 * 121. 买卖股票的最佳时机（动态规划）
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/25
 * @version 8.0
 */
public class SellStock {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int p : prices) {
            if (p < minPrice)
                minPrice = p;
            else if (p - minPrice > maxProfit)
                maxProfit = p - minPrice;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
