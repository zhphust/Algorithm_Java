package string;

import java.util.Arrays;

/**
 * 低位优先字符串排序
 * 适用于各字符串的长度相等且较短的情况
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/16
 * @version 8.0
 */
public class LSD {
    public static void sort(String[] s) {
        int N = s.length;
        String[] aux = new String[N];                 // 辅助数组
        int R = 256;
        int[] count = new int[R];                     // 索引数组

        for (int d = s[0].length()-1; d >= 0; d--) {
            // 初始化索引数组
            for (int i = 0; i < R; i++) {
                count[i] = 0;
            }
            // 计算每个字符的频率
            for (String value : s) {
                count[value.charAt(d)]++;
            }

            // 将频率转化为索引
            for (int i = 1; i < R; i++) {
                count[i] += count[i-1];
            }

            // 根据索引，将字符串放入辅助数组中指定位置
            for (int i = N-1; i >= 0 ; i--) {            // 要采用逆序，保持稳定性
                aux[count[s[i].charAt(d)]-1] = s[i];     // 此处-1是针对aux索引，避免越界
                count[s[i].charAt(d)]--;                 // 此处-1是因为一个元素已经排定，索引需-1
            }

            // 回写
            for (int i = 0; i < N; i++) {
                s[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] s = {"aacbd", "adbca", "bacda", "babcd", "acdbc", "cdacb", "bcaad", "dcabc",
                            "cbcad", "abcda", "dbcad", "cbadc", "12345", "21324", "13214",
                            "15231", "21311"};
        sort(s);
        System.out.println(Arrays.toString(s));
    }
}
