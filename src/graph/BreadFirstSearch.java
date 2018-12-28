package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 广度优先搜索（适用于无向图和有向图）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public class BreadFirstSearch {
    private boolean[] marked;
    private int count;

    public BreadFirstSearch(UnedgedGraph G, Integer s) {
        marked = new boolean[G.V()];
        count = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        marked[s] = true;
        count++;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adjacent(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    queue.add(w);
                }
            }
        }
    }

    public boolean isReachable(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException {
        Digraph dg = new Digraph(".\\src\\source\\tinyDG.txt");
        System.out.println(dg);
        BreadFirstSearch dfs = new BreadFirstSearch(dg, 6);
        System.out.print("Can reach " + dfs.count() + " nodes: ");
        for (int i = 0; i < dg.V(); i++) {
            if (dfs.isReachable(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
