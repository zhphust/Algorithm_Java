package graph;

import dataStruct.IndexMinPriorityQueue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 即时最小生成树
 * 基于数据结构：索引最小优先队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class PrimMST {
    private boolean[] marked;
    private Edge[] edgeTo;                                // 指向某顶点的最小生成树中的边
    private double[] disTo;                               // 最小生成树中边的权重
    private IndexMinPriorityQueue<Double> vertices;       // 和树中顶点距离已知的非树顶点的集合

    public PrimMST(EdgeUnGraph G) {
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            disTo[i] = Double.POSITIVE_INFINITY;                   // 初始化所有距离为正无穷
        }
        vertices = new IndexMinPriorityQueue<>(G.V());              // 队列中最多有V个元素

        disTo[0] = 0.0;                                   // 初始顶点的距离设为0，初始顶点没有指向它的边
        vertices.insert(0, 0.0);
        while (!vertices.isEmpty()) {
            addCrossEdges(G, vertices.delMin());
        }
    }

    private void addCrossEdges(EdgeUnGraph G, int v) {
        marked[v] = true;                                // 标记树中的结点
        for (Edge e : G.adjacent(v)) {
            int w = e.otherPoint(v);
            if (marked[w])                              // 树中的边，忽略
                continue;
            if (e.weight() < disTo[w]) {               // 边的权重小于当前顶点到树的顶点之间的距离
                edgeTo[w] = e;
                disTo[w] = e.weight();
                if (vertices.contains(w)) {           // 优先队列中已经有该结点，更新它
                    vertices.changeKey(w, disTo[w]);
                } else {
                    vertices.insert(w, disTo[w]);     // 优先队列中没有该结点，将其加入
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 1; i < edgeTo.length; i++) {
            edges.add(edgeTo[i]);
        }
        return edges;
    }

    public double weights() {
        double weights = 0.0;
        for (Double dis : disTo)
            weights += dis;
        return weights;
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\tinyEWG.txt";
        EdgeUnGraph G = new EdgeUnGraph(filename);
        PrimMST mst = new PrimMST(G);
        System.out.println(mst.edges());
        System.out.println(mst.weights());

    }
}
