package leetcode;

/**
 * 29. 两数相除
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/24
 * @version 8.0
 */
public class Divided {
    public static int divide(int dividend, int divisor) {
        int max_int = Integer.MAX_VALUE;
        int min_int = Integer.MIN_VALUE;
        boolean flag = false;
        int result = 0;
        if (divisor == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        } else if (divisor == -1){
            if (dividend == min_int)
                return max_int;
            else
                return -dividend;
        }

        // 负数表示范围大一些，将数都由正转负
        if (dividend >= 0 && divisor < 0) {
            flag = true;
            dividend = ~dividend + 1;
        } else if (dividend <= 0 && divisor > 0) {
            flag = true;
            divisor = ~divisor + 1;
        } else if (dividend >= 0 && divisor > 0) {
            dividend= ~dividend + 1;
            divisor = ~divisor + 1;
        }

        while (divisor >= dividend) {         // 注意比较的方向
            int temp = 1;
            if (divisor == dividend) {
                result += 1;
                break;
            }
            long div = divisor;
            long diven = dividend;
            while (div > diven) {
                temp <<= 1;
                div <<= 1;
            }
            div >>= 1;
            temp >>>= 1;
            result += temp;
            dividend -= div;
        }

        if (flag) {
            result = ~result;
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(divide(2147483647, 3));
    }
}
