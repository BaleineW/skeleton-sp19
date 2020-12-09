import java.util.Comparator;

public class Maximizer {

    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;                 }}
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = {d1, d2, d3};
        Dog maxDog = (Dog) max(dogs);
        maxDog.bark();

        Comparator<Dog> nc = Dog.getNameComparator();
        if (nc.compare(d1, d2) > 0) {
            d1.bark();
        } else {
            d2.bark();
        }
    }

}
