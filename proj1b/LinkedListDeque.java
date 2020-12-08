public class LinkedListDeque<T> implements Deque<T> {
    private class TNode{
        public T item;
        public TNode prev;
        public TNode next;
        public TNode (T x, TNode p, TNode n){
            item = x;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void printDeque(){
        TNode node = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public T get(int index){
        if (size - 1 < index) return null;
        TNode node = sentinel.next;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.item;
    }

    @Override
    public void addFirst(T x){
        TNode node = new TNode(x, null, null);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    @Override
    public void addLast(T x){
        TNode node = new TNode(x, null, null);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode node = sentinel.next;
        node.next.prev = sentinel;
        sentinel.next = node.next;
        size -= 1;
        return node.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode node = sentinel.prev;
        node.prev.next = sentinel;
        sentinel.prev = node.prev;
        size -= 1;
        return node.item;
    }

}
