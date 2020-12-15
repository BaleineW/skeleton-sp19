package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow!");
        } else {
            rb[last] = x;
            last = (last + 1) % capacity();
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow!");
        } else {
            T res = rb[first];
            first = (first + 1) % capacity();
            fillCount -= 1;
            return res;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow!");
        } else {
            return rb[first];
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        private int count;

        public ArrayRingBufferIterator() {
            pos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount();
        }

        @Override
        public T next() {
            T curr = rb[pos];
            pos = (pos + 1) % capacity();
            count += 1;
            return curr;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true;}
        if (o == null || o.getClass() != this.getClass()) { return false;}
        ArrayRingBuffer<T> o2 = (ArrayRingBuffer<T>) o;
        if (o2.fillCount() != this.fillCount()) { return false;}
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> oIterator = o2.iterator();
        while (thisIterator.hasNext() && oIterator.hasNext()) {
            if (thisIterator.next() != oIterator.next()) {
                return false;
            }
        }
        return true;
    }

}
