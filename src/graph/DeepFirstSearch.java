package graph;

import java.io.IOException;

/**
 * 深度优先搜索（适用于无向图和有向图）
 * 深度优先搜索通过使用递归，不断地进行深入，从而找到与源结点所连接的所有顶点
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class DeepFirstSearch {
    private boolean[] marked;            // 记录当前顶点是否已经被访问过
    private int count;                   // 与源结点相连结点的总数（包含源结点）

    /**
     * 深度优先搜索
     * @param G 无权重图的接口
     * @param source 源顶点数组
     */
    public DeepFirstSearch(UnedgedGraph G, Integer... source) throws IOException {
        if (source.length == 0) {
            throw new IOException("need at least one Integer parameter");
        }
        this.marked = new boolean[G.V()];
        this.count = 0;
        for (int i = 0; i < source.length; i++) {
            if (!marked[source[i]])
                dfs(G, source[i]);
        }
    }

    private void dfs(UnedgedGraph G, int s) {             // 深度优先搜索，递归调用
        marked[s] = true;
        count++;
        for (int w : G.adjacent(s)) {
            if (!marked[w]) {
                dfs(G, w);
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
        DeepFirstSearch dfs = new DeepFirstSearch(dg, 6);
        System.out.print("Can reach " + dfs.count() + " nodes: ");
        for (int i = 0; i < dg.V(); i++) {
            if (dfs.isReachable(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
