package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码（中等）
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2019/2/5
 * @version 8.0
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        /**
         关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
         如 n = 3:
         G(0) = 000,
         G(1) = 1 ^ 0 = 001 ^ 000 = 001
         G(2) = 2 ^ 1 = 010 ^ 001 = 011
         G(3) = 3 ^ 1 = 011 ^ 001 = 010
         G(4) = 4 ^ 2 = 100 ^ 010 = 110
         G(5) = 5 ^ 2 = 101 ^ 010 = 111
         G(6) = 6 ^ 3 = 110 ^ 011 = 101
         G(7) = 7 ^ 3 = 111 ^ 011 = 100
         **/
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 1<<n; ++i)
            ret.add(i ^ i>>1);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(3));
    }
}
