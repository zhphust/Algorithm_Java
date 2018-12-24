package leetcode;

/**
 * 3. 无重复字符的最长子串
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/23
 * @version 8.0
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() < 1){
            return 0;
        }
        int maxLen = 1;
        int tempLen = 1;
        int i = 0;
        while (i + tempLen < s.length()) {
            char cmpChar = s.charAt(i + tempLen);
            int j = 0;
            while (j < i + tempLen){
                if(cmpChar == s.charAt(j)){
                    tempLen = tempLen - j;
                    i = j + 1;
                    cmpChar = s.charAt(i + tempLen);
                    j = i;
                    continue;
                }
                j++;
            }
            tempLen++;
            if (tempLen > maxLen) {
                maxLen = tempLen;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcdef";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
