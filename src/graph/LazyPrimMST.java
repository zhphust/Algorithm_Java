package graph;

import dataStruct.MinPriorityQueue;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 延时最小生成树，基于贪心算法
 * 切分定理：对于任意的切分，横切边中的权重最小者必然属于最小生成树
 * Prim算法的每一步，都会为一棵生长着的最小生成树添加一条边，直到有V-1条边，
 * 每一次都是将连接树顶点和非树顶点的边中权重最小的边加入到树中。
 * 实现采用的数据结构是最小优先队列。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class LazyPrimMST {
    private boolean[] marked;                       // 树中的顶点会被标记
    private Set<Edge> mst;                          // 最小生成树中的边集合
    private MinPriorityQueue<Edge> crossEdges;      // 横切边构成的最小优先队列

    public LazyPrimMST(EdgeUnGraph G) {
        marked = new boolean[G.V()];
        mst = new HashSet<>();
        crossEdges = new MinPriorityQueue<>();

        addCrossEdges(G, 0);                    // 从顶点0开始，维护一个最小优先队列
        while (!crossEdges.isEmpty() && (mst.size() < G.V() - 1)) {
            Edge e = crossEdges.delMin();
            int v = e.onePoint();
            int w = e.otherPoint(v);
            if (marked[v] && marked[w])            // 树中的边，非横切边，加入后形成环，不符合条件，舍去
                continue;
            mst.add(e);                            // 有一个顶点未被标记，说明该边是一条最小横切边，加入树中
            if (!marked[v])                        // 将和该顶点相连的所有横切边加入优先队列中
                addCrossEdges(G, v);
            if (!marked[w])
                addCrossEdges(G, w);
        }
    }

    private void addCrossEdges(EdgeUnGraph G, int v) {
        marked[v] = true;                                 // 将顶点标记
        for (Edge e : G.adjacent(v)) {
            int w = e.otherPoint(v);
            if (!marked[w])                              // 该边是一条横切边，将其加入队列
                crossEdges.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weights() {
        double weights = 0.0;
        for (Edge e : mst) {
            weights += e.weight();
        }
        return weights;
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\tinyEWG.txt";
        EdgeUnGraph G = new EdgeUnGraph(filename);
        LazyPrimMST mst = new LazyPrimMST(G);
        System.out.println(mst.edges());
        System.out.println(mst.weights());

    }
}
