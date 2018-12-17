package sort;

import util.zhangpeng.hust.Swap;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序，快速排序是一般情况下性能最好的排序算法
 * 快速排序的关键是找到一个切分元素，该元素左侧的子数组都不大于该元素，右侧的子数组都大于该元素
 * 因此该元素是排定的，然后递归地对两个切分后的子数组进行排序，最终使数组有序
 * 快速排序对随机元素构成的数组效果较好，如果数组本身有序或接近有序，性能可能会急剧下降，因此一般排序前需打乱数组。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class QuickSort {
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);             // 找到一个切分
            // int q = hoarePartion(a, p, r);
            quickSort(a, p, q - 1);              // 根据切分对两个子数组排序
            quickSort(a, q + 1, r);
        }
    }

    // 原始求切分位置的方法，从数组两端向中间靠拢
    private static <T extends Comparable<T>> int hoarePartion(T[] a, int p, int r) {
        int i = p - 1;
        int j = r + 1;
        T x = a[p];
        while (true) {
            do {
                j--;
            } while (a[j].compareTo(x) > 0);                  // 此时a[j] <= x
            do {
                i++;
            } while (i <= r && a[i].compareTo(x) <= 0);        // 此时a[i] > x
            if (i < j) {
                Swap.swap(a, i, j);                          // 交换量元素
            } else {
                a[p] = a[j];
                a[j] = x;
                return j;
            }
        }
    }

    private static <T extends Comparable<T>> int partition(T[] a, int p, int r) {
        // 随机化主元
        Random rand = new Random();
        int k = rand.nextInt(r + 1 - p) + p;
        Swap.swap(a, k, r);                     // 将随机选取的主元交换到数组最后一位

        T x = a[r];                             // 主元，待划分元素
        // 排序过程中，有a[p...i] <= x < a[i+1...j-1]
        // a[j...r-1]是待排定元素
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j].compareTo(x) <= 0) {
                i++;
                Swap.swap(a, i, j);
            }
        }
        // 将切分元素插入到合适的位置
        i++;
        a[r] = a[i];
        a[i] = x;
        return i;
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
        quickSort(a);
        System.out.format(format2, "After", Arrays.toString(a));
    }
}
