public class AList<Item> implements List61B<Item>{
    private Item[] items;
    private int size;
    private static int RFACTOR = 2;

    public AList() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    @Override
    public void addFirst(Item x) {
        insert(x, 0);
    }
    @Override
    public void addLast(Item x) {
        if (size == items.length){
            resize(size * RFACTOR);
        }
        items[size] = x;
        size += 1;
    }
    @Override
    public Item getFirst() {
        return get(0);
    }
    @Override
    public Item getLast() {
        return items[size];
    }
    @Override
    public Item get(int i) {
        return items[i];
    }
    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item x = items[size];
        items[size] = null;
        return x;
    }
    @Override
    public void insert(Item x, int position) {
        Item[] a = (Item[]) new Object[items.length + 1];
        System.arraycopy(items, 0, a, 0, position);
        a[position] = x;
        System.arraycopy(items, position, a, position+1, items.length-position);
        items = a;
    }
    @Override
    public int  size() {
        return size;
    }
}