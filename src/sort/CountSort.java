package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序，计数排序统计数组中每个元素出现的频率，以此确定每个元素在数组中的范围边界
 * 虽然没有相互间比较，但是数组元素本身隐含了比较
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class CountSort {
    /**
     *
     * @param a 待排序数组
     * @param R 基数，所有数组元素范围为 0~R-1
     */
    public static void sort(int[] a, int R) {
        int N = a.length;
        int[] aux = new int[N];                       // 辅助数组
        int[] count = new int[R];                     // 频率计算数组
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < a.length; i++) {
            count[a[i]]++;                       // 统计每个元素出现的频率
        }
        for (int i = 1; i < R; i++) {
            count[i] += count[i-1];              // 根据频率计算不同元素的索引边界
        }
        for (int i = N - 1; i >= 0; i--) {
            // 采用逆序的原因是保证排序是稳定的，
            // 关键字相同的元素（带卫星数据）在a中出现先后顺序和排序后的顺序一致
            aux[count[a[i]]-1] = a[i];          // 将元素写入辅助数组
            count[a[i]]--;                      // 修改位置索引
        }
        System.arraycopy(aux, 0, a, 0, N);     // 将元素回写至原数组
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int arrayLength = 30;
        int[] a = new int[arrayLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(30);
        }

        String format1 = "%-6s: %s\n";
        String format2 = "%-6s: %s\n";
        System.out.format(format1, "Before", Arrays.toString(a));
        sort(a, 50);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
