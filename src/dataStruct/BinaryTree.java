package dataStruct;

import java.util.*;

/**
 * 二叉树
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/3
 * @version 8.0
 */
public class BinaryTree<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        Node parent, left, right;
        int size = 1;                                   // 结点默认为叶节点，大小为1
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
        public String toString() {
            return "[" + key + ":" + value + "|" + size + "]";
        }
    }

    private Node root;
    private ArrayList<Node> list = new ArrayList<>();                    // 遍历时所用的集合

    //查找
    public Value search(Key key) {
        return search(root, key);
    }
    private Value search(Node x, Key key) {
        /*
         * 以当前结点为根结点，查找关键字为key的某结点
         * 如果当前结点为空，说明不存在此关键字的结点，返回空；
         * 如果当前结点关键字和该关键字相等，则找到该结点，并返回
         * 否则，则在当前关键字的左、右子结点中递归查找
         */
       if (x == null)
           return null;
       if (key == x.key)
            return x.value;
       else if (key.compareTo(x.key) < 0)
           return search(x.left, key);
       else
           return search(x.right, key);
    }

    // 插入结点
    public void insert(Key key, Value value) {
        // 插入一个结点，新插入的结点一定在叶结点上
        Node z = new Node(key, value);
        if (root == null) {
            root = z;
            return;
        }
        Node x = root;                      // 从根结点开始遍历
        Node y = null;                      // 跟踪一个结点，该结点是最终插入位置结点的父结点
        while (x != null) {
            y = x;                          // 记住当前比较结点，使得最终知道将结点插入到该结点的左孩子还是右孩子
            y.size += 1;                    // 最终添加的结点是该结点的孩子，因此子树中结点个数+1
            if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        if (z.key.compareTo(y.key) < 0)
            y.left = z;
        else y.right = z;
        z.parent = y;
    }

    // 删除某个关键字的结点
    public Value delete(Key key) {
        // 查询过程会使路径上的结点数目-1，因此需要首先查看是否存在该结点
        if (search(key) == null)
            return null;

        Node x = root;
        while (x != null) {
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

    private Value delete(Node z) {
        if (z == null)
            return null;
        Value value = z.value;

        if (z.left == null) {                // 1. 结点的左子树为空，用右子结点占据该结点的位置
            transplant(z, z.right);
        } else if (z.right == null) {        // 2. 结点的右子树为空，用左子结点占据该结点的位置
            transplant(z, z.left);
        } else {                             // 3. 该结点的左右子树均不为空，用其后继（或前驱）占据该结点的位置
            Node y = successor(z);                   // 右子树不为空，后继一定在右子树上
            if (y != z.right) {                      // 3.1 后继不是该待删除结点的右孩子
                Node temp = y.right;                 // 更新结点个数使用
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
            transplant(z, y);                       // 3.2 后继此时为待删除结点的右孩子，和3.1是递进关系
            y.left = z.left;
            z.left.parent = y;

            // 更新结点y
            y.size = y.left.size + y.right.size + 1;
        }
        return value;
    }

    public Value deleteMin() {
        Key key = min();
        return delete(key);
    }

    public Value deleteMax() {
        Key key = max();
        return delete(key);
    }

    private void transplant(Node x, Node y) {
        /*
         * 用结点y替换结点x，y成为结点x的父结点的孩子
         * 只处理父结点，不处理子结点
         */
        Node z = x.parent;
        if (z == null)
            root = y;
        else if (x == z.left)
            z.left = y;
        else
            z.right = y;
        if (y != null)
            y.parent = z;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    public Key max() {
        Node max = max(root);
        if (max != null)
            return max.key;
        else
            return null;
    }

    private Node max(Node x) {
        if (x == null)
            return null;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Key min() {
        Node min = min(root);
        if (min != null) {
            return min.key;
        } else
            return null;
    }

    private Node min(Node x) {
        if (x == null)
            return null;
        while (x.left != null)
            x = x.left;
        return x;
    }

    // 查找小于等于某关键字结点的最大关键字的结点
    public Node floor(Key key) {
        return floor(root, key);
    }

    private Node floor(Node x, Key key) {
        if (x == null || x.key == key)
            return x;
        if (key.compareTo(x.key) < 0) {                 // 当前结点的值大于待查找关键字
            return floor(x.left, key);                  // 递归在左子树中查找
        } else {
            Node y = floor(x.right, key);               // 否则，递归在右子树中继续查找
            if (y == null)
                return x;                           // y为空，说明右子树中没有比key小或者相等的结点，那么x就是最大的
            else
                return y;                           // 否则，返回右子树中查找到的子结点y
        }
    }

    // 查找大于等于某关键字结点的最小关键字的结点
    public Node ceiling(Key key) {
        return ceiling(root, key);
    }
    private Node ceiling(Node x, Key key) {
        if (x == null || x.key == key)
            return x;
        if (x.key.compareTo(key) < 0) {
            return ceiling(x.right, key);
        } else {
            Node y = ceiling(x.left, key);
            if (y == null)
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
        if (x == null)
            return null;
        // 第一种情况
        if (x.right != null) {
            return min(x.right);
        }
        // 第二种情况
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;          // 返回可能为空（到达根结点），也可能找到某个祖先
    }

    // 查找某个结点的前驱
    private Node predecessor(Node x) {
        if (x == null)
            return null;
        if (x.left != null)
            return max(x.left);
        Node y = x.parent;
        while (y != null && x == y.left) {
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
        if (x == null)
            return null;
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
        if (x == null)
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
        if (x == null)
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
        if (x != null) {
            inorderTreeWalk(x.left);
            list.add(x);
            inorderTreeWalk(x.right);
        }
    }
    public Iterable<Node> inorderTreeWalk1() {
        list.clear();
        Node x = root;
        /*
         * 循环方式，实现中序遍历
         */
        Stack<Node> stack = new Stack<>();
        while (x != null || !stack.isEmpty()) {
            while (x != null) {
                stack.push(x);                  // 先入栈
                x = x.left;
            }
            x = stack.pop();
            list.add(x);
            x = x.right;                     // 如果为空，根节点出栈并访问右子树
        }
        return list;
    }

    public Iterable<Node> preOrderTreeWalk() {
        list.clear();
        preOrderTreeWalk(root);
        return list;
    }
    private void preOrderTreeWalk(Node x) {
        /*
         * 递归方式，实现前序遍历
         */
        if (x != null) {
            list.add(x);
            preOrderTreeWalk(x.left);
            preOrderTreeWalk(x.right);
        }
    }

    public Iterable<Node> preOrderTreeWalk1() {
        list.clear();
        Node x = root;
        Stack<Node> stack = new Stack<>();
        while (x != null || !stack.isEmpty()) {
            /*
             * 如果当前结点非空，则先打印（访问），然后将其入栈，并循环，一直到没有左孩子的结点；
             * 到达没有左孩子的结点后，将栈中的该结点弹出，并获得其右孩子结点，然后处理右子树
             */
            while (x != null) {
                list.add(x);
                stack.push(x);                        // 先访问，再入栈
                x = x.left;
            }
            x = stack.pop();
            x = x.right;                            // 如果为空，出栈并处理右子树
        }
        return list;
    }

    public Iterable<Node> preOrderTreeWalk2() {
        /*
         * 循环方式，实现前序遍历
         */
        list.clear();
        Node x = root;
        if (x == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(x);
        while (!stack.isEmpty()) {
            x = stack.pop();
            list.add(x);
            // 先将右子结点入栈，再将左子结点入栈
            // 如此，下一轮循环，栈将先弹出左子结点
            if (x.right != null) {
                stack.push(x.right);
            }
            if (x.left != null) {
                stack.push(x.left);
            }
        }
        return list;
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
        if (x != null) {
            postOrderTreeWalk(x.left);
            postOrderTreeWalk(x.right);
            list.add(x);
        }
    }
    public Iterable<Node> postOrderTreeWalk1() {
        /*
         * 循环方式，后序遍历
         */
        list.clear();
        Node x = root;
        if (x == null)
            return null;
        Stack<Node> stack = new Stack<>();
        Stack<Node> temp = new Stack<>();
        stack.push(x);
        while (!stack.isEmpty()) {
            x = stack.pop();
            temp.push(x);
            if (x.left != null) {
                stack.push(x.left);
            }
            if (x.right != null) {
                stack.push(x.right);
            }
        }
        while (!temp.isEmpty()) {
            list.add(temp.pop());
        }
        return list;
    }

    // 层序遍历
    public Iterable<Node> levelOrderTreeWalk() {
        list.clear();
        Node x = root;
        if (x == null)
            return null;
        LinkedList<Node> list1 = new LinkedList<>();
        list1.add(x);
        while (!list1.isEmpty()) {
            x = list1.poll();
            list.add(x);
            if (x.left != null)
                list1.add(x.left);
            if (x.right != null)
                list1.add(x.right);
        }
        return list;
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        BinaryTree<Integer, Character> bst = new BinaryTree<>();
        for (int i = 0; i < 20; i++) {
            bst.insert(rand.nextInt(100), (char)('A' + i));
        }

        System.out.println("Level: " + bst.levelOrderTreeWalk() + "\n");
        System.out.println("In:    " + bst.inorderTreeWalk());
        System.out.println("In:    " + bst.inorderTreeWalk1() + "\n");
        System.out.println("Pre:   " + bst.preOrderTreeWalk());
        System.out.println("Pre:   " + bst.preOrderTreeWalk1());
        System.out.println("Pre:   " + bst.preOrderTreeWalk2() + "\n");
        System.out.println("Post:  " + bst.postOrderTreeWalk());
        System.out.println("Post:  " + bst.postOrderTreeWalk1() + "\n");

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
        System.out.println("Level: " + bst.levelOrderTreeWalk());

    }
}
