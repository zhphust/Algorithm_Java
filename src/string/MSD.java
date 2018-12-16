package string;

import java.util.Random;

/**
 * 高位优先字符串排序
 * 各字符串长度可以不等，
 * 字符串越随机，性能越好
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/16
 * @version 8.0
 */
public class MSD {
    private static int R = 256;                           // 基数
    private static int M = 5;                             // 小数组的大小，小数组采用插入排序
    private static String[] aux;

    public static void sort(String[] s) {

        int N = s.length;
        aux = new String[N];
        sort(s, 0, N-1, 0);
    }

    private static void sort(String[] s, int lo, int hi, int d) {
        if (hi <= lo + M) {                                         // 插入排序
            for (int i = lo + 1; i <= hi; i++) {
                String key = s[i];
                int j = i - 1;
                while (j >= 0 && key.compareTo(s[j]) < 0) {
                    s[j+1] = s[j];
                    j--;
                }
                s[j+1] = key;
            }
            return;
        }

        int[] count = new int[R+1];                             // 给charAt()返回值为-1留一个位置
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        // 计算频率
        for (int i = lo; i <= hi; i++) {
                count[charAt(s[i], d)+1]++;
        }

        // 将频率转化为索引
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        // 根据索引写值
        for (int i = hi; i >= lo; i--) {
            aux[count[charAt(s[i], d)+1]-1] = s[i];           // 辅助数组索引是从0开始的
            count[charAt(s[i], d)+1]--;
        }

        // 回写
        for (int i = lo; i <= hi; i++) {
            s[i] = aux[i-lo];                                // 注意辅助数组和原数组索引之间的关系
        }

        // 递归
        for (int i = 0; i < R; i++) {
            // 分块递归
            // count数组元素此时表示的是各不同字符开始的索引
            sort(s, lo + count[i], lo + count[i+1] - 1, d+1);
        }
        sort(s, lo + count[R], hi, d+1);
    }

    private static int charAt(String s, int d) {
        if (d == s.length())
            return -1;
        else
            return s.charAt(d);
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
