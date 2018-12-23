package leetcode;

/**
 * 5. 给定一个字符串 s，找到 s 中最长的回文子串。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/23
 * @version 8.0
 */
public class Palindrome {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";

        // 数组用来记录从索引i到j的最长回文的长度
        int[][] len = new int[s.length()][s.length()];
        // 长度为1
        for (int i = 0; i < len.length; i++) {
            len[i][i] = 1;
        }
        // 长度为2
        for (int i = 0; i < len.length - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                len[i][i+1] = 2;
            } else {
                len[i][i+1] = 1;
            }
        }
        // 长度大于等于3
        for (int l = 3; l <= s.length(); l++) {
            for (int i = 0; i <= s.length() - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && len[i + 1][j - 1] == l - 2) {  // 首尾字符串相等且去掉首尾已经是回文
                    len[i][j] = l;
                } else {
                    len[i][j] = Math.max(len[i][j-1], len[i+1][j]);
                }
            }
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (len[i][j] == j - i + 1) {
                return s.substring(i, j + 1);
            } else if (len[i][j] == len[i][j - 1]) {
                j--;
            } else {
                i++;
            }
        }
        return s.substring(0, 1);
    }

    public static void main(String[] args) {
        String s = "babad";
        Palindrome p = new Palindrome();
        System.out.println(p.longestPalindrome(s));
    }
}
