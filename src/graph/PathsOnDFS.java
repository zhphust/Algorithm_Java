package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 基于深度优先搜索寻找路径
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class PathsOnDFS {
    private final int s;                                // 源结点
    private boolean[] marked;
    private int[] edgeTo;

    public PathsOnDFS(UnedgedGraph G, int s) {
        this.s =s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        edgeTo[s] = s;
        dfs(G, s);
    }

    private void dfs(UnedgedGraph G, int s) {
        marked[s] = true;
        for (int w : G.adjacent(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G, w);
            }
        }
    }

    boolean hasPathTo(int v) {
        return marked[v];
    }

    public LinkedList<Integer> pathTo(int v) {
        if (!marked[v])
            return null;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = v; i != s ; i = edgeTo[i]) {
            list.addFirst(i);
        }
        list.addFirst(s);
        return list;
    }

    public static void main(String[] args) throws IOException {
        UnGraph G = new UnGraph(".\\src\\source\\tinyCG.txt");
        int source = 0;
        PathsOnDFS path = new PathsOnDFS(G, source);
        for (int i = 0; i < G.V(); i++) {
            System.out.print(source + " to " + i + ": ");
            if (path.hasPathTo(i)) {
                // System.out.println(path.pathTo(i));
                LinkedList<Integer> p = path.pathTo(i);
                System.out.print(p.pop());
                while (!p.isEmpty()) {
                    System.out.print("-" + p.pop());
                }
                System.out.println();
            } else {
                System.out.println("has no path.");
            }
        }
    }
}
