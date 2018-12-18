package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序，时间复杂度为O(N^2)，适用于小规模数组，常作为其他排序算法的子算法
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class InsertSort {
    /**
     * 插入排序（升序），从左向右，每次从未排定数组序列中拿出一个待排定元素，与该元素之前的已排定元素做比较，
     * 若该元素小于已当前比较的已排定元素，则将已排定元素向右移动一个位置，直至待排定元素不小于与之比较的元素，
     * 然后将待排定元素插入已排定元素的空余位置，一轮结束
     * @param a 待排定数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        sort(a, 0, a.length-1);
    }

    /**
     * 对数组的某一区间进行插入排序
     * @param a 待排序数组
     * @param lo 排序的起始索引（包括）
     * @param hi 排序的终止索引（包括）
     */
    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= 0 && key.compareTo(a[j]) < 0) {
                a[j+1] = a[j];
                j--;
            }

            a[j+1] = key;          // 将元素插入到合适的位置
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
        sort(a);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
