package dataStruct;

import util.zhangpeng.hust.Swap;
import java.util.Arrays;
import java.util.Random;

/**
 * 最小堆
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] array;
    private int heapSize = 0;

    public MinHeap() {
        array = (T[])new Comparable[1];
        array[0] = null;
    }

    public MinHeap(int max) {
        array = (T[])new Comparable[max+1];
        array[0] = null;
    }

    public MinHeap(T[] a) {
        array = (T[])new Comparable[a.length+1];
        array[0] = null;                                    // 堆的索引为0的位不使用
        for (int i = 0; i < a.length; i++) {
            array[i+1] = a[i];
        }
        buildHeap();                                         // 建堆
    }

    private void buildHeap() {
        /*
        * 在一个初始数组中，数组后一半默认已经满足堆的性质，
        * 因此只需按逆序，从索引为 heapSize/2 的元素开始，
        * 对每个元素逐级下沉，最终构建出整个堆。
        */
        heapSize = array.length - 1;
        for (int i = heapSize /2; i >= 1; i--) {
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
        if (left <= heapSize && array[left].compareTo(array[min]) < 0)
            min = left;
        if (right <= heapSize && array[right].compareTo(array[min]) < 0)
            min = right;
        if (min != i) {
            Swap.swap(array, min, i);
            sink(min);                       //  递归，下沉以维护堆的性质
        }
    }

    /**
     * 向堆中插入元素
     * @param element: 要插入的元素
     */
    @SuppressWarnings("unchecked")
    public void insert(T element) {
        heapSize += 1;
        if (heapSize == array.length) {
            T[] temp = (T[]) new Comparable[heapSize*2];
            System.arraycopy(array, 0, temp, 0, heapSize);
            array = temp;
        }
        array[heapSize] = element;
        swim(heapSize);
    }

    private void swim(int i) {      // 上浮
        while (i > 1 && array[i/2].compareTo(array[i]) > 0) {
            Swap.swap(array, i, i/2);
            i /= 2;
        }
    }

    /**
     * 获得（不去除）关键字最小元素
     * @return 获得关键字最小元素
     */
    public T min() {
        return array[1];
    }

    /**
     * 去掉并获得关键字最小元素
     * @return 关键字最小元素
     */
    public T delMin() {
        if (isEmpty()) {
            throw new NullPointerException("Heap is null.");
        }

        T min = array[1];
        array[1] = array[heapSize];
        array[heapSize] = null;
        heapSize--;
        sink(1);                           // 下沉

        if (heapSize == array.length / 4) {
            T[] temp = (T[])new Comparable[array.length / 4 + 1];
            System.arraycopy(array, 0, temp, 0, heapSize+1);
            array = temp;
        }

        return min;
    }

    /**
     * 判断堆是否为空
     * @return 布尔值
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    public T delete(int i) {
        T element = array[i];
        array[i] = array[heapSize];
        array[heapSize] = null;
        heapSize--;
        if (element == array[i])                             // 该堆末尾元素和待删除元素相等
            return element;
        if (array[i].compareTo(element) > 0) {                              // 新占位的元素变小，下沉
            sink(i);
        } else {                                                            // 上浮
            swim(i);
        }

        if (heapSize == array.length / 4) {
            T[] temp = (T[])new Comparable[array.length / 4 + 1];
            System.arraycopy(array, 0, temp, 0, heapSize+1);
            array = temp;
        }

        return element;
    }

    public int size() {
        return heapSize;
    }

    /**
     * 堆排序（降序）
     */
    public T[] heapSort() {
        T[] list = (T[]) new Comparable[heapSize];
        int i = 0;
        while (heapSize != 1) {
            list[i++] = array[1];
            // 将堆中的根元素和堆中的最后一个元素交换
            Swap.swap(array, 1, heapSize);
            array[heapSize] = null;
            heapSize--;
            sink(1);
        }
        list[i++] = array[1];
        return list;
    }

    public String toString() {
        return Arrays.toString(array);
    }


    public static void main(String[] args) {
        Random random = new Random(47);
        int N = 10;
        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
        MinHeap<Integer> heap = new MinHeap<>(a);
        System.out.println(heap);
        heap.insert(10);
        heap.delete(1);
        heap.delete(5);
        heap.delMin();
        heap.delete(3);
        heap.delete(2);
        heap.delMin();
        System.out.println(heap);
        System.out.println(Arrays.toString(heap.heapSort()));
    }
}
