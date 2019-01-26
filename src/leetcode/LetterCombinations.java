package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合（中等，回溯算法）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0)
            return result;
        String temp = "";
        String[] strings = new String[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)) {
                case '2' :
                    strings[i] = "abc";
                    break;
                case '3' :
                    strings[i] = "def";
                    break;
                case  '4' :
                    strings[i] = "ghi";
                    break;
                case  '5' :
                    strings[i] = "jkl";
                    break;
                case  '6' :
                    strings[i] = "mno";
                    break;
                case  '7' :
                    strings[i] = "pqrs";
                    break;
                case '8' :
                    strings[i] = "tuv";
                    break;
                case '9' :
                    strings[i] = "wxyz";
                    break;
            }
        }
        backTrace(strings, result, temp, 0);
        return result;
    }

    private void backTrace(String[] strings, List<String> result, String temp, int index) {
        if (temp.length() == strings.length) {
            result.add(temp);
            return;
        }
        for (int i = 0; i < strings[index].length(); i++) {
            temp += strings[index].charAt(i);
            backTrace(strings, result, temp, index+1);
            temp = temp.substring(0, temp.length()-1);
            if (index != 0 && i == strings[index].length() - 1)
                temp = temp.substring(0, temp.length()-1);
        }
    }

    public static void main(String[] args) {
        String digits = "234";
        System.out.println(new LetterCombinations().letterCombinations(digits));
    }
}
