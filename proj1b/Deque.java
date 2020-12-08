public interface Deque<T> {
    int size();

    default public boolean isEmpty() {
        return (size() == 0);
    }

    void printDeque();

    T get(int index);

    void addFirst(T x);

    void addLast(T x);

    T removeFirst();

    T removeLast();


}
