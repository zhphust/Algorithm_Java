package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序（升序），先将数组拆分成两个子数组，然后对子数组排序，再将两个子数组合并到一个数组中。
 * 自上而下：递归拆分数组
 * 自下而上：先两两合并，再加倍合并，直到整个数组有序
 * 归并排序可以附带计算一个数组中的逆序对(数组中后面的元素小于前面的元素)数目
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class MergeSort {
    private static int reverseOrderSum = 0;     // 计算数组中的逆序对个数

    /**
     * @param a 待排序数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        reverseOrderSum = 0;
        sort(a, 0, a.length-1);        // 排序，首尾包含
    }

    /**
     * 递归分解过程
     * @param a 待排序数组
     * @param p 数组的左边界（包含）
     * @param r 数组的右边界（包含）
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    private static <T extends Comparable<T>> void sort(T[] a, int p, int r) {
        /*
         * 改进措施：当数组较小时，直接采用插入排序，可以减少递归深度，加快排序速度
         * 注意：当采用插入排序后，就不能正确计算逆序对的数目了
         */
        if (r - p < 5) {
            InsertSort.sort(a, p, r);
        } else {
            // 分解数组
            int q = (p + r) / 2;
            // 排序子数组
            sort(a, p, q);
            sort(a, q+1, r);
            // 合并子数组
            merge(a, p, q, r);
        }
    }

    /**
     * 合并过程
     * @param a 待排序数组
     * @param p 数组左边界（包含）
     * @param q 两个子数组的分隔边界（包含在左子数组中）
     * @param r 数组右边界（包含）
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] a, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        T[] left = (T[]) new Comparable[n1];                // 辅助数组
        System.arraycopy(a, p, left, 0, n1);
        T[] right = (T[]) new Comparable[n2];               // 辅助数组
        System.arraycopy(a, q+1, right, 0, n2);
        int k = p;                                          // 待排序元素在原数组的起始位置
        int i = 0;
        int j = 0;
        while (k <= r) {
            if (i == n1) {                                 // 左侧辅助数组已用尽
                while (j < n2) {
                    a[k++] = right[j++];
                }
                break;
            }
            if (j == n2) {                                 // 右侧辅助数组已用尽
                while (i < n1) {
                    a[k++] = left[i++];
                }
                break;
            }
            if (left[i].compareTo(right[j]) <= 0) {
                a[k++] = left[i++];
            } else {
                /*
                 * 由于数组有序，
                 * 当前right数组元素小于left数组元素，则该right元素小于left数组从索引i开始的所有元素
                 */
                reverseOrderSum += n1 - i;
                a[k++] = right[j++];
            }
        }
    }

    /**
     * 自底向上的归并排序，非递归
     * @param a 待排序数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     */
    public static <T extends Comparable<T>> void sort2(T[] a) {
        reverseOrderSum = 0;
        for (int sz = 1; sz < a.length; sz = 2*sz) {             // 每个数组的长度为sz，每次将子数组的长度加倍
            for (int j = 0; j < a.length-sz; j += 2*sz) {        // 每一对和下一对待合并数组间隔为2*sz
                merge(a, j, j+sz-1, Math.min(a.length-1, j+2*sz-1));
            }
        }
    }

    /**
     * 计算逆序对的个数，该结果可以说是归并排序的副产品
     * @param a 待排序数组
     * @param <T> 数组元素类型，继承自Comparable<T>
     * @return 逆序对的个数
     */
    public static <T extends Comparable<T>> int getReverseOrderSum(T[] a) {
        /*
         * 在此类中，递归方式在数组小于一定规模后采用了插入排序，所以得不到正确的结果，
         * 如果想得到正确结果，去掉插入排序的优化方式；
         * 本方法中采用了循环方式，可以得到正确结果
         */
        sort2(a);
        return reverseOrderSum;
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int arrayLength = 10;
        Integer[] a = new Integer[arrayLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }

        String format1 = "%-6s: %s\n";
        String format2 = "%-6s: %s\n";
        System.out.format(format1, "Before", Arrays.toString(a));
        int n = getReverseOrderSum(a);
        System.out.format(format2, "After", Arrays.toString(a));
        System.out.println("逆序对的数量是：" + n);
    }
}
