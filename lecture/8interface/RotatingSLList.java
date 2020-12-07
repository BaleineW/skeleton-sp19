public class RotatingSLList<Item> extends SLList<Item>{

    public void rotateRight() {
        Item i = removeLast();
        addFirst(i);
    }


    public static void main(String[] args){
        RotatingSLList<Integer> rsl = new RotatingSLList<>();
        rsl.addLast(10);
        rsl.addLast(15);
        rsl.addLast(19);
        rsl.addLast(20);
        rsl.rotateRight();
        rsl.print();
    }
}
