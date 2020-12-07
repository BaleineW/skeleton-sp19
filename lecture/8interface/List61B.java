public interface List61B<Item> {

    public void addFirst(Item x);

    public void addLast(Item y);

    public Item getFirst();

    public Item getLast();

    public Item get(int i);

    public Item removeLast();

    public void insert(Item x, int position);

    public int  size();

    default public void print() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }


}