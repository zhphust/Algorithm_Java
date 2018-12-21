package dataStruct;

import util.zhangpeng.hust.Swap;
import java.util.Arrays;
import java.util.Random;

/**
 * 由最小堆实现的最小优先队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */
public class MinPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public MinPriorityQueue() {
        this(0);
    }
    @SuppressWarnings("unchecked")
    public MinPriorityQueue(int max) {
        heap = (T[])new Comparable[max+1];
        heap[0] = null;
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public MinPriorityQueue(T[] a) {
        heap = (T[])new Comparable[a.length+1];
        heap[0] = null;                                    // 堆的索引为0的位不使用
        for (int i = 0; i < a.length; i++) {
            heap[i+1] = a[i];
        }
        buildHeap();                                         // 建堆
    }

    private void buildHeap() {
        /*
        * 在一个初始数组中，数组后一半默认已经满足堆的性质，
        * 因此只需按逆序，从索引为 size/2 的元素开始，
        * 对每个元素逐级下沉，最终构建出整个堆。
        */
        size = heap.length - 1;
        for (int i = size /2; i >= 1; i--) {
            // 维护堆的性质，下沉
            sink(i);
        }
    }

    private void sink(int i) {
         /*
         * 维护堆的性质（下沉）
         */
        int left = 2 * i;
        int right = 2 * i + 1;
        int min = i;
        // 孩子节点一定要和堆的长度而不是数组长度比较
        if (left <= size && heap[left].compareTo(heap[min]) < 0)
            min = left;
        if (right <= size && heap[right].compareTo(heap[min]) < 0)
            min = right;
        if (min != i) {
            Swap.swap(heap, min, i);
            sink(min);                       //  递归，下沉以维护堆的性质
        }
    }

    /**
     * 向堆中插入元素
     * @param element: 要插入的元素
     */
    @SuppressWarnings("unchecked")
    public void insert(T element) {
        size += 1;
        if (size == heap.length) {
            T[] temp = (T[]) new Comparable[size *2];
            System.arraycopy(heap, 0, temp, 0, size);
            heap = temp;
        }
        heap[size] = element;
        swim(size);                 // 上浮
    }

    // 上浮
    private void swim(int i) {
        while (i > 1 && heap[i/2].compareTo(heap[i]) > 0) {
            Swap.swap(heap, i, i/2);
            i /= 2;
        }
    }

    /**
     * 获得（不去除）关键字最小元素
     * @return 获得关键字最小元素
     */
    public T min() {
        return heap[1];
    }

    /**
     * 去掉并获得关键字最小元素
     * @return 关键字最小元素
     */
    @SuppressWarnings("unchecked")
    public T delMin() {
        if (isEmpty()) {
            throw new NullPointerException("Heap is null.");
        }

        T min = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        sink(1);                           // 下沉

        if (size == heap.length / 4) {       // 调整堆的大小
            T[] temp = (T[])new Comparable[heap.length / 4 + 1];
            System.arraycopy(heap, 0, temp, 0, size +1);
            heap = temp;
        }

        return min;
    }

    /**
     * 判断堆是否为空
     * @return 布尔值
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T delete(int i) {
        T element = heap[i];
        heap[i] = heap[size];
        heap[size] = null;
        size--;
        if (heap[i].compareTo(element) >= 0) {                              // 新占位的元素变小，下沉
            sink(i);
        } else {                                                            // 上浮
            swim(i);
        }

        if (size == heap.length / 4) {                       // 调整堆的大小
            T[] temp = (T[])new Comparable[heap.length / 4 + 1];
            System.arraycopy(heap, 0, temp, 0, size +1);
            heap = temp;
        }

        return element;
    }

    public int size() {
        return size;
    }

    /**
     * 堆排序
     */
    @SuppressWarnings("unchecked")
    public T[] heapSort() {
        T[] list = (T[]) new Comparable[size];
        int i = 0;
        while (size != 1) {
            list[i++] = heap[1];
            // 将堆中的根元素和堆中的最后一个元素交换
            Swap.swap(heap, 1, size);
            heap[size] = null;
            size--;
            sink(1);
        }
        list[i] = heap[1];
        return list;
    }

    public String toString() {
        return Arrays.toString(heap);
    }


    public static void main(String[] args) {
        Random random = new Random(47);
        int N = 20;
        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
        MinPriorityQueue<Integer> heap = new MinPriorityQueue<>(a);
        System.out.println(heap);
        heap.insert(10);
        heap.delete(5);
        heap.delMin();
        heap.delete(3);
        heap.delete(2);
        System.out.println(heap);
        System.out.println(Arrays.toString(heap.heapSort()));
    }
}
