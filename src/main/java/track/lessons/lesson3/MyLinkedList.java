package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Queue, Stack {


    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private Node head;
    private Node tail;

    public MyLinkedList() {

    }

    @Override
    public void enqueue(int value) {
        this.add(value);
    }

    @Override
    public int dequeu() {
        return this.remove(0);
    }

    @Override
    public void push(int value) {
        this.add(value);
    }

    @Override
    public int pop() {
        return this.remove(length - 1);
    }

    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    @Override
    public void add(int item) {
        if (tail != null) {
            tail = new Node(tail, null, item);
            tail.prev.next = tail;
        } else {
            head = new Node(null, null, item);
            tail = head;
        }
        length++;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= length) {
            throw new NoSuchElementException();
        } else {
            Node node = find_Node(idx);
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            length--;
            return node.val;
        }
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= length) {
            throw new NoSuchElementException();
        } else {
            Node node = find_Node(idx);
            return node.val;
        }
    }

    private Node find_Node(int idx) {
        Node node;
        if (idx <= length / 2) {
            node = head;
            while (idx > 0) {
                node = node.next;
                idx--;
            }
        } else {
            node = tail;
            int is = length - idx - 1;
            while (is > 0) {
                node = node.prev;
                is--;

            }
        }
        return node;
    }

    @Override
    public int size() {
        return length;
    }
}