package sort;

import util.zhangpeng.hust.Swap;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class SelectSort {
    /**
     * 选择排序（升序），从左向右，每次排定一个元素
     * 一轮中，每个未排序的每个元素和未排定数组中当前的最小元素相比较，如果前者更小，则替换最小元素，
     * 直至得到最小元素，并将其和未排定数组的最左侧元素交换位置，如此，该位置排定，一轮结束
     * 技巧：为了避免频繁交换元素的位置，排序过程中先找到最小元素的索引，最后只交换一次。
     * @param a 待排序数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void selectSort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n-1; i++) {
            int index = i;                 // 最小元素的索引，仅记住索引，最后再交换元素，可以减少交换元素的次数
            for (int j = i+1; j < n; j++) {
                if (a[j].compareTo(a[index]) < 0) {
                    index = j;
                }
            }
            Swap.swap(a, index, i);        // 一轮只交换一次元素
        }
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int arrayLength = 20;
        Integer[] a = new Integer[arrayLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }

        String format1 = "%-6s: %s\n";
        String format2 = "%-6s: %s\n\n";
        System.out.format(format1, "Before", Arrays.toString(a));
        SelectSort.selectSort(a);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
