package graph;

import java.io.IOException;

/**
 * 强连通分量（基于有向图、深度优先搜索、拓扑排序）
 * 如果两个顶点相互可达，则两顶点是强连通的；如果所有顶点都相互可达，则图是强连通的
 * 两顶点相互可达说明他们在一个有向环中
 * Kosaraju算法：
 *  1. 得到有向图的逆向图；
 *  2. 计算逆向图的逆后序排列（拓扑排序）；
 *  3. 按照拓扑排序顺序对原图进行深度优先搜索，一次能够到达的顶点都在同一个强连通分量中。
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class StrongConnectedComponent {
    private boolean[] marked;
    private int[] id;                              // 连通分量标识
    private int count;                             // 连通分量个数

    public StrongConnectedComponent(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;

        DeepFirstOrder dfo = new DeepFirstOrder(G.reverse());         // 对G的反向图进行逆后序排列
        for (int v : dfo.reversePostOrder()) {                        // 按照逆后序顺序对原图所有顶点进行深度优先搜索
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : G.adjacent(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean strongConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws IOException{
        String filename = ".\\src\\source\\tinyDG.txt";
        Digraph dg = new Digraph(filename);
        StrongConnectedComponent scc = new StrongConnectedComponent(dg);
        System.out.println(scc.count() + " components.");
        for (int i = 0; i < scc.count(); i++) {
            for (int j = 0; j < dg.V(); j++) {
                if (scc.id(j) == i) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}
