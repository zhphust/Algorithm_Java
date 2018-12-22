package dataStruct;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * B树
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/22
 * @version 8.0
 */
public class BTree<T extends Comparable<T>> {
    public final class Node {
        int N = 0;                                                     // 结点中关键字的个数
        T[] keys;                                                      // 关键字数组
        Object[] children;                                              // 子结点数组
        Node parent = null;                                           // 父结点
        boolean leaf;                                                 // 该结点是否为叶结点

        @SuppressWarnings("unchecked")
        Node(int degree) {
            keys = (T[])new Comparable[2*degree-1];
            children = new Object[2*degree];
            leaf = true;
        }

        public String toString() {
            StringBuilder s = new StringBuilder("[");
            for (int i = 0; i < N; i++) {
                s.append(keys[i]).append(" ");
            }
            s.deleteCharAt(s.length()-1);
            s.append("]");
            return s.toString();
        }
    }

    private int degree;                                                   // 度数
    private Node root;

    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node(degree);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterable<Node> nodes = level();
        for (Node x : nodes) {
            s.append(x);
        }
        return s.toString();
    }

    /**
     * 查找包含某关键字的结点，返回该结点或者空（不存在）
     * @param key: 要查找的关键字
     * @return 包含关键字的结点
     */
    public Node search(T key) {
        return search(root, key);
    }

    private Node search(Node x, T key) {
        if (x == null)
            return null;
        while (true) {
            int pos = 1;
            while (pos <= x.N && key.compareTo(x.keys[pos-1]) > 0) {
                pos++;
            }
            if (pos <= x.N && key == x.keys[pos-1]) {        // 在该结点上找到关键字
                return x;
            } else if (x.leaf) {                         // 该结点上存在该关键字，且该结点已经是叶结点了
                return null;
            } else {
                x = (Node)x.children[pos-1];                     // 读取磁盘数据，沿树向下循环
            }
        }
    }

    /**
     * 返回某关键字在结点中的位置
     * @param key; 要查找的关键字
     * @return 返回该关键字在结点中的位置，未找到则返回-1
     */
    public int searchIndex(T key) {
        Node x = search(key);
        if (x == null)
            return -1;
        int pos = 1;
        while (key != x.keys[pos-1]) {
            pos++;
        }
        return pos;
    }

    /**
     * 向树中插入关键字（插入的位置最终位于叶结点）
     * @param key: 要插入的关键字
     */
    public void insert(T key) {
        /*
        * 从根结点开始，对插入关键字路径上的每一个结点进行检查，
        * 查看其是否已满，如果是，则将该结点进行分裂
        * 根结点的分裂是树长高的唯一方式
        */
        if (root.N == 2 * degree - 1) {                               // 根结点已满
            Node s = new Node(degree);
            s.leaf = false;
            s.children[0] = root;
            root.parent = s;
            root = s;
            splitChild(root, 1);
        }
        insertNotFull(root, key);
    }

    /**
     * 将当前结点的某个子结点分裂，分裂后该结点的关键字会加1
     * @param p : 待处理的结点（即将分裂结点的父结点，该结点一定非满）
     * @param pos : 待分裂的当前结点x的第i个子结点
     */
    private void splitChild(Node p, int pos) {
        Node y = (Node)p.children[pos-1];                  // 待分裂结点（结点的第i个子结点）
        Node z = new Node(degree);                        // 新建一个结点，该结点会成为y的兄弟结点
        z.leaf = y.leaf;                                  // 将待y结点的叶结点属性赋予新建结点z
        z.N = degree - 1;
        for (int j = 1; j <= degree-1; j++) {           // 将y的一半关键字赋给z，中心关键字不动，将会被提升
            z.keys[j-1] = y.keys[j-1+degree];
            y.keys[j-1+degree] = null;
        }
        if (!y.leaf) {                                 // y结点不是叶结点，有子结点，将其一半也赋给z结点
            for (int j = 1; j <= degree; j++) {
                z.children[j-1] = y.children[j-1 + degree];
                ((Node)z.children[j-1]).parent = z;
                y.children[j-1+degree] = null;
            }
        }
        y.N = degree - 1;

        for (int j = p.N; j >= pos; j--) {                // 将p结点的后一半向右移动一位
            p.keys[j] = p.keys[j-1];
            p.children[j+1] = p.children[j];
        }
        p.keys[pos-1] = y.keys[degree-1];                // 将子结点的中间结点提升到父结点中
        p.children[pos] = z;
        z.parent = p;                                  // 将z结点链接到父结点上
        y.keys[degree-1] = null;
        p.N += 1;
    }

    /**
     * 在一个当前非满结点上进行关键字插入操作
     * @param x: 待处理结点（非满）
     * @param k: 待插入关键字
     */
    private void insertNotFull(Node x, T k) {
        int i = x.N;
        if (x.leaf) {                                                      // 关键字插入在叶结点上
            while (i >= 1 && k.compareTo(x.keys[i-1]) < 0) {
                x.keys[i] = x.keys[i-1];                                  // 将关键字后移一位，留出插入位置
                i--;
            }
            x.keys[i] = k;
            x.N += 1;
        } else {                                              // 当前结点不是叶结点，找到要插入子结点的位置
            while (i >= 1 && k.compareTo(x.keys[i-1]) < 0) {
                i--;
            }
            i++;
            Node z = (Node)x.children[i-1];
            if (z.N == 2 * degree - 1) {                             // 判断子结点是否已满
                splitChild(x, i);
                if (k.compareTo(x.keys[i-1]) > 0)
                    i += 1;
            }
            insertNotFull((Node)x.children[i-1], k);                      // 递归地在一个非满的子结点上插入关键字
        }
    }

    public T minKey() {
        return minKey(root);
    }

    // 找到以某结点为根的最小结点的关键字
    private T minKey(Node x) {
        while (!x.leaf) {
            x = (Node)x.children[0];
        }
        return x.keys[0];
    }

    public T maxKey() {
        return maxKey(root);
    }

    private T maxKey(Node x) {
        while (!x.leaf) {
            x = (Node)x.children[x.N];
        }
        return x.keys[x.N-1];
    }

    public void delete(T key) {
        delete(root, key);
    }

    // 删除过程中，访问路径上的结点的关键字个数至少为degree，比正常结点的最少个数多1
    private void delete(Node x0, T key) {
        Node x = search(x0, key);                          // 找到要删除关键字的结点
        if (x == null)
            return;
        // 情况1，要删除的关键字在当前结点中
        if (x == x0) {
            int i = 1;
            while (key != x.keys[i-1]) {
                i++;
            }
            // 1.1 结点为叶结点
            if (x.leaf) {
                for (int j = i; j < x.N; j++) {
                    x.keys[j-1] = x.keys[j];
                }
                x.keys[x.N-1] = null;
                x.N -= 1;
            }  // 1.2 结点为内部结点，将子结点的一个关键字提升上来
            else {
                Node left = (Node)x.children[i-1];
                Node right = (Node)x.children[i];
                // 左子结点中至少包含degree个关键字，将待删除关键字的前驱代替该关键字
                if (left.N >= degree) {
                    T key1 = predecessor(key);                // 找到前驱
                    delete(left, key1);                       // 从子结点中删除前驱关键字
                    x.keys[i-1] = key1;                       // 让前驱替换待删除关键字
                } // 左子结点中包含少于degree个关键字，查看右子结点
                  // 将待删除关键字的后继代替该关键字
                else if (right.N >= degree) {
                    T key2 = successor(key);
                    delete(right, key2);
                    x.keys[i-1] = key2;
                } else {     // 左右两个子结点都只有degree-1个关键字，将两个结点连通待删除关键字都合并到左子结点
                    union(left, right, i);
                    delete(left, key);        // 递归在左子结点上删除关键字
                }
            }
        } else {     // 情况2，要删除的关键字不在当前内部结点中
            int i = 1;
            while (i <= x0.N && key.compareTo(x0.keys[i-1]) > 0)
                i++;
            Node ci = (Node)x0.children[i-1];         // 找到可能存在关键字的左子结点
            if (ci.N == degree-1) {             // 左子结点的关键字个数少于degree
                if (i == 1) {                   // 该子结点是第一个子结点，只有右兄弟
                    Node ciRight = (Node)x0.children[i];
                    if (ciRight.N >= degree) {  // 有兄弟关键字个数足够，给左兄弟分一个
                        moveRightToLeft(ciRight, ci, i);
                    } else {                    // 否则，合并量兄弟
                        union(ci, ciRight, i);
                    }
                } else if (i == x0.N + 1) {           // 该子结点是最后一个子结点，只有左兄弟
                    Node ciLeft = (Node)x0.children[i-2];
                    if (ciLeft.N >= degree) {
                        moveLeftToRight(ciLeft, ci, i-1);
                    } else {
                        union(ciLeft, ci, i - 1);
                        ci = ciLeft;
                    }
                } else {                             // 该子结点是一个中间结点
                    Node ciLeft = (Node)x0.children[i-2];
                    Node ciRight = (Node)x0.children[i];
                    if (ciLeft.N >= degree) {
                        moveLeftToRight(ciLeft, ci, i - 1);
                    } else if (ciRight.N >= degree) {
                        moveRightToLeft(ciRight, ci, i);
                    } else {
                        union(ci, ciRight, i);
                    }
                }
            }
            delete(ci, key);                        // 递归地在子结点中删除
        }
    }

    private T successor(T key) {
        Node x = search(key);
        int position = searchIndex(key);
        if (x == null) {
            return null;
        }
        if (x.leaf) {
            if (position < x.N) {
                return x.keys[position];
            } else {                       // 待查找关键字是叶结点的最后一个关键字，后继一定在祖先结点上
                Node p = x.parent;
                while (p != null) {
                    int j = 1;
                    while (j <= p.N && x != p.children[j-1])     // 找到该结点在父结点中的位置
                        j++;
                    if (j == p.N+1) {
                        x = p;
                        p = x.parent;
                    } else
                        return p.keys[j-1];
                }
                return null;
            }
        } else {
            Node z = (Node)x.children[position];
            return minKey(z);
        }
    }

    private T predecessor(T key) {
        Node x = search(key);
        int position = searchIndex(key);
        if (x == null)                                         // 未找到关键字key
            return null;
        if (x.leaf) {                                          // 叶结点
            if (position > 1) {
                return x.keys[position-2];             // i代表该结点的第i个关键字，因此前一个关键字下标为i-2
            } else {
                Node p = x.parent;
                while (p != null) {
                    int j = 1;
                    while (j <= p.N && x != p.children[j-1])
                        j++;
                    if (j == 1) {
                        x = p;
                        p = x.parent;
                    } else
                        return p.keys[j-2];
                }
                return null;                                    // 上溯到根结点也未找到前驱，返回空
            }
        } else {
            Node z = (Node)x.children[position-1];
            return maxKey(z);
        }
    }

    /*
     * 将左子结点的最后一个位置的关键字转移到其父结点上，
     * 父结点上的某一位置的关键字转移到其右子结点的第一个位置上
     * 转移前，左子结点的关键字个数>=degree
     */
    private void moveLeftToRight(Node left, Node right, int i) {
        Node p = left.parent;

        for (int j = right.N; j >= 1; j--) {
            right.keys[j] = right.keys[j-1];
            right.children[j+1] = right.children[j];
        }
        right.keys[0] = p.keys[i-1];                       // 将父结点上的关键字转移到右子结点上
        right.children[1] = right.children[0];
        right.children[0] = left.children[left.N];         // 左兄弟的一个孩子转移到右兄弟
        if (!left.leaf)
            ((Node)left.children[left.N]).parent = right;          // 设定转移孩子的父结点
        right.N += 1;

        p.keys[i-1] = left.keys[left.N-1];               // 将左子结点上的关键字转移到父结点上

        left.keys[left.N-1] = null;
        left.children[left.N] = null;
        left.N -= 1;
    }

    private void moveRightToLeft(Node right, Node left, int i) {
        Node p = right.parent;

        left.keys[left.N] = p.keys[i-1];
        left.children[left.N+1] = right.children[0];
        if (!right.leaf)
            ((Node)right.children[0]).parent = left;
        left.N += 1;

        p.keys[i-1] = right.keys[0];

        for (int j = 1; j < right.N; j++) {
            right.keys[j-1] = right.keys[j];
            right.children[j-1] = right.children[j];
        }
        right.keys[right.N-1] = null;
        right.children[right.N-1] = right.children[right.N];
        right.children[right.N] = null;
        right.N -= 1;
    }

    // 合并两兄弟结点，加上父结点某一位置的关键字
    private void union(Node x, Node y, int pos) {
        Node p = x.parent;
        x.keys[x.N] = p.keys[pos-1];
        for (int j = 1; j <= y.N; j++) {
            x.keys[x.N+j] = y.keys[j-1];
            x.children[x.N+j] = y.children[j-1];
            if (!y.leaf)
                ((Node)y.children[j-1]).parent = x;
        }
        x.children[x.N+y.N+1] = y.children[y.N];
        if (!y.leaf)
            ((Node)y.children[y.N]).parent = x;
        x.N += y.N + 1;
        y.parent = null;

        if (p == root && p.N == 1) {
            root = x;
            x.parent = null;
        } else {
            for (int j = pos; j < p.N; j++) {
                p.keys[j-1] = p.keys[j];
                p.children[j] = p.children[j+1];
            }
            p.keys[p.N-1] = null;
            p.children[p.N] = null;
            p.N -= 1;
        }
    }

    private Iterable<Node> level() {
        ArrayList<Node> list = new ArrayList<>();
        LinkedList<Node> list1 = new LinkedList<>();

        if (root == null) {
            return null;
        }
        list1.add(root);
        while (!list1.isEmpty()) {
            Node x = list1.pop();
            list.add(x);
            if (!x.leaf) {
                for (int i = 1; i <= x.N; i++) {
                    list1.add((Node)x.children[i-1]);
                }
                list1.add((Node)x.children[x.N]);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        BTree<Character> tree = new BTree<>(3);
        char[] chars = new char[]{'G', 'F', 'S', 'J', 'Q', 'K', 'C', 'I', 'L', 'U', 'H', 'T', 'V',
                                    'W', 'M', 'R', 'N', 'P', 'A', 'B', 'O', 'X', 'Y', 'D', 'Z', 'E',
                                    'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'b',
                                    'n', 'm', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'v'};
        for (int i = 0; i < chars.length; i++) {
            tree.insert(chars[i]);
        }
        System.out.println(tree.search('V'));
        System.out.println(tree.searchIndex('V'));
        System.out.println(tree.minKey());
        System.out.println(tree.maxKey());
        char x = 'X';
        tree.delete(x);
        char y = 'W';
        System.out.println(tree.predecessor(y) + " " + y + " " + tree.successor(y));
        System.out.println(tree);
    }
}


