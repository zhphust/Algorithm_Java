package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 加权无向图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class EdgeUnGraph implements EdgeGraph<Edge> {
    private final int V;
    private int E;
    private ArrayList<Edge>[] adj;

    public EdgeUnGraph(int V) {
        this.V= V;
        this.E = 0;
        this.adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public EdgeUnGraph(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)));
        this.V = Integer.parseInt(reader.readLine());
        this.E = 0;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        String ss;
        while ((ss = reader.readLine()) != null) {
            String[] vertices = ss.split(" ");
            int v = Integer.parseInt(vertices[0]);
            int w = Integer.parseInt(vertices[1]);
            double weight = Double.parseDouble(vertices[2]);
            addEdge(new Edge(v, w, weight));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.onePoint();
        int w = e.otherPoint(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adjacent(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Set<Edge> edges = new HashSet<>();           // 每条边会出现两次，因此用Set集合，消除重复情况
        for (ArrayList<Edge> edge : adj) {
            edges.addAll(edge);
        }
        return edges;
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
            for (Edge e : adj[i]) {
                s.append(" ");
                s.append(e);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\tinyEWG.txt";
        EdgeUnGraph G = new EdgeUnGraph(filename);
        System.out.println(G);
        System.out.println(G.edges());
        System.out.println(G.adjacent(0));
    }
}
