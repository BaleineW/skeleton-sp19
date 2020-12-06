public class SLList<T> implements List61B<T>{
    /* define nested class TNode */
    private class TNode{
        public T item;
        public TNode next;
        public TNode (T i, TNode n){
            item = i;
            next = n;
        }
    }

    /* define SLList class, the first item (if exists) is sentinel.next */
    private TNode sentinel;
    private int size;
    public SLList(){
        sentinel = new TNode(null, null);
        size = 0;
    }

    public T getFirst(){
        return sentinel.next.item;
    }

    public T get(int index){
        TNode p = sentinel.next;
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getLast(){
        return get(size-1);
    }

    public int size(){
        return size;
    }

    public void addFirst(T x){
        sentinel.next = new TNode(x, sentinel.next);
        size += 1;
    }

    public void addLast(T x){
        size += 1;
        TNode p = sentinel;
        while (p.next != null) p = p.next;
        p.next = new TNode(x, null);
    }

    public void insert(T x, int position) {

    }

}
