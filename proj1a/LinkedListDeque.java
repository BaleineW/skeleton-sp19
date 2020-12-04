public class LinkedListDeque<T> {
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

    public LinkedListDeque(T x){
        sentinel = new TNode(null, null, null);
        TNode node = new TNode(x, sentinel, sentinel);
        sentinel.next = node;
        sentinel.prev = node;
        size = 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        TNode node = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public T get(int index){
        if (size - 1 < index) return null;
        TNode node = sentinel.next;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.item;
    }

    public void addFirst(T x){
        TNode node = new TNode(x, null, null);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T x){
        TNode node = new TNode(x, null, null);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
        size += 1;
    }

    public T removeFirst(){
        TNode node = sentinel.next;
        node.next.prev = sentinel;
        sentinel.next = node.next;
        size -= 1;
        return node.item;
    }

    public T removeLast(){
        TNode node = sentinel.prev;
        node.prev.next = sentinel;
        sentinel.prev = node.prev;
        size -= 1;
        return node.item;
    }

    /* using recursion to get */
    public T getRecursive(int index, TNode node){
        if (index == 0){
            return node.item;
        }
        return getRecursive(index-1, node.next);
    }

    public T getRecursive(int index){
        if (size - 1 < index) return null;
        return getRecursive(index, sentinel.next);
    }

    /* deep copy */
    public LinkedListDeque(LinkedListDeque target){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for (int i = 0; i < target.size; i++){
            addLast((T) target.get(i));
        }
    }
}
