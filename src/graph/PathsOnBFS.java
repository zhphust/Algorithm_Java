package graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 基于广度优先搜索查找路径
 * 广度优先搜索显式的通过队列这一数据结构，对树进行搜索
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class PathsOnBFS {
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;                            // 数组中的值表示到达某顶点的父顶点

    public PathsOnBFS(UnedgedGraph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(UnedgedGraph G, int s) {
        LinkedList<Integer> queue = new LinkedList<>();             // 用链表实现队列
        marked[s] = true;                                  // 先处理，再入队列
        edgeTo[s] = s;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();                         // 从队首获取元素
            for (int w : G.adjacent(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);                        // 将元素添加到队列中
                }
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
        PathsOnBFS path = new PathsOnBFS(G, source);
        for (int i = 0; i < G.V(); i++) {
            System.out.print(source + " to " + i + ": ");
            if (path.hasPathTo(i)) {
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
