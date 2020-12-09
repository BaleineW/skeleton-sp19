import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String s, int x) {
        name = s;
        size = x;
    }

    public int compareTo(Dog otherDog) {
        return this.size - otherDog.size;
    }

    /*
    public int compareTo(Object o) {
        Dog otherDog = (Dog) o;
        if (this.size < otherDog.size) {
            return -1;
        } else if (this.size == otherDog.size) {
            return 0;
        }
        return 1;
    }*/

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
}
