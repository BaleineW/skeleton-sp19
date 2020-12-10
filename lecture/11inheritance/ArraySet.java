import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean contain(T x){
        for (int i = 0; i < size; i++){
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x){
        if (!this.contain(x)){
            items[size] = x;
            size += 1;
        }
    }

    private class ArraySetIterator implements Iterator<T> {
        private int pos;
        public ArraySetIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return (pos < size);
        }
        public T next() {
            T returnItem = items[pos];
            pos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size; i += 1) {
            returnSB.append(items[i]);
            returnSB.append(", ");
        }
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        } // optimization
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArraySet<T> other = (ArraySet<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (T item : this) {
            if (!other.contain(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] arg){
        ArraySet<String> as = new ArraySet<>();
        as.add("horse");
        as.add("is");
        as.add("running");
        System.out.println(as.contain("horse"));
        Iterator<String> aseer = as.iterator();
        while (aseer.hasNext()){
            String i = aseer.next();
            System.out.println(i);
        }
        for (String i : as){
            System.out.println(i);
        }

        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(2);
        aset.add(3);
        aset.add(4);
        System.out.println(aset.toString());
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(4);
        aset2.add(2);
        aset2.add(3);
        System.out.println("does set(2,3,4) equal to set(4,2,3)? " + aset.equals(aset2));
    }

}
