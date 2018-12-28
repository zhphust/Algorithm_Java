package graph;

/**
 * 无权重有向图和无向图的通用接口
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public interface UnedgedGraph {
    int V();                // 顶点的个数
    int E();                // 边的个数
    Iterable<Integer> adjacent(int v);        // 顶点的邻接表
    void addEdge(int u, int v);               // 添加边
}
