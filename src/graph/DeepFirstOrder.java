package graph;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 基于深度优先搜索的排序：前序，后序，逆后序
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public class DeepFirstOrder {
    private boolean[] marked;
    private LinkedList<Integer> preOrder;                 // 顶点的前序排列，基于队列
    private LinkedList<Integer> postOrder;                // 顶点的后序排列，基于队列
    private LinkedList<Integer> reversePostOrder;         // 顶点的逆后序排列，基于栈

    public DeepFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        preOrder = new LinkedList<>();
        postOrder = new LinkedList<>();
        reversePostOrder = new LinkedList<>();

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])
                dfs(G, i);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        preOrder.add(v);                 // 前序排列，在递归之前将结点加入队列中

        for (int w : G.adjacent(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }

        postOrder.add(v);                // 后序排列，在递归之后将结点加入队列中
        reversePostOrder.addFirst(v);    // 逆后序排列，在递归之后将结点加入栈中
    }

    public Iterable<Integer> preOrder() {
        return preOrder;
    }

    public Iterable<Integer> postOrder() {
        return postOrder;
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePostOrder;
    }

    public static void main(String[] args) throws IOException {
        Digraph dg = new Digraph(".\\src\\source\\topo.txt");
        DeepFirstOrder dfo = new DeepFirstOrder(dg);

        for (int v : dfo.reversePostOrder()) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
