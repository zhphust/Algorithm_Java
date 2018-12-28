package graph;

import java.io.*;
import java.util.ArrayList;

/**
 * 无权重无向图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class UnGraph implements UnedgedGraph {
    private final int V;                                  // 顶点的个数
    private int E;                                        // 边的个数
    private ArrayList<Integer>[] adj;                     // 链表实现邻接表
    public UnGraph(int V) {
        this.V = V;
        E = 0;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public UnGraph(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)));
        this.V = Integer.parseInt(reader.readLine());
        this.E = 0;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] vertices = line.split(" ");
            int v = Integer.parseInt(vertices[0]);
            int w = Integer.parseInt(vertices[1]);
            addEdge(v, w);
        }
    }

    public void addEdge(int u, int w) {
        adj[u].add(w);
        adj[w].add(u);
        E++;
    }

    public Iterable<Integer> adjacent(int v) {
        return adj[v];
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    public int degree(int v) {                        // 计算顶点的度数
        return adj[v].size();
    }

    public int maxDegree(){                          // 计算最大度数
        int max = 0;
        for (int i = 0; i < V; i++) {
            if (degree(i) > max) {
                max = degree(i);
            }
        }
        return max;
    }

    public double avgDegree() {
        return 2 * E / V;
    }

    public int numOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                if (v == w)
                    count++;
            }
        }
        return count/2;                 // 每条边在增加边的时候都被记了两次
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V);
        s.append(" vertices, ");
        s.append(E);
        s.append(" edges.\n");
        for (int i = 0; i < V; i++) {
            s.append(i);
            s.append(":");
            for (int w : adj[i]) {
                s.append(" ");
                s.append(w);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException{
        UnGraph G = new UnGraph(".\\src\\source\\tinyG.txt");
        System.out.println(G);
    }
}
