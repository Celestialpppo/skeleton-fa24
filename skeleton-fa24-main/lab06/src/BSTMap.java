import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private int size;
    private Node root;
    private class Node{
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private Node get(K key){
            if (key == null){
                return null;
            }

            int cmp = key.compareTo(this.key);
            if (cmp < 0){
                return left == null ? null : this.left.get(key);
            }
            else if (cmp > 0) {
                return right == null ? null : this.right.get(key);
            }
            else {
                return this;
            }
        }
        private Node getLeftMin(Node node){
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
    }


    @Override
    public void put(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException("key == null");
        }

        if (root == null){
            root = new Node(key, value);
            size++;
            return;
        }

        Node current = root;
        Node parent;
        while (current != null){
            int cmp = key.compareTo(current.key);
            if (cmp < 0){
                parent = current;
                current = current.left;
                if (current == null){
                    parent.left = new Node(key, value);
                    size++;
                }
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
                if (current == null){
                    parent.right = new Node(key, value);
                    size++;
                }
            }
            else {
                current.value = value;
                return;
            }
        }
    }

    @Override
    public V get(K key) {
        if (root == null || key == null){
            return null;
        }

        Node node = root.get(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null){
            return false;
        }
        return root.get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        collectKeys(root, set);
        return set;
    }

    private void collectKeys(Node node, Set<K> set){
        if (node == null) return;
        collectKeys(node.left, set);
        set.add(node.key);
        collectKeys(node.right, set);
    }

    @Override
    public V remove(K key) {
        if (key == null) return null;
        Holder<V> removedValue = new Holder<>();
        root = remove(root, key, removedValue);
        if (removedValue.val != null) {
            size--;
        }
        return removedValue.val;
    }

    private Node remove(Node node, K key, Holder<V> removed) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key, removed);
        } else if (cmp > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            // 👇 找到要删除的节点，记录旧值
            removed.val = node.value;

            // 🍃 Case 1: 无左子树，直接返回右子树
            if (node.left == null) return node.right;

            // 🍂 Case 2: 无右子树，直接返回左子树
            if (node.right == null) return node.left;

            // 🌿 Case 3: 左右子树都存在
            Node successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = deleteMin(node.right);
        }

        return node;
    }

    // 找右子树中最小节点（后继）
    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 从右子树中删除最小节点（用于删除后继）
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    // 🌟 简单的容器类，用于保存删除的 value
    private static class Holder<T> {
        T val;
    }


    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
