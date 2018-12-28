package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 有向图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public class Digraph implements UnedgedGraph {
    private int V;
    private int E;
    private ArrayList<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    public Digraph(String s) throws IOException{
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(s)));
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
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adjacent(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph G1 = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int w : adj[i]) {
                G1.addEdge(w, i);
            }
        }
        return G1;
    }

    public int V() {
        return V;
    }

    public int E(){
        return E;
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
        Digraph dg = new Digraph(".\\src\\source\\tinyDG.txt");
        System.out.println(dg);
        System.out.println(dg.reverse());
    }
}
