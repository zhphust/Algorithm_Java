package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 无环加权有向图的最短路径
 * 实现思想：按照拓扑排序顺序，对每一个顶点引出的边进行放松
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/11
 * @version 8.0
 */
public class AcyclicShortestPath {
    private final int s;
    private Edge[] edgeTo;
    private double[] disTo;

    public AcyclicShortestPath(EdgeDigraph G, int s) {
        this.s = s;
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            disTo[i] = Double.POSITIVE_INFINITY;                 // 初始化所有顶点的距离为正无穷
        }
        disTo[s] = 0.0;

        Digraph dg = G.toDigraph();                             // 构造有向图
        Topological top = new Topological(dg);                  // 拓扑排序

        for (int v : top.order()) {                             // 按照拓扑排序，放松每一条边
            relax(G, v);
        }
    }
    private void relax(EdgeDigraph G, int v) {
        for (Edge e : G.adjacent(v)) {
            int w = e.to();
            if (disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
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
        String filename = ".\\src\\source\\tinyEWGAD.txt";
        EdgeDigraph G = new EdgeDigraph(filename);
        int source = 5;
        AcyclicShortestPath sp = new AcyclicShortestPath(G, source);

        for (int i = 0; i < G.V(); i++) {
            System.out.print(source + " to " + i);
            System.out.printf(" (%4.2f): ", sp.disTo(i));
            if (sp.hasPathTo(i))
                System.out.println(sp.pathTo(i));
        }
    }
}
