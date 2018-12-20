package dataStruct;

import java.util.*;

/**
 * 红黑树
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private final Node terminal = new Node();                // 注意：terminal不能有父结点、子结点
    private Node root = terminal;
    private LinkedList<Node> list = new LinkedList<>();

    private class Node {
        Key key;
        Value value;
        boolean color;
        int size;
        Node parent, left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.color = RED;
            this.left = terminal;
            this.right = terminal;
        }

        private Node() {
            this.color = BLACK;           // 哨兵结点的颜色为黑色
            this.size = 0;
        }

        public String toString() {
            return "[" + key + ":" + value + "|" + size + "(" + (color == BLACK ? "B)]" : "R)]");
        }
    }

    public Value search(Key key) {
        Node x = root;
        while (x != terminal) {
            if (key.compareTo(x.key) == 0)
                return x.value;
            else if (key.compareTo(x.key) < 0)
                x = x.left;
            else
                x = x.right;
        }
        return null;
    }

    // 插入结点
    public void insert(Key key, Value value) {
        Node z = new Node(key, value);
        if (root == terminal) {                            // 树为空
            root = z;
            root.color = BLACK;
            root.parent = terminal;
            return;
        }

        Node x = root;
        Node y = terminal;                                 // 要插入元素z的父结点
        while (x != terminal) {
            y = x;
            y.size++;                                      // 插入路径上更新结点的个数
            if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;
        if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        // 调整结点，使其满足红黑树的性质
        insertFixup(z);
    }

    private void insertFixup(Node z) {
        // 结点z的父结点颜色为红的情况下才会破坏红黑树的性质
        // 因为插入结点是红结点，如果父结点是黑，那么没有对树造成任何破坏
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {         // 结点z一定有祖父结点，否则父结点为根结点，而根结点颜色为黑
                Node y = z.parent.parent.right;             // 结点y为结点z的叔结点
                // 情况1，父结点和叔结点颜色均为红色
                if (y.color == RED) {
                    y.color = BLACK;                        // 叔结点设为黑
                    z.parent.color = BLACK;                 // 父结点设为黑
                    z.parent.parent.color = RED;            // 祖父结点设为红
                    z = z.parent.parent;                    // 将祖父结点赋值给z，循环
                }
                else {
                    // 情况2，叔结点是黑色的，且结点z是其父结点的右子节点
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);                             // 左旋
                    }
                    // 情况3，该情况是情况2的递进，叔结点是黑色的，且结点z是其父结点的左子孩子
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);                    // 此时，调整结束，退出循环
                }
            }
            else {
                Node y = z.parent.parent.left;
                if (y.color == RED) {
                    y.color = BLACK;
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                }
                else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = BLACK;                      // 结束时一定要将根结点的颜色置为黑色
    }

    // 左旋
    private void leftRotate(Node x) {
        /*
        * 旋转改变对象的过程中，先分别改变两个结点与其他结点间的关系，
        * 再改变两个结点之间的关系
        * 旋转只会改变旋转轴上两个结点的数量，不会改变其他的数量
        */
        Node y = x.right;
        // 处理y结点的左子节点
        x.right = y.left;
        if (y.left != terminal)
            y.left.parent = x;
        // 处理x结点的父结点
        y.parent = x.parent;
        if (x.parent == terminal)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else x.parent.right = y;
        // 处理x结点和y结点的关系
        x.parent = y;
        y.left = x;

        // 处理两结点的个数
        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
    }

    // 右旋
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != terminal)
            x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == terminal)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else y.parent.right = x;

        x.right = y;
        y.parent = x;

        x.size = y.size;
        y.size = y.left.size + y.right.size + 1;
    }

    // 删除某关键字的结点
    public Value delete(Key key) {
        // 查询过程会使路径上的结点数目-1，因此需要首先查看是否存在该结点
        if (search(key) == null) {
            return null;
        }

        Node x = root;
        while (x != terminal) {
            if (key == x.key) {
                return delete(x);
            } else if (key.compareTo(x.key) < 0) {
                x.size--;                              // 路径上的结点数-1
                x = x.left;
            } else {
                x.size--;                              // 路径上的结点数-1
                x = x.right;
            }
        }
        return null;
    }

    public Value delete(Node z) {
        if (z == terminal)
            return null;

        Value value = z.value;
        // 删除结点只有一个子结点的情况下，y == z;
        // 有两个结点的情况下，y是z的后继
        Node y = z;
        boolean originColor = y.color;       // 记住y的颜色
        Node x;                              // y的子结点，将来占据y的位置
        if (y.left == terminal) {
            x = y.right;
            transplant(y, y.right);
        } else if (y.right == terminal) {
            x = y.left;
            transplant(y, y.left);
        } else {
            y = min(z.right);                 // 找到z的后继，赋给y
            originColor = y.color;            // 重新将y的原始颜色保存
            x = y.right;                      // y的后继
            if (y == z.right) {
                x.parent = y;
            } else {
                Node temp = y.right;           // 更新结点的个数时使用
                transplant(y, y.right);
                y.right = z.right;
                z.right.parent = y;

                // 更新结点的个数（从y的右孩子，一直更新到替换y的地方）
                Node k = y.right;
                while (k != temp) {
                    k.size--;
                    k = k.left;
                }
            }
            transplant(z, y);
            y.left = z.left;
            z.left.parent = y;
            y.color = z.color;

            // 更新y结点的个数
            y.size = y.left.size + y.right.size + 1;
        }
        if (originColor == BLACK) {
            deleteFixup(x);
        }
        return value;
    }

    private void deleteFixup(Node x) {
        while (x != root && x.color == BLACK) {                     // 结点x为具有双重黑色的非根结点
            // 情况1，结点x是其父结点的左子节点
            if (x == x.parent.left) {
                Node w = x.parent.right;                             // 结点w为结点x的兄弟结点
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;                               // 重新定义兄弟结点
                }
                // 情况2，由情况1递进，经过1后，w一定为黑色的，如果：结点w和其两个子结点都是黑色的
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;                                    // 重新定义结点x，循环
                }
                else {
                    // 情况3，结点w的右子结点为黑色的
                    if (w.right.color == BLACK) {
                        w.color = RED;
                        w.left.color = BLACK;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // 情况4
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;                            // 进行了情况4,调整就结束了，通过设置根结点，退出循环
                }
            }
            else {
                Node w = x.parent.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                }
                else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        root.color = BLACK;                              // 最后，将根结点设为黑色
    }

    public Value deleteMin() {
        Key key = min();
        return delete(key);
    }

    public Value deleteMax() {
        Key key = max();
        return delete(key);
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x.size;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        while (x.left != terminal) {
            x = x.left;
        }
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != terminal) {
            x = x.right;
        }
        return x;
    }

    private void transplant(Node x, Node y) {
        y.parent = x.parent;
        if (x.parent == terminal)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else x.parent.right = y;
    }

    // 查找小于等于某关键字结点的最大关键字的结点
    public Node floor(Key key) {
        return floor(root, key);
    }

    private Node floor(Node x, Key key) {
        if (x == terminal || x.key == key)
            return x;
        if (key.compareTo(x.key) < 0) {                 // 当前结点的值大于待查找关键字
            return floor(x.left, key);                  // 递归在左子树中查找
        } else {
            Node y = floor(x.right, key);               // 否则，递归在右子树中继续查找
            if (y == terminal)
                return x;                             // y为空，说明右子树中没有比key小或者相等的结点，那么x就是最大的
            else
                return y;                             // 否则，返回右子树中查找到的子结点y
        }
    }

    // 查找大于等于某关键字结点的最小关键字的结点
    public Node ceiling(Key key) {
        return ceiling(root, key);
    }
    private Node ceiling(Node x, Key key) {
        if (x == terminal || x.key == key)
            return x;
        if (key.compareTo(x.key) > 0) {
            return ceiling(x.right, key);
        } else {
            Node y = ceiling(x.left, key);
            if (y == terminal)
                return x;
            else
                return y;
        }
    }

    // 查找某个结点的后继
    private Node successor(Node x) {
        /*
         * 查找结点的后继分为两种情况：
         * 1. 当前结点有右孩子，则它的后继一定是以右孩子为根的树的最小结点
         * 2. 当前结点没有右孩子，若存在后继，一定是该结点的一个祖先，该祖先是从该结点向上遍历，
         *    直到第一次得到某个祖先，该祖先是要得到的祖先的左孩子（左侧进入）。
         */
        if (x == terminal)
            return terminal;
        // 第一种情况
        if (x.right != terminal) {
            return min(x.right);
        }
        // 第二种情况
        Node y = x.parent;
        while (y != terminal && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;             // 返回可能为空（到达根结点），也可能找到某个祖先
    }

    // 查找某个结点的前驱
    private Node predecessor(Node x) {
        if (x == terminal)
            return terminal;
        if (x.left != terminal)
            return max(x.left);
        Node y = x.parent;
        while (y != terminal && x == y.left) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    /**
     * 查找某一排名的结点
     * @param rank 表示rank小的元素
     */
    public Node select(int rank) {
        return select(root, rank);
    }
    private Node select(Node x, int rank) {
        if (x == terminal)
            return terminal;
        int t = size(x.left);
        if (t + 1 == rank) {
            return x;
        } else if (rank < t + 1) {
            return select(x.left, rank);
        } else {
            return select(x.right, rank - t - 1);
        }
    }

    // 得到某一关键字表示结点的排名
    public int rank(Key key) {
        return rank(root, key);
    }
    /*
     * 查找排名分为两种情况：
     * 1. 该关键字key是某个结点的关键字，那么可以直接找到该结点排名；
     * 2. 该关键字key不是某个结点的关键字，那么返回的是若该结点在数中时候的排名
     */
    private int rank(Node x, Key key) {
        if (x == terminal)
            return 1;                               // 结点不存在树中，返回1
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return size(x.left) + 1;
        else if (cmp < 0) {
            return rank(x.left, key);
        } else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public Iterable<Key> keys() {
        List<Key> set = new ArrayList<>();
        keys(root, set, min(), max());
        return set;
    }

    // 得到某一范围内的关键字集合
    private void keys(Node x, List<Key> set, Key lo, Key hi) {
        if (x == terminal)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // 在左子树中查找是否存在范围内的关键字
        if (cmplo < 0) {
            keys(x.left, set, lo, hi);
        }
        // 当前结点在范围内，将其加入集合中
        if (cmplo <= 0 && cmphi >= 0) {
            set.add(x.key);
        }
        // 在又子树中查找是否存在范围内的关键字
        if (cmphi > 0) {
            keys(x.right, set, lo, hi);
        }
    }

    // 中序遍历，递归形式
    public Iterable<Node> inorderTreeWalk() {
        list.clear();
        inorderTreeWalk(root);
        return list;
    }
    private void inorderTreeWalk(Node x) {
        /*
         * 递归方式，实现中序遍历，中序遍历可以作为排序
         */
        if (x != terminal) {
            inorderTreeWalk(x.left);
            list.add(x);
            inorderTreeWalk(x.right);
        }
    }

    // 前序遍历，递归形式
    public Iterable<Node> preOrderTreeWalk() {
        list.clear();
        preOrderTreeWalk(root);
        return list;
    }
    private void preOrderTreeWalk(Node x) {
        /*
         * 递归方式，实现前序遍历
         */
        if (x != terminal) {
            list.add(x);
            preOrderTreeWalk(x.left);
            preOrderTreeWalk(x.right);
        }
    }

    // 后序遍历
    public Iterable<Node> postOrderTreeWalk() {
        list.clear();
        postOrderTreeWalk(root);
        return list;
    }
    private void postOrderTreeWalk(Node x) {
        /*
         * 递归方式，后序遍历
         */
        if (x != terminal) {
            postOrderTreeWalk(x.left);
            postOrderTreeWalk(x.right);
            list.add(x);
        }
    }

    // 层序遍历，思想：广度优先搜索
    public Iterable<Node> levelOrderTreeWalk() {
        list.clear();
        Node x = root;
        if (x == terminal) {
            System.out.println("The redblacktree is null!");
            return null;
        }
        LinkedList<Node> list1 = new LinkedList<>();
        list1.add(x);
        while (!list1.isEmpty()) {
            x = list1.poll();
            list.add(x);
            if (x.left != terminal)
                list1.add(x.left);
            if ((x.right != terminal))
                list1.add(x.right);
        }
        return list;
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Character> bst = new RedBlackTree<>();
        Random rand = new Random(47);
        for (int i = 0; i < 20; i++) {
            bst.insert(rand.nextInt(100), (char)('A' + i));
        }

        System.out.println("Level: " + bst.levelOrderTreeWalk() + "\n");
        System.out.println("In:    " + bst.inorderTreeWalk() + "\n");
        System.out.println("Pre:   " + bst.preOrderTreeWalk() + "\n");
        System.out.println("Post:  " + bst.postOrderTreeWalk() + "\n");

        System.out.println(bst.max());
        System.out.println(bst.min());
        System.out.println(bst.size());
        System.out.println(bst.search(20));


        System.out.println("floor 63: " + bst.floor(63) + ", ceiling 63: " + bst.ceiling(63));
        System.out.println("rank 5: " + bst.select(5));
        System.out.println("key 68: " + bst.rank(68));

        Iterable<Integer> keys = bst.keys();
        for (Integer i : keys) {
            System.out.print(i + " ");
        }
        System.out.println();

        bst.insert(30, 'R');
        bst.insert(52, 'S');
        bst.delete(29);
        bst.insert(70, 'M');
        bst.insert(72, 'Y');
        bst.delete(68);
        bst.deleteMin();
        bst.deleteMax();
        bst.delete(61);
        bst.delete(100);
        System.out.println(bst.levelOrderTreeWalk());
    }
}
