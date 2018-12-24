package leetcode;

/**
 * 6. Z 字形变换
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/24
 * @version 8.0
 */
public class Zigzag {
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder result = new StringBuilder();
        int N = s.length();
        // 处理第一行
        for (int i = 0; i < N; i += 2 * numRows - 2) {
            result.append(s.charAt(i));
        }
        // 处理第2~numRows-1行
        for (int k = 1; k < numRows - 1; k++) {
            for (int i = k; i < N; i += 2 * numRows - 2) {
                result.append(s.charAt(i));
                int delta = i + (numRows - k) * 2 - 2;   // 计算Z形斜边与直边当前行之间索引的关系
                if (delta < N) {
                    result.append(s.charAt(delta));
                }
            }
        }
        // 处理第numRows行
        for (int i = numRows - 1; i < N; i += 2 * numRows - 2) {
            result.append(s.charAt(i));
        }

        return result.toString();

    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));
    }
}
