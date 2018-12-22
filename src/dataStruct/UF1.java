package dataStruct;

/**
 * 本例通过quick-find用来实现动态连通性
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/28
 * @version 8.0
 */
public class UF1 {
    private int[] id;            // 存储不同连通分量的标识，相连通的结点i, j，该数组中相应索引下的值是相同的
    private int count;           // 连通分量的个数

    public UF1(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        count = n;
    }

    /**
     * 将一个分量下的所有标识变为另一个，从而将不同的连通分量相连
     * @param p 待连通的分量
     * @param q 待连通的另一分量
     */
    public void union(int p, int q) {
        int p0 = find(p);             // 获得分量的标识
        int q0 = find(q);
        if (p0 != q0) {
            // 遍历数组，将一个分量下的所有标识改为另一个
            for (int i = 0; i < id.length; i++) {
                if (id[i] == p0) {
                    id[i] = q0;
                }
            }
            count--;
        }
    }

    /**
     * 获得一个分量的标识，只需返回数组值，所以是quick-find方式
     * @param p 要获得标识的分量
     * @return  分量的标识
     */
    public int find(int p) {
        return id[p];
    }

    /**
     * 判断两个分量是否相连
     * @param p 一个分量
     * @param q 另一个分量
     * @return 判断结果，布尔值
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 获得连通分量的个数
     * @return 返回连通分量的个数
     */
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        UF1 uf = new UF1(10);
        uf.union(3, 6);
        uf.union(2, 7);
        uf.union(2, 6);
        uf.union(0, 8);
        uf.union(4, 5);
        uf.union(9, 8);
        uf.union(1, 5);
        System.out.println(uf.count());
    }
}
