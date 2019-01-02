package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 无环条件下的最长路径
 * 将距离初始化为负无穷，然后放松的法则反号即可
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/11
 * @version 8.0
 */
public class AcyclicLongestPath {
    private final int s;
    private Edge[] edgeTo;
    private double[] disTo;

    public AcyclicLongestPath(EdgeDigraph G, int s) {
        this.s = s;
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            disTo[i] = Double.NEGATIVE_INFINITY;
        }
        disTo[s] = 0.0;

        Digraph dg = G.toDigraph();
        Topological top = new Topological(dg);
        for (int v : top.order()) {
            relax(G, v);
        }

    }

    private void relax(EdgeDigraph G, int v) {
        for (Edge e : G.adjacent(v)) {
            int w = e.to();
            if (disTo[w] < disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        return disTo[v] > Double.NEGATIVE_INFINITY;
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
        AcyclicLongestPath sp = new AcyclicLongestPath(G, source);

        for (int i = 0; i < G.V(); i++) {
            System.out.print(source + " to " + i);
            System.out.printf(" (%4.2f): ", sp.disTo(i));
            if (sp.hasPathTo(i))
                System.out.println(sp.pathTo(i));
        }
    }
}
