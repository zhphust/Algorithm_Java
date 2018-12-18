package sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * 桶排序
 * 桶排序是对一组范围在0~1（不包括1）的浮点型数据进行排序
 * 基本思想是根据数据范围对数据进行分组（装入桶），然后对每个桶的数据进行插入排序，再按顺序输出结果
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class BucketSort {
    /**
     * @param a 待排序数组，每个元素的范围在0~1之间
     */
    public static void sort(double[] a) {
        int R = 10;                                     // 10个桶
        ArrayList<Double>[] b = new ArrayList[R];
        for (int i = 0; i < b.length; i++) {
            b[i] = new ArrayList<>();
        }
        for (int i = 0; i < a.length; i++) {
            b[(int)(a[i]*10)].add(a[i]);                // 对每个元素进行分组
        }
        int index = 0;                                          // 原数组中桶的边界索引
        for (int i = 0; i < R; i++) {
            Double[] bucket = b[i].toArray(new Double[0]);      // 将列表转换成数组
            InsertSort.sort(bucket);                      // 对每个桶进行插入排序
            for (int j = 0; j < bucket.length; j++) {
                a[index+j] = bucket[j];
            }
            index += bucket.length;                            // 更新原数组的索引，即下一个桶插入的位置
        }
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int N = 30;
        double[] a = new double[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(1000) / 1000.0;
        }
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%.3f\n", a[i]);
        }
    }
}
