package sort;

import util.zhangpeng.hust.Swap;
import java.util.Random;

/**
 * 顺序统计量，查找一个数组中某个排名的元素（不经过完全排序）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/18
 * @version 8.0
 */
public class OrderStatistics {
    // 返回最小元素，只需一次遍历数组
    public static <T extends Comparable<T>> T minimum(T[] a) {
        T min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(min) < 0) {
                min = a[i];
            }
        }
        return min;
    }

    // 同时返回最小和最大元素，只需3N/2次比较
    public static <T extends Comparable<T>> String minAndMax(T[] a) {
        int N = a.length;
        T min, max;
        if (N % 2 == 0) {                                  // 偶数个元素
            if (a[0].compareTo(a[1]) <= 0) {
                min = a[0];
                max = a[1];
            } else {
                min = a[1];
                max = a[0];
            }
            for (int i = 2; i < N; i = i+2) {
                T minTemp, maxTemp;
                if (a[i].compareTo(a[i+1]) <= 0) {          // 两元素先自己比较
                    minTemp = a[i];
                    maxTemp = a[i+1];
                } else {
                    minTemp = a[i+1];
                    maxTemp = a[i];
                }
                // 比较后的元素分别和已知的最小元素和最大元素相比较
                if (minTemp.compareTo(min) < 0) {
                    min = minTemp;
                }
                if (maxTemp.compareTo(max) > 0) {
                    max = maxTemp;
                }
            }
        } else {                                               // 奇数个元素
            min = a[0];
            max = a[0];
            for (int i = 1; i < N; i = i+2) {
                T minTemp, maxTemp;
                if (a[i].compareTo(a[i+1]) <= 0) {
                    minTemp = a[i];
                    maxTemp = a[i+1];
                } else {
                    minTemp = a[i+1];
                    maxTemp = a[i];
                }
                if (minTemp.compareTo(min) < 0) {
                    min = minTemp;
                }
                if (maxTemp.compareTo(max) > 0) {
                    max = maxTemp;
                }
            }
        }
        return "minKey: " + min + ", max: " + max;
    }

    /**
     * 利用期望时间为O(N)的算法获得顺序统计量
     * 基本思想：利用随机化快速排序的切分
     * @param i 第i个顺序统计量，和数组索引差1
     */
    public static <T extends Comparable<T>> T randomSelect(T[] a, int i) {
        return randomSelect(a, 0, a.length - 1, i);
    }

    private static <T extends Comparable<T>> T randomSelect(T[] a, int p, int r, int i) {
        if (p == r)
            return a[p];
        int q = randomPartition(a, p, r);
        int k = q - p + 1;                        // 切分左侧数组的元素个数+1
        if (i == k) {
            return a[q];
        }
        if (i < k) {
            return randomSelect(a, p, q-1, i);       // 递归，在左侧子数组寻找
        } else {
            return randomSelect(a, q+1, r, i-k);  // 递归，在右侧子数组寻找
        }
    }

    private static <T extends Comparable<T>> int randomPartition(T[] a, int p, int r) {
        // 随机化主元
        Random rand = new Random();
        int k = rand.nextInt(r+1-p) + p;
        Swap.swap(a, k, r);

        T x = a[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j].compareTo(x) <= 0) {
                i++;
                Swap.swap(a, i, j);
            }
        }
        i++;
        Swap.swap(a, i, r);
        return i;
    }

    /*
     * 最坏情况为O(N)时间的顺序统计量
     */
    public static <T extends Comparable<T>> T select(T[] a, int i) {
        return select(a, 0, a.length - 1, i);
    }

    private static <T extends Comparable<T>> T select(T[] a, int p, int r, int i) {
        T pivot = selectMedium(a, p, r);                                        // 得到中位数，位置在a[p]
        int q = randomPartition(a, p, r, pivot);                                // 得到根据中位数为切分元素的划分点
        int k = q - p + 1;
        if (k == i)
            return a[q];
        if (i < k)
            return select(a, p, q-1, i);
        else return select(a, q+1, r, i-k);
    }

    private static <T extends Comparable<T>> T selectMedium(T[] a, int p, int r) {
        if (p == r)
            return a[p];                                             // 返回中位数，数组的第一个元素
        int group = (r - p) / 5 + 1;                                 // 计算组的个数，每组5个元素，最后一组可能少于5个
        for (int j = 0; j < group; j++) {
            int left = p + j*5;                                      // 计算每组的左边界
            int right = ((p+j*5+4) < r) ? (p+j*5+4) : r;             // 计算每组的右边界
            InsertSort.sort(a, left, right);                         // 插入排序对每组排序
            Swap.swap(a, p+j, (left + right)/2);                 // 将第j组的中位数交换到数组a的第j个位置上去
        }
        return selectMedium(a, p, p + group - 1);                  // 递归选择中位数
    }

    private static <T extends Comparable<T>> int randomPartition(T[] a, int p, int r, T pivot) {
        Swap.swap(a, p, r);                 // 将中位数(位置就是数组的第一个元素)与数组a最后一个元素交换，作为主元
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j].compareTo(pivot) <= 0) {
                i++;
                Swap.swap(a, i, j);
            }
        }
        i++;
        Swap.swap(a, i, r);
        return i;
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int N = 233;
        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(2000);
        }
        System.out.println("minKey: " + minimum(a));
        System.out.println(minAndMax(a));
        Integer[] b = new Integer[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Integer[] c = new Integer[a.length];
        System.arraycopy(a, 0, c, 0, a.length);
        QuickSort.sort(a);
        System.out.println("完全排序后得到的顺序统计量：" + a[127]);
        int re1 = randomSelect(b, 128);
        System.out.println("  一种方式得到的顺序统计量：" + re1);
        int re = select(c, 128);
        System.out.println("另一种方式得到的顺序统计量：" + re);

    }
}