import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testAdd() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
            }

            assertEquals("Oh noooo!\nThis is bad:\n   actual deque " + sad1
                    + " not equal to " + ads1 + "!", ads1, sad1);
        }
    }


    @Test
    public void testRemove() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
            }
        }

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            int sad = 0;
            int ads = 0;

            if (numberBetweenZeroAndOne < 0.5) {
                sad = sad1.removeLast();
                ads = ads1.removeLast();
            } else {
                sad = sad1.removeFirst();
                ads = ads1.removeFirst();
            }

            assertEquals("Oh noooo!\nThis is bad:\n   actual removed value " + sad
                    + " not equal to " + ads + "!", ads, sad);
        }
    }

}
