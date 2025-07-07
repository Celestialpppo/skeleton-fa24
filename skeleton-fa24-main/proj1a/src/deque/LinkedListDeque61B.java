package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    public static class Node<T>{
        public T data;
        public Node<T> next;
        public Node<T> prev;
        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> sentinel;
    private int size;
    public LinkedListDeque61B() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node<T> newNode = new Node<>(x, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node<T> newNode = new Node<>(x, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> tempNode = sentinel.next;
        for (int i = 0; i < size(); i++) {
            returnList.add(tempNode.data);
            tempNode = tempNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        size--;
        Node<T> Temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return Temp.data;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        size--;
        Node<T> Temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return Temp.data;
    }

    @Override
    public T get(int index) {
        if (index <= 0 ){
            return null;
        } else if (index > size) {
            return null;
        }
        else{
            Node<T> tNode = sentinel;
            for (int i = 0; i < index; i++) {
                tNode = tNode.next;
            }
            return tNode.data;
        }
    }
//    private int t = 0;
//    private Node<T> tNode = sentinel;
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size ){
            return null;
        } else if (index == 0) {
            return sentinel.data;
        }
        Node<T> originalSentinel = sentinel;
        sentinel = sentinel.next;
        T result = getRecursive(index - 1);
        sentinel = originalSentinel;
        return result;
    }
}
