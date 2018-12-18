package dataStruct;

import java.util.Iterator;

/**
 * 数组的方式实现栈
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */

public class StackArray<E> implements Iterable<E> {
    private Object[] stack;                     // 数组实现
    private int top;                            // 指向的是下一个将要插入元素的位置

    /**
     * 构造一个具有默认容量为2的栈
     */
    public StackArray() {
        stack = new Object[2];
        this.top = 0;
    }

    /**
     * 构造一个具有指定容量的栈
     * @param size 容量的大小
     */
    public StackArray(int size) {
        stack = new Object[size];
        this.top = 0;
    }

    /**
     * 根据一个已有的数组，构造一个具有相同元素和容量的栈
     * @param a 现有数组
     */
    public StackArray(E[] a) {
        stack = new Object[a.length];
        System.arraycopy(a, 0, stack, 0, a.length);
        top = a.length;
    }

    /**
     * 此方法将元素添加到栈/数组中。
     * 如果数组已满，将数组容量加倍
     * @param item 要添加到栈/数组中的元素
     */
    public void push(E item) {
        if (top == stack.length) {
            Object[] temp = new Object[top*2];
            System.arraycopy(stack, 0, temp, 0, top);
            stack = temp;
        }
        stack[top++] = item;
    }

    /**
     * 从栈中弹出最近添加的元素。
     * 如果数组中的元素是数组容量的1/4，将数组的容量减半
     * @return 从栈中弹出的元素
     */
    @SuppressWarnings("unchecked")
    public E pop() {
        if (top == 0) {
            throw new NullPointerException("Stack is empty");
        }
        if (top == stack.length / 4) {
            Object[] temp = new Object[stack.length / 2];
            System.arraycopy(stack, 0, temp, 0, top);
            stack = temp;
        }
        E result = (E)stack[--top];
        stack[top] = null;
        return result;
    }

    /**
     * 检查栈是否为空
     * @return 返回栈的状态，若为空，返回true；反之，返回false
     */
    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            public boolean hasNext() {
                return !isEmpty();
            }

            public E next() {
                return pop();
            }

            public void remove() {
                throw new RuntimeException();
            }
        };
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8,};
        StackArray<Integer> stack = new StackArray<>(a);
        stack.push(9);
        stack.push(10);
        for (Integer i : stack) {
            System.out.println(i);
        }
    }
}
