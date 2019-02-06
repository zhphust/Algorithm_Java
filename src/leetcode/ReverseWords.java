package leetcode;

/**
 * 557. 反转字符串中的单词 III（简单）
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/6
 * @version 8.0
 */
public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                temp.insert(0, s.charAt(i));
            } else {
                result.append(temp);
                result.append(" ");
                temp = new StringBuilder();
            }
        }
        result.append(temp);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(new ReverseWords().reverseWords(s));
    }
}
