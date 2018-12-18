package sort;

import java.util.Random;

/**
 * 基数排序
 * 长度相同的（字符）串，采用计数排序方法，按照低位优先的原则，对串中每一位进行一次排序
 * 本例也实现了对int型数组的排序，因为int在Java中占有32位，可以认为是长度相等，因此可以利用此方法排序
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class RadixSort {
    /**
     * 对字符串数组进行排序
     * @param a 待排序字符串，字符串的长度应相等
     */
    public static void sort(String[] a) {
        int N = a.length;
        int R = 256;                               // 基数, 基于ASCII码
        String[] aux = new String[N];
        int[] count = new int[R];
        int d = a[0].length();
        for (int k = d-1; k >= 0; k--) {
            // 初始化
            for (int i = 0; i < R; i++) {
                count[i] = 0;
            }
            // 计算频率
            for (String s : a) {
                count[s.charAt(k)]++;
            }
            // 将频率转化为索引
            for (int i = 1; i < R; i++) {
                count[i] += count[i-1];
            }
            // 将数组元素写入辅助数组
            for (int i = N-1; i >= 0; i--) {
                aux[count[a[i].charAt(k)]-1] = a[i];
                count[a[i].charAt(k)]--;
            }
            // 回写
            System.arraycopy(aux, 0, a, 0, N);
        }
    }

    /**
     * 对int型数组进行排序，因为int型整数占有的比特位是一样的，因此可以按照此方法排序
     * @param a 待排序数组
     */
    public static void sort(int[] a) {
        int R = 2;                             // 2进制数，R = 2
        int N = a.length;
        int d = 32;                             // Java中，int型是32位的
        for (int k = d; k >= 1; k--) {          // 低位优先
            int[] aux = new int[N];
            int[] count = new int[R];
            for (int i = 0; i < R; i++) {
                count[i] = 0;
            }
            for (int i = 0; i < a.length; i++) {
                count[getInt(a[i], k)]++;
            }
            for (int i = 1; i < R; i++) {
                count[i] += count[i-1];
            }
            for (int i = N - 1; i >= 0; i--) {
                aux[count[getInt(a[i], k)]-1] = a[i];
                count[getInt(a[i], k)]--;
            }
            System.arraycopy(aux, 0, a, 0, N);
        }
    }

    private static int getInt(int i, int k) {
        return  (i >> (32 - k)) & 1;
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int arrayLength = 50;
        int[] a = new int[arrayLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(10000);
        }
        sort(a);
        for (int c : a) {
            System.out.printf("%04d\n", c);
        }
        System.out.println("=====");

        String s = "COW DOG SEA RUG ROW MOB BOX TAB BAR EAR DIG BIG TEA NOW FOX " +
                "ACX BDE BEX ARV CRD DWV SSA SAB HFR KTA";
        String[] ss = s.split(" ");
        sort(ss);
        for (String sss : ss) {
            System.out.println(sss);
        }
    }
}
