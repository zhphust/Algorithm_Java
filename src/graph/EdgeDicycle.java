package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 检测有向环
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/11
 * @version 8.0
 */
public class EdgeDicycle {
    private boolean[] marked;
    private Edge[] edgeTo;
    private LinkedList<Edge> cycle;
    private boolean[] onStack;

    public EdgeDicycle(EdgeDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        cycle = new LinkedList<>();
        onStack = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (Edge e : G.adjacent(v)) {
            int w = e.to();
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                for (int x = v; x != w; x = edgeTo[x].from()) {
                    cycle.push(edgeTo[x]);
                }
                cycle.push(e);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return !cycle.isEmpty();
    }

    public Iterable<Edge> cycle() {
        return cycle;
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\tinyEWD.txt";
        EdgeDigraph G = new EdgeDigraph(filename);
        EdgeDicycle cyc = new EdgeDicycle(G);

        if (cyc.hasCycle()) {
            System.out.println(cyc.cycle());
        }
    }
}
