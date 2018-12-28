package graph;

import java.io.*;
import java.util.TreeMap;

/**
 * 符号图
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/7
 * @version 8.0
 */
public class SymbolGraph {
    private TreeMap<String, Integer> tree;                         // 字典，实现符号到顶点（整数）的映射
    private String[] keys;                                         // 顶点到符号的反向映射
    private UnGraph G;

    public SymbolGraph(String stream, String re) throws IOException{
        tree = new TreeMap<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(stream)));
        String ss;
        while ((ss = reader.readLine()) != null) {
            String[] vertices = ss.split(re);                // 每一行的第一位是电影名，后面是演员
            for (int i = 0; i < vertices.length; i++) {
                if (!tree.containsKey(vertices[i])) {
                    tree.put(vertices[i], tree.size());      // 将当前树的大小作为后续索引
                }
            }
        }
        // 构造反向索引，数组的值是电影或演员名
        keys = new String[tree.size()];
        for (String name : tree.keySet()) {
            keys[tree.get(name)] = name;
        }
        // 重新扫描文件，构造图
        G = new UnGraph(tree.size());
        reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(stream)));
        while ((ss = reader.readLine()) != null) {
            String[] names = ss.split(re);
            String movie = names[0];
            for (int i = 1; i < names.length; i++) {
                G.addEdge(tree.get(movie), tree.get(names[i]));
            }
        }

    }

    public boolean contains(String s) {
        return tree.containsKey(s);
    }

    public int index(String s) {
        return tree.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public UnGraph graph() {
        return G;
    }

    public static void main(String[] args) throws IOException {
        String filename = ".\\src\\source\\movies.txt";
        String regex = "/";
        SymbolGraph sg = new SymbolGraph(filename, regex);
        UnGraph G = sg.graph();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source;
        while ((source = reader.readLine()) != null) {
            for (int w : G.adjacent(sg.index(source))) {
                System.out.println("    " + sg.name(w));
            }
        }
    }
}
