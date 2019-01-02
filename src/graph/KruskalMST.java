package graph;

import dataStruct.MinPriorityQueue;
import dataStruct.UF2;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 最小生成树，通常比Prim算法慢
 * 由一个个森林逐渐形成一棵树
 * 实现结构：最小优先队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class KruskalMST {
    private Set<Edge> mst;

    public KruskalMST(EdgeUnGraph G) {
        Edge[] edges = new Edge[G.E()];                                // 所有的边
        int i = 0;
        for (Edge e : G.edges()) {
            edges[i++] = e;
        }
        MinPriorityQueue<Edge> mpq = new MinPriorityQueue<>(edges);     // 将所有边加入最小优先队列
        mst = new HashSet<>();
        UF2 uf = new UF2(G.V());                                        // 连通分量，将森林练成树

        while (!mpq.isEmpty() && (mst.size() < G.V() - 1)) {
            Edge e = mpq.delMin();
            int v = e.onePoint();
            int w = e.otherPoint(v);
            if (uf.connected(v, w))                                     // 两顶点属于同一分量，忽略
                continue;
            uf.union(v, w);                                             // 连通两分量
            mst.add(e);                                                 // 将边加入树中
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
        KruskalMST mst = new KruskalMST(G);
        System.out.println(mst.edges());
        System.out.println(mst.weights());

    }
}
