package graph;

/**
 * 带权重的图接口
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public interface EdgeGraph<T> {
    int V();
    int E();
    Iterable<T> adjacent(int v);
    void addEdge(T e);
    Iterable<T> edges();
}
