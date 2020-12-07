public class VengefulSLList<Item> extends SLList<Item>{

    SLList<Item> deletedItems;

    /* constructor */
    public VengefulSLList() {
        super();
        deletedItems = new SLList<>();
    }

    public  VengefulSLList(Item x){
        super(x);
        deletedItems = new SLList<>();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public void printLostItems() {
        deletedItems.print();
    }

    public static void main(String[] args){
        VengefulSLList<Integer> rsl = new VengefulSLList<>();
        rsl.addLast(10);
        rsl.addLast(15);
        rsl.addLast(19);
        rsl.addLast(20);
        rsl.removeLast();
        rsl.removeLast();
        rsl.print();
        System.out.println("The fallen are: ");
        rsl.printLostItems();
    }
}
