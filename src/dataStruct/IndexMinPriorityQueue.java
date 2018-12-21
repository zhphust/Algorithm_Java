package dataStruct;

import java.util.Arrays;
import java.util.Random;

/**
 * 最小索引优先队列（对象的索引和对象一一对应）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/9
 * @version 8.0
 */
public class IndexMinPriorityQueue<Key extends Comparable<Key>> {
    // 索引二叉堆，存储对象的索引，下标从1开始，连续存放，
    // 堆上浮和下沉操作的是对象的索引，其实比较的是索引相关联的对象
    private int pq[];
    // 索引的逆序，数组索引是对象索引，和对象数组位置一一对应，满足pq[qp[i]] = qp[pq[i]] = i;
    private int qp[];
    // 存储对象的引用，可以不连续存放，对象只用作比较，位置不变，数组索引就是对象的索引
    private Key[] keys;
    // 堆的大小
    private int size;

    @SuppressWarnings("unchecked")
    public IndexMinPriorityQueue(int max) {
        pq = new int[max + 1];
        pq[0] = -1;
        qp = new int[max];
        keys = (Key[]) new Comparable[max];
        Arrays.fill(qp, -1);             // 索引的逆序初始化为-1

        size = 0;
    }

    // 插入一个对象
    public void insert(int index, Key key) {
        if (!contains(index)) {                 // 不包含该索引
            size++;
            pq[size] = index;
            qp[index] = size;
            keys[index] = key;
            swim(size);                          // 上浮
        } else {                                 // 包含该索引
            changeKey(index, key);
        }
    }

    // 上浮，处理的是pq的索引
    private void swim(int k) {
        while (k > 1 && keys[pq[k/2]].compareTo(keys[pq[k]]) > 0) {
            swap(k, k/2);
            k /= 2;
        }
    }

    // 下沉
    private void sink(int k) {
        int min = k;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= size && keys[pq[left]].compareTo(keys[pq[min]]) < 0)
            min = left;
        if (right <= size && keys[pq[right]].compareTo(keys[pq[min]]) < 0)
            min = right;
        if (min != k) {
            swap(min, k);
            sink(min);
        }
    }

    public void changeKey(int index, Key key) {
        if (!contains(index)) {
            insert(index, key);
            return;
        }
        Key oldKey = keys[index];
        keys[index] = key;
        if (key.compareTo(oldKey) < 0) {
            swim(qp[index]);                           // 处理的是pq的索引
        } else if (key.compareTo(oldKey) > 0) {
            sink(qp[index]);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // 检查是否包含某个索引
    public boolean contains(int index) {
        return qp[index] != -1;
    }

    private void swap(int i, int j) {
        // 更新pq
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        // 同时需更新qp
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    /**
     * 删除最小对象
     * @return 最小对象的索引
     */
    public int delMin() {
        int index = pq[1];
        delete(index);
        return index;
    }

    public Key delete(int index) {
        if (!contains(index)) {
            return null;
        }

        Key key = keys[index];
        int k = qp[index];

        swap(k, size--);
        if (keys[pq[k]].compareTo(key) < 0)
            swim(k);
        else if (keys[pq[k]].compareTo(key) > 0)
            sink(k);

        keys[pq[size+1]] = null;
        qp[pq[size+1]] = -1;
        pq[size+1] = -1;

        return key;
    }

    public Key keyOf(int index) {
        return keys[index];
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 1; i <= size; i++) {
            String ss = pq[i] + ":" + keys[pq[i]] + " ";
            s.append(ss);
        }
        s.deleteCharAt(s.length()-1);
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        IndexMinPriorityQueue<Character> mpq = new IndexMinPriorityQueue<>(20);
        for (int i = 0; i < 20; i++) {
            mpq.insert(i, (char)('A' + rand.nextInt(26)));
        }

        mpq.changeKey(7, 'K');
        mpq.changeKey(1, 'M');
        System.out.println(mpq);
        System.out.println(mpq.minKey());
        System.out.println(mpq.delMin());
        System.out.println(mpq.delete(8));
        System.out.println(mpq.delMin());
        System.out.println(mpq.minIndex());
        System.out.println(mpq.keyOf(4));
        System.out.println(mpq.delete(7));

        System.out.println("======");
        while (mpq.size() != 0) {
            int x = mpq.minIndex();
            System.out.println(mpq.keyOf(x));
            mpq.delete(x);
        }
    }
}
