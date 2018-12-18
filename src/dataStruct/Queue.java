package dataStruct;

import java.util.Iterator;

/**
 * 使用链表的方式实现队列
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/11/24
 * @version 8.0
 */
public class Queue<E> implements Iterable<E> {

    private class Node {
        E item;                     // 结点的值
        Node next;                  // 指向的下一个结点
        public Node(E item) {
            this.item = item;
        }

        public String toString() {
            return "Node: " + item;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int N;

    /**
     * 将元素添加到队尾，该元素由关键字t构造
     * @param item 待添加元素的关键字
     */
    public void enqueue(E item) {
        Node x = new Node(item);
        if (tail == null) {               // 队列为空
            head = x;
            tail = x;
            return;
        }
        tail.next = x;                    // 队列不为空
        tail = x;
        N++;
    }

    /**
     * 将队首元素移除队列
     * @return 队首元素，若队列为空，返回null
     */
    public E dequeue() {
        if (head == null)
            throw new NullPointerException("Queue is empty.");
        E item = head.item;
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        N--;
        return item;
    }

    /**
     * 判断队列是否为空
     * @return 返回队列中元素的状态，若为空，返回true；反之，返回false
     */
    public boolean isEmpty() {
        return head == null;
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
                return dequeue();
            }

            public void remove() {
                throw new RuntimeException();
            }
        };
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        queue.enqueue(20);
        queue.enqueue(30);
        for (Integer i : queue) {
            System.out.println(i);
        }
    }
}
