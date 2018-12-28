package graph;

/**
 * 检测无向图中是否存在环，基于深度优先搜索
 * 对于一个顶点，如果其邻接表中的顶点已经被标记，且该顶点不是当前顶点的父顶点，
 * 那么一定存在环
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class Cycle {
    private boolean[] marked;
    private boolean cycle;

    public Cycle(UnGraph G) {
        marked = new boolean[G.V()];
        cycle = false;
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i, i);
            }
        }
    }
    private void dfs(UnGraph G, int u, int v) {
        /*
         * v结点（父结点）是到达u结点（子结点）的上一结点，
         * 对于无向图来说，如果某个结点已经被标记，而该结点又不是正在访问结点的父结点，
         * 那么一定存在环
         */
        marked[u] = true;
        for (int w : G.adjacent(u)) {
            if (!marked[w]) {
                dfs(G, w, u);
            } else if (w != v) {
                // w的父结点是u，而u的父结点是v，路径是：源结点-v-u-w
                // 如果w被标记，而w又不是v，
                // 那么源结点一定从其他路径访问过w，但是源结点已经访问了u，而从u又访问了w，所以一定存在环
                cycle = true;
            }
        }
    }
    public boolean hasCycle() {
        return cycle;
    }
}
