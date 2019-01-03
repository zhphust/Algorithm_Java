package graph;

import java.util.LinkedList;

/**
 * 有向环的检测
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public class DiCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private LinkedList<Integer> cycle;                  // 有向环中的所有顶点（如果存在的话）
    private boolean[] onStack;                          // 递归调用栈上的所有顶点

    public DiCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        cycle = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;                  // 从结点v开始递归，先将此结点标记，用于检测环
        for (int w : G.adjacent(v)) {
            if (hasCycle()) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                // w已经被标记过，说明在递归的路径上已经访问过该结点，也就存在环
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;                   // 递归路径上没有检测出环，将结点标记清除
    }

    public boolean hasCycle() {
        return !cycle.isEmpty();
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
