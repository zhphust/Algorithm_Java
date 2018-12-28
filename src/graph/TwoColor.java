package graph;

/**
 * 无向图的双色问题，基于深度优先搜索
 * 有环不一定不会生成双色图，但是无环一定可以生成双色图
 * 只有在有环并且环上的结点个数为奇数的情况下才不能生成双色图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean twoColor = true;

    public TwoColor(UnGraph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
            }
        }
    }
    private void dfs(UnGraph G, int s) {
        marked[s] = true;
        for (int w : G.adjacent(s)) {
            if (!marked[w]) {
                color[w] = !color[s];
                dfs(G, w);
            } else if (color[w] == color[s]) {
                // w颜色已经标记，如果w是s的父结点，则其颜色相反，而不会相同，
                // 如果颜色相同，那么w一定不是s的父结点，说明存在环，并且该环上有奇数个结点
                twoColor = false;
            }
        }
    }

    public boolean isTwoColor() {
        return twoColor;
    }
}
