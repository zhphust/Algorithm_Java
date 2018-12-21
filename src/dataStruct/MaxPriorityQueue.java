package dataStruct;

import util.zhangpeng.hust.Swap;

import java.util.Arrays;
import java.util.Random;

/**
 * 最大堆实现的最大优先队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public MaxPriorityQueue() {
        this(0);
    }

    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(int max) {
        heap = (T[])new Comparable[max + 1];
        heap[0] = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(T[] array) {
        heap = (T[]) new Comparable[array.length + 1];                  // 堆的下标从1开始，便于计算
        heap[0] = null;
        for (int i = 0; i < array.length; i++) {
            heap[i + 1] = array[i];
        }
        buildHeap();
    }

    private void buildHeap() {
        size = heap.length - 1;
        for (int i = size / 2; i >= 1; i--) {
            // 维护堆的性质
            sink(i);
        }
    }
    private void sink(int i) {
        // 非递归方式
        while (true) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int max = i;
            if (left <= size && heap[max].compareTo(heap[left]) < 0)
                max = left;
            if (right <= size && heap[max].compareTo(heap[right]) < 0)
                max = right;
            if (max == i)
                break;
            else  {
                Swap.swap(heap, i, max);
                i = max;
            }
        }
        // 递归方式
        /*int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        // 孩子节点一定要和堆的长度而不是数组长度比较，
        if (left <= size && heap[max].compareTo(heap[left]) < 0)
            max = left;
        if (right <= size && heap[max].compareTo(heap[right]) < 0)
            max = right;
        if (max != i) {
            Swap.swap(heap, i, max);
            sink(max);                       //  递归下沉以维护堆的性质
        }*/
    }

    // 插入元素
    @SuppressWarnings("unchecked")
    public void insert(T element) {
        size += 1;
        if (size == heap.length) {
            T[] temp = (T[]) new Comparable[size * 2];
            System.arraycopy(heap, 0, temp, 0, size);
            heap = temp;
        }
        heap[size] = element;
        swim(size);
    }
    // 上浮
    private void swim(int i) {
        while (i > 1 && heap[i/2].compareTo(heap[i]) < 0) {
            Swap.swap(heap, i/2, i);
            i /= 2;
        }
    }

    public T max() {
        return heap[1];
    }

    @SuppressWarnings("unchecked")
    public T delMax() {
        if (isEmpty()) {
            throw new NullPointerException("Heap is null.");
        }
        T max = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        sink(1);

        if (size == heap.length / 4) {       // 调整堆的大小
            T[] temp = (T[])new Comparable[heap.length / 4 + 1];
            System.arraycopy(heap, 0, temp, 0, size +1);
            heap = temp;
        }

        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T delete(int i) {
        T element = heap[i];
        heap[i] = heap[size];
        heap[size] = null;
        size--;

        if (heap[i].compareTo(element) <= 0) {
            sink(i);
        } else {
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

    @SuppressWarnings("unchecked")
    public T[] heapSort() {
        T[] list = (T[]) new Comparable[size];
        int i = size-1;
        while (size != 1) {
            list[i--] = heap[1];
            // 将堆中的根元素和堆中的最后一个元素交换
            Swap.swap(heap, 1, size);
            heap[size] = null;
            size--;
            sink(1);
        }
        list[i] = heap[1];
        return list;
    }

    @Override
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
        MaxPriorityQueue<Integer> heap = new MaxPriorityQueue<>(a);
        System.out.println(heap);
        heap.insert(90);
        heap.delete(3);
        heap.delete(2);
        System.out.println(heap);
        System.out.println(Arrays.toString(heap.heapSort()));
    }
}
