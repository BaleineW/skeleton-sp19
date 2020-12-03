public class SLList {
    /* define nested class IntNode */
    private static class IntNode{
        public int item;
        public IntNode next;
        public IntNode (int i, IntNode n){
            item = i;
            next = n;
        }
    }

    /* define SLList class, the first item (if exists) is sentinel.next */
    private IntNode sentinel;
    private int size;
    public SLList(){
        sentinel = new IntNode(-1, null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(-1, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public int size(){
        return size;
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst(){
        return sentinel.next.item;
    }

    public void addLast(int x){
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) p = p.next;
        p.next = new IntNode(x, null);
    }

    public static void main (String[] arg){
        SLList L = new SLList();
        L.addFirst(9);
        L.addFirst(8);
        L.addLast(11);
        System.out.println(L.size());
    }

}
