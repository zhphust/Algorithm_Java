package sort;

import util.zhangpeng.hust.Swap;
import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class BubbleSort {
    /**
     * 冒泡排序（升序）
     * 思想：从数组一端，相邻两个元素两两比较，若左边大，则交换元素，
     * 如此下去，一直到最右边，则最大的元素便在数组最右侧，
     * 一轮结束，排定一个元素，然后下一轮
     * 时间复杂度：O(N^2)
     * @param a 待排定数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(a[j+1]) > 0) {
                    Swap.swap(a, j, j+1);
                }
            }
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
        BubbleSort.bubbleSort(a);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
