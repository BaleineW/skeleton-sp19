public class SLList<T> extends Object implements List61B<T>{
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

    public SLList(T x){
        sentinel = new TNode(null, null);
        sentinel.next = new TNode(x, null);
        size = 1;
    }

    @Override
    public T getFirst(){
        return sentinel.next.item;
    }

    @Override
    public T get(int index){
        TNode p = sentinel.next;
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public TNode getLastNode() {
        TNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    @Override
    public T getLast(){
        TNode back = getLastNode();
        return back.item;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void addFirst(T x){
        sentinel.next = new TNode(x, sentinel.next);
        size += 1;
    }
    @Override
    public void addLast(T x){
        size += 1;
        TNode p = sentinel;
        while (p.next != null) p = p.next;
        p.next = new TNode(x, null);
    }

    @Override
    public T removeLast() {
        TNode back = getLastNode();
        if (back == null) {
            return null;
        }
        size -= 1;
        TNode tmp = sentinel;
        while (tmp.next != back) {
            tmp = tmp.next;
        }
        tmp.next = null;
        return back.item;
    }

    @Override
    public void insert(T x, int position) {

    }

    @Override
    public void print() {
        for (TNode p = sentinel.next; p != null; p = p.next) {
            System.out.print(p.item + " ");
        }

        System.out.println();
    }


}
