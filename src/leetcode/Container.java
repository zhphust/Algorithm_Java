package leetcode;

/**
 * 11. 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/22
 * @version 8.0
 */
public class Container {
    public static int maxArea(int[] height) {
        /*
          思路：保存两个指针，起始时位于首位，得到面积，
          然后向中间移动高度较短的指针，这样低会减少，但高度可能会增高，面积可能变大
         */
        int max = 0;
        int temp;
        for (int i = 0, j = height.length - 1; i < j;) {
            if (height[i] <= height[j]) {
                temp = (j - i) * height[i];
                if (temp > max)
                    max = temp;
                i++;
            } else {
                temp = (j - i) * height[j];
                if (temp > max)
                    max = temp;
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

}
