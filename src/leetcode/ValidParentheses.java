package leetcode;

import dataStruct.Stack;

/**
 * 20. 有效的括号（简单）
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/6
 * @version 8.0
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c0 = s.charAt(i);
            if (c0 == '(' || c0 == '[' || c0 == '{') {
                stack.push(c0);
            }
            else if (c0 == ')' || c0 == ']' || c0 == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = stack.pop();
                if (c0 == ')' && c != '(') {
                    return false;
                } else if (c0 == ']' && c != '[') {
                    return false;
                } else if (c0 == '}' && c != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([)]";
        System.out.println(new ValidParentheses().isValid(s));
    }
}
