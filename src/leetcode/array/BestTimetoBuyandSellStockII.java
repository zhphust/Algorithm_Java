package leetcode.array;

/**
 * 122. 买卖股票的最佳时机 II（简单，贪心算法）
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/19
 * @version 8.0
 */
public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int sum = 0;
        int lowPrice = prices[0];
        int highPrice = prices[0];
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            lowPrice = prices[i];
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            highPrice = prices[i];
            sum += highPrice - lowPrice;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(new BestTimetoBuyandSellStockII().maxProfit(prices));
    }
}
