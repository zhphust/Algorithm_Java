package dataStruct;

import java.io.*;

/**
 * 采用加权quick-union方式，将分量进行连通
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class UF2 {
    private int[] id;                 // 数组中存储的是该分量的父结点
    private int[] counts;             // 以每个分量为根结点的分量个数
    private int count;                // 连通分量的个数

    public UF2(int n) {
        id = new int[n];
        counts = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;                // 每个连通分量的父结点都是自己，即自己为根结点
            counts[i] = 1;            // 每个连通分量的个数初始化为1
        }
        count = n;
    }

    /**
     * 得到该分量的根结点分量
     * @param p 待获取的分量
     * @return 该结点的根结点的分量
     */
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * 将两个分量连通，该方法只需要改变其中一个分量的值，将其链接到另一分量上
     * 不用遍历整个数组，因此称为quick-union
     * @param p 一个分量
     * @param q 另一个分量
     */
    public void union(int p, int q) {
        int p0 = find(p);
        int q0 = find(q);
        if (p0 != q0) {
            // 采用加权连通，将分量数量较少的分支连接到数量较大的分支的根结点上
            if (counts[p0] >= counts[q0]) {
                id[q0] = p0;                       // 将根结点的分量值变为另一个根结点的分量值
                counts[p0] += counts[q0];          // 分量个数改变
            } else {
                id[p0] = q0;
                counts[q0] += counts[p0];
            }
            count--;
        }
    }

    /**
     * 判断两份量是否连通
     * @param p 一个分量
     * @param q 另一个分量
     * @return 布尔值
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 得到连通分量的个数
     * @return 连通分量的个数
     */
    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/source/mediumUF.txt"));
        String s = reader.readLine();
        UF2 uf = new UF2(Integer.parseInt(s));
        while ((s = reader.readLine()) != null) {
            String[] i = s.split(" ");
            int p = Integer.parseInt(i[0]);
            int q = Integer.parseInt(i[1]);
            uf.union(p, q);
        }
        System.out.println(uf.count() + " components.");
    }
}
