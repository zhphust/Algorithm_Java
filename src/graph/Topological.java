package graph;

/**
 * 拓扑排序（必须为有向无环图），所有顶点的逆后序排列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/8
 * @version 8.0
 */
public class Topological {
    private Iterable<Integer> order;
    public Topological(Digraph G) {
        DiCycle diCycle = new DiCycle(G);
        if (!diCycle.hasCycle()) {                       // 首先确保不存在有向环
            DeepFirstOrder dfo = new DeepFirstOrder(G);  // 对图进行排序
            order = dfo.reversePostOrder();              // 得到逆后序排列即为拓扑排序
        }
    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
