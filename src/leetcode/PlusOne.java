package leetcode;

import java.util.Arrays;

/**
 * 66. 加一（简单）
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/1/26
 * @version 8.0
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n - 1] += 1;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 10) {
                digits[i] = 0;
                if (i == 0) {
                    int[] temp = new int[n + 1];
                    temp[0] = 1;
                    for (int k = 1; k < temp.length; k++) {
                        temp[k] = 0;
                    }
                    return temp;
                } else {
                    digits[i-1] += 1;
                }
            } else {
                break;
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] digit = {9, 9, 9, 9};
        System.out.println(Arrays.toString(new PlusOne().plusOne(digit)));
    }
}
