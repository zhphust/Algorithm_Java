package dataStruct;

import java.util.Iterator;

/**
 * 用链表的方式实现栈
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */

public class Stack<E> implements Iterable<E> {

    private class Node {
        E item;                     // 结点的值
        Node next;               // 指向的下一个结点

        Node(E item) {
            this.item = item;
        }
        public String toString() {
            return "Node: " + item;
        }
    }

    private Node head;           // 栈顶元素
    private int N;               // 元素个数

    /**
     * 判断栈是否为空
     * @return 返回栈中元素的状态，若为空，返回true；反之，返回false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 添加一个元素到栈中，该元素是由关键字t构造的
     * @param item 待构造元素的关键字
     */
    public void push(E item) {
        Node x = new Node(item);
        x.next = head;
        head = x;
        N++;
    }

    /**
     * 将栈顶元素弹出
     * @return 返回栈顶元素，若栈为空，返回null
     */
    public E pop() {
        if (isEmpty()) {
            throw new NullPointerException("Stack is empty");
        }
        E item = head.item;
        head = head.next;
        N--;
        return item;
    }

    public int size() {
        return N;
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
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.push(20);
        stack.push(30);
        for (Integer i : stack) {
            System.out.println(i);
        }
    }
}
