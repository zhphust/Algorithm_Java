package graph;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 连通分量
 * 从一个顶点开始，能够访问到的所有顶点处于一个连通分量中
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;                             // 数组中元素表示该顶点所处连通分量的标识符
    private int count;                            // 连通分量的个数

    public ConnectedComponent(UnGraph G) {        // 针对的是无向图
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }
        }
    }
    private void dfs(UnGraph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : G.adjacent(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean isConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws IOException{
        UnGraph G = new UnGraph(".\\src\\source\\tinyG.txt");
        System.out.println(G);

        ConnectedComponent cc = new ConnectedComponent(G);
        int count = cc.count();
        Set<Integer>[] set = new Set[count];
        for (int i = 0; i < count; i++) {
            set[i] = new HashSet<>();
        }
        for (int i = 0; i < G.V(); i++) {
            set[cc.id(i)].add(i);
        }
        System.out.println(count + " components");
        for (int i = 0; i < count; i++) {
            Integer[] ii = set[i].toArray(new Integer[0]);
            System.out.println(Arrays.toString(ii));
        }
    }
}
