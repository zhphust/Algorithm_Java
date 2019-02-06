package leetcode;

import java.util.Arrays;

/**
 * 344. 反转字符串（简单）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/6
 * @version 8.0
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            swap(s, i, n - 1 - i);
        }
    }

    private void swap(char[] s, int l, int r) {
        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        new ReverseString().reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
