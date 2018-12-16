package string;

import java.util.Random;

/**
 * 三向字符串快速排序，通用排序算法
 * 将高位优先和快速排序结合起来
 * 特别适合于含有较长公共前缀的字符串
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/16
 * @version 8.0
 */

public class Quick3String {
    public static void sort(String[] s) {
        sort(s, 0, s.length-1, 0);
    }

    private static void sort(String[] s, int lo, int hi, int d) {
        if (hi <= lo)
            return;
        int v = charAt(s[lo], d);

        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(s[i], d);
            if (t < v)
                exch(s, lt++, i++);
            else if (t > v)
                exch(s, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        sort(s, lo, lt-1, d);
        if (v >= 0)                         // v = -1时，所有字符已比较，无需再递归排序
            sort(s, lt, gt, d+1);
        sort(s, gt+1, hi, d);
    }

    private static int charAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    private static void exch(String[] s, int i, int j) {
        String temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        String[] base = {"a", "b", "c", "d", "ac", "ad", "bd", "ca", "adc", "bca", "bcd", "cda", "dac",
                "adcb", "badc", "bbdc", "cabd", "cbdc", "dadc", "ddcb"};
        int N = base.length;
        int M = 50;
        String[] s = new String[M];
        for (int i = 0; i < M; i++) {
            String a = base[rand.nextInt(N)];
            String b = base[rand.nextInt(N)];
            s[i] = a + b;
        }
        sort(s);
        for (String ss : s) {
            System.out.println(ss);
        }
    }
}
