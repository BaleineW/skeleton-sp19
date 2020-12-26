package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> indexes;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        indexes = new HashMap<>();
    }
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        indexes.put(item, size() - 1);
        upper(size() - 1);
    }
    @Override
    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return indexes.containsKey(item);
    }
    private boolean isEmpty() {
        return size() == 0;
    }
    @Override
    public int size() {
        return items.size();
    }
    @Override
    public T getSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).getItem();
    }
    @Override
    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T toRemove = items.get(0).getItem();
        swap(0, size()-1);
        items.remove(size()-1);
        indexes.remove(toRemove);
        lower(0);
        return toRemove;
    }
    @Override
    public void changePriority(T item, double priority) {
        if (isEmpty() || !contains(item)) {
            throw new NoSuchElementException();
        }
        int index = indexes.get(item);
        double prevPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (prevPriority < priority) {
            lower(index);
        } else {
            upper(index);
        }
    }
    private int parent(int index) {
        if (index == 0) {
            return index;
        } else {
            return (index - 1) / 2;
        }
    }
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    private int rightChild(int index) {
        return 2 * index + 2;
    }
    private void swap(int i, int j) {
        PriorityNode tmp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, tmp);
        indexes.put(items.get(i).getItem(), i);
        indexes.put(items.get(j).getItem(), j);
    }
    private void upper(int index) {
        int parent = parent(index);
        if (index > 0 && items.get(index).compareTo(items.get(parent)) < 0) {
            swap(index, parent);
            upper(parent);
        }
    }
    private void lower(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);
        if (left < size() && items.get(left).compareTo(items.get(index)) < 0) {
            smallest = left;
        }
        if (right < size() && items.get(right).compareTo(items.get(index)) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            lower(smallest);
        }
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }
    }
}
