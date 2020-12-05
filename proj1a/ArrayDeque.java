public class ArrayDeque<T> {
    private T[] items;
    private int first;
    private int last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 1;
        last = -1;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T get(int index) {
        if (size + 1 < index) {
            return null;
        }
        int newIndex = (first + index) % items.length;
        return items[newIndex];
    }

    public void printDeque() {
        int index = first;
        for (int i = 0; i < size; i++){
            System.out.print(items[index] + " ");
            index = (index + 1) % items.length;
        }
        System.out.println();
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int index = first;
        for (int i = 0; i < size; i++){
            a[i] = items[index];
            index = (index + 1) % items.length;
        }
        items = a;
        first = 0;
        last = size - 1;
    }

    public void addFirst(T x) {
        if (size == items.length){
            resize(size * 2);
        }
        first = (first - 1 + items.length) % items.length;
        items[first] = x;
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length){
            resize(size * 2);
        }
        last = (last + 1) % items.length;
        items[last] = x;
        size += 1;
    }

    public T removeFirst() {
        if (items.length >= 16 && size * 4 < items.length){
            resize(items.length / 2);
        }
        T x = items[first];
        items[first] = null;
        first = (first + 1) % items.length;
        if (!isEmpty()) size -= 1;
        return x;
    }

    public T removeLast() {
        if (items.length >= 16 && size * 4 < items.length){
            resize(items.length / 2);
        }
        T x = items[last];
        items[last] = null;
        last = (last - 1 + items.length) % items.length;
        if (!isEmpty()) size -= 1;
        return x;
    }

    /* deep copy
    public ArrayDeque(ArrayDeque target) {
        items = (T[]) new Object[target.size];
        first = 0;
        size = target.size();
        last = size - 1;
        int index = target.first;
        for (int i = 0; i < size; i++){
            items[i] = (T) target.items[index];
            index = (index + 1) % target.items.length;
        }
    } */

}
