package graph;

import dataStruct.IndexMinPriorityQueue;
import java.io.IOException;
import java.util.LinkedList;

/**
 * 最短路径，适用于权重为非负的有向图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/10
 * @version 8.0
 */
public class DijkstraShortestPath {
    private final int s;                                             // 起点
    private Edge[] edgeTo;
    private double[] disTo;

    private IndexMinPriorityQueue<Double> pq;                        // 索引最小优先队列

    public DijkstraShortestPath(EdgeDigraph G, int s) {
        this.s = s;
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        for (int i = 0; i < disTo.length; i++) {
            disTo[i] = Double.POSITIVE_INFINITY;                     // 初始化所有距离为正无穷大
        }
        pq = new IndexMinPriorityQueue<>(G.V());

        disTo[s] = 0.0;                                              // 初始化起点的距离为0
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());                                   // 队列非空的条件下放松一条边
        }
    }

    private void relax(EdgeDigraph G, int v) {
        for (Edge e : G.adjacent(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))                                // 某顶点已经在队列中，则更新它，否则，将其添加到队列中
                    pq.changeKey(w, disTo[w]);
                else pq.insert(w, disTo[w]);
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        return disTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
        LinkedList<Edge> paths = new LinkedList<>();
        for (int i = v; i != s; i = edgeTo[i].from()) {
            paths.addFirst(edgeTo[i]);
        }
        return paths;
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\tinyEWD.txt";
        EdgeDigraph G = new EdgeDigraph(filename);
        System.out.println(G);
        int source = 0;
        DijkstraShortestPath sp = new DijkstraShortestPath(G, source);

        for (int i = 0; i < G.V(); i++) {
            System.out.print(source + " to " + i);
            System.out.printf(" (%4.2f): ", sp.disTo(i));
            if (sp.hasPathTo(i))
                System.out.println(sp.pathTo(i));
        }
    }
}
