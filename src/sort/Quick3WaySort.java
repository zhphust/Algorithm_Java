package sort;

import util.zhangpeng.hust.Swap;

import java.util.Arrays;
import java.util.Random;

/**
 * 三向切分快速排序，适用于重复元素较多的情况
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/17
 * @version 8.0
 */
public class Quick3WaySort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        sort(a, 0, a.length-1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo;
        int gt = hi;
        T x = a[lo];
        int i = lo+1;
        // 排序过程中，有a[lo...lt-1] < x == a[lt...i-1] < a[gt+1...hi]
        // a[i...gt]待排定
        while (i <= gt) {
            int cmp = a[i].compareTo(x);
            if (cmp < 0) {
                Swap.swap(a, lt++, i++);
            } else if (cmp > 0) {
                Swap.swap(a, i, gt--);           // 右侧的元素是没有扫描过的，因此调换后i不变
            } else {
                i++;
            }
        }
        // 此时，i = gt+1，所以有：a[lo...lt-1] < x == a[lt...gt] < a[gt+1...hi]
        // 递归排序，a[lt...gt]元素相同，无需再排序
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
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
