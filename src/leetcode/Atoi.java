package leetcode;

/**
 * 8. 字符串转换整数 (atoi)
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/25
 * @version 8.0
 */
public class Atoi {
    public static int myAtoi(String str) {
        int max_int = Integer.MAX_VALUE;
        int min_int = Integer.MIN_VALUE;
        long num = 0;
        int i = 0;
        int flagIndex = 0;
        while(i < str.length() && str.charAt(i) == ' ') {  // 去除空字符
            i++;
        }
        if(i == str.length()){
            return 0;
        }
        if(str.charAt(i) == '+' || str.charAt(i) == '-'){ // 找出正负号
            flagIndex = i;
            i++;
            while(i < str.length() && str.charAt(i) == '0'){  // 去除正负号后的0
                i++;
            }
        } else if(str.charAt(i) > '0' || str.charAt(i) < '0'){   // 非法
            return 0;
        }
        while(i < str.length() && str.charAt(i) == '0'){      // 去除起始处的0
            i++;
        }
        while(i < str.length()){
            if(str.charAt(i) <= '9' && str.charAt(i) >= '0'){
                num = num * 10 + (str.charAt(i++) - '0');
                if(num > max_int)
                    break;
            } else
                break;
        }
        if(str.charAt(flagIndex) == '-'){
            return -num < min_int ? min_int : (int)(-num);
        }
        return num > max_int ? max_int : (int) num;
    }

    public static void main(String[] args) {
        String s = "  -00065as";
        System.out.println(myAtoi(s));
    }
}
