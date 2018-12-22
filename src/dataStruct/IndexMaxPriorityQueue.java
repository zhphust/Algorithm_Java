package dataStruct;

import java.util.Arrays;
import java.util.Random;

/**
 * 最大索引优先队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/22
 * @version 8.0
 */
public class IndexMaxPriorityQueue<Key extends Comparable<Key>> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int size;

    @SuppressWarnings("unchecked")
    public IndexMaxPriorityQueue(int maxN) {
        pq = new int[maxN + 1];
        pq[0] = -1;
        qp = new int[maxN];
        Arrays.fill(qp, -1);
        keys = (Key[]) new Comparable[maxN];
        size = 0;
    }

    public void insert(int index, Key key) {
        if (contains(index)) {
            changeKey(index, key);
            return;
        }
        if (size == pq.length - 1) {
            throw new RuntimeException("queue is full");
        }

        size++;
        pq[size] = index;
        qp[index] = size;
        keys[index] = key;

        swim(size);
    }

    public boolean contains(int index) {
        return qp[index] != -1;
    }

    public void changeKey(int index, Key key) {
        if (!contains(index)) {
            insert(index, key);
            return;
        }
        Key oldKey = keys[index];
        keys[index] = key;

        if (key.compareTo(oldKey) < 0) {
            sink(qp[index]);
        } else {
            swim(qp[index]);
        }

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size ==  0;
    }

    public int maxIndex() {
        return pq[1];
    }

    public Key maxKey() {
        return keys[pq[1]];
    }

    public int delMax() {
        int index = pq[1];
        delete(index);
        return index;
    }

    public Key delete(int index) {
        if (!contains(index))
            return null;

        Key key = keys[index];
        int k = qp[index];

        swap(k, size--);
        if (keys[pq[k]].compareTo(key) < 0) {
            sink(k);
        } else {
            swim(k);
        }

        qp[pq[size+1]] = -1;
        keys[pq[size+1]] = null;
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

    private void sink(int k) {
        int left = 2 * k;
        int right = 2 * k + 1;
        int max = k;

        if (left <= size && keys[pq[left]].compareTo(keys[pq[max]]) > 0) {
            max = left;
        }
        if (right <= size && keys[pq[right]].compareTo(keys[pq[max]]) > 0) {
            max = right;
        }
        if (max != k) {
            swap(k, max);
            sink(max);
        }
    }

    private void swim(int k) {
        while (k > 1 && keys[pq[k]].compareTo(keys[pq[k / 2]]) > 0) {
            swap(k,  k/2);
            k /= 2;
        }
    }

    private void swap(int i, int j) {
        // 更新pq
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        //pq位置变了，重新建立qp与pq的对应关系
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        IndexMaxPriorityQueue<Character> mpq = new IndexMaxPriorityQueue<>(20);
        for (int i = 0; i < 20; i++) {
            mpq.insert(i, (char)('A' + rand.nextInt(26)));
        }

        mpq.changeKey(7, 'K');
        mpq.changeKey(1, 'M');
        System.out.println(mpq);

        System.out.println(mpq.maxKey());
        System.out.println(mpq.delMax());
        System.out.println(mpq.delete(8));
        System.out.println(mpq.delMax());
        System.out.println(mpq.maxIndex());
        System.out.println(mpq.keyOf(4));
        System.out.println(mpq.delete(7));

        System.out.println("===============");
        while (mpq.size() != 0) {
            int x = mpq.maxIndex();
            System.out.print(mpq.keyOf(x) + " ");
            mpq.delete(x);
        }
    }
}
