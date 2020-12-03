import java.util.Formatter;
public class DLList {
    private static class IntNode{
        public int item;
        public IntNode prev;
        public IntNode next;
        public IntNode (int i, IntNode p, IntNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;
    public DLList(){
        sentinel = new IntNode(-1, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public DLList(int x){
        sentinel = new IntNode(-1, null, null);
        IntNode node = new IntNode(x, sentinel, sentinel);
        sentinel.next = node;
        sentinel.prev = node;
        size = 1;
    }

    public int size(){
        return size;
    }

    public int getFirst(){
        return sentinel.next.item;
    }

    public int getLast(){
        return sentinel.prev.item;
    }

    public void addFirst(int x){
        IntNode node = new IntNode(x, null, null);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(int x){
        IntNode node = new IntNode(x, null, null);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
        size += 1;
    }

    public void removeLast(){
        IntNode node = sentinel.prev;
        node.prev.next = sentinel;
        sentinel.prev = node.prev;
        size -= 1;
    }

    public static void main (String[] arg){
        DLList L = new DLList();
        L.addFirst(8);
        L.addLast(11);
        L.addLast(12);
        System.out.println(L.getFirst());
        System.out.println(L.getLast());
        L.removeLast();
        L.removeLast();
        System.out.println(L.getFirst());
        System.out.println(L.getLast());
    }
}

