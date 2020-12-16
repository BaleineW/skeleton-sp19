import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        private Node(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }

    public BSTMap() {
    }
    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }
    @Override
    /* Returns the value to which the specified key is mapped */
    public V get(K key) {
        return get(root, key);
    }
    private V get(Node root, K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root.value;
        } else if (cmp < 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }
    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }
    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.size;
    }
    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }
    private Node put(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.value = value;
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }
    public void printInOrder() {
        for (int i = 0; i < size(); i++) {
            System.out.println(select(i).key + ": " + select(i).value);
        }
    }
    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> kSet = new HashSet<>();
        for (int i = 0; i < size(); i++) {
            kSet.add(select(i).key);
        }
        return kSet;
    }
    private Node select(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return select(root, i);
    }
    private Node select(Node root, int i) {
        if (root == null) {
            return null;
        }
        int l = size(root.left);
        if (i == l) {
            return root;
        } else if (i < l) {
            return select(root.left, i);
        } else {
            return select(root.right, i - 1 - l);
        }
    }
    @Override
    /* Removes the mapping for the specified key from this map if present. */
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V toRemove = get(key);
        root = remove(root, key);
        return toRemove;
    }
    @Override
    public V remove(K key, V value) {
        if (!containsKey(key) || !get(key).equals(value)) {
            return null;
        }
        root = remove(root, key);
        return value;
    }
    private Node remove(Node root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            if (root.right == null) {
                return root.left;
            }else if (root.left == null) {
                return root.right;
            } else {
                Node tmp = root;
                if (size(root.left) > size(root.right)) {
                    root = leftMost(root.right);
                    root.right = delLeftMost(root.right);
                    root.left = tmp.left;
                } else {
                    root = rightMost(root.left);
                    root.left = delRightMost(root.left);
                    root.right = tmp.right;
                }
            }
        } else if (cmp < 0) {
            root.left = remove(root.left, key);
        } else {
            root.right = remove(root.right, key);
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }
    private Node leftMost(Node root) {
        if (root.left == null) {
            return root;
        }
        return leftMost(root.left);
    }
    private Node delLeftMost(Node root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = delLeftMost(root.left);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }
    private Node rightMost(Node root) {
        if (root.right == null) {
            return root;
        }
        return rightMost(root.right);
    }
    private Node delRightMost(Node root) {
        if (root.right == null) {
            return root.left;
        }
        root.right = delRightMost(root.right);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Iterator<K> iterator() {
        return new BSTiterator(root);
    }
    private class BSTiterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTiterator(Node src) {
            while (src != null) {
                stack.push(src);
                src = src.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public K next() {
            Node curr = stack.pop();
            if (curr.right != null) {
                Node tmp = curr.right;
                while (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                }
            }
            return curr.key;
        }
    }
}
