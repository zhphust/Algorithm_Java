package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class ShellSort {
    /**
     * 希尔排序，插入排序的升级版，维护一个间隔序列，序列值从大到小递减，使得元素能够在较大范围内移动
     * 看似多了一层while循环，但由于元素能够大范围地移动造成部分有序，因此总体时间复杂度是低于插入排序的
     * @param a 待排序数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void shellSort(T[] a) {
        int h = 1;                                         // 间隔
        while (h < a.length / 3)
            h = 3 * h + 1;                                // 计算间隔， h = 1, 4, 13, 40, 121, ...
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                T key = a[i];                              // 待插入元素
                int j = i - h;                             // 待比较的元素，间隔为h
                while (j >= 0 && key.compareTo(a[j]) < 0) {
                    a[j+h] = a[j];                       // 移动元素
                    j -= h;
                }
                a[j+h] = key;
            }
            h /= 3;
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
        String format2 = "%-6s: %s\n";
        System.out.format(format1, "Before", Arrays.toString(a));
        shellSort(a);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
