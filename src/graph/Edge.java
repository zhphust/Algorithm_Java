package graph;

/**
 * 加权图的边，本例包含了无向边和有向边的表示
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;                        // 权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    // 无向边
    public int onePoint() {
        return v;
    }

    public int otherPoint(int v) {
        if (v == this.v)
            return this.w;
        else
            return this.v;
    }

    // 有向边
    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int compareTo(Edge that) {
        double delta = this.weight - that.weight;
        if (delta < 0) return -1;
        else if (delta > 0) return +1;
        else return 0;
    }

    public String toString() {
        return String.format("%d-%d(%.2f)", v, w, weight);
    }
}
