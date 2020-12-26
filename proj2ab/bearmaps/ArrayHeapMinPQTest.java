package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ArrayHeapMinPQTest {

    @Test
    public void testBasic() {
        NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> ahpq = new ArrayHeapMinPQ<>();
        npq.add(8, 8);
        ahpq.add(8, 8);
        npq.add(1, 1);
        ahpq.add(1, 1);
        assertTrue(ahpq.contains(1));
        assertEquals(npq.getSmallest(), ahpq.getSmallest());
        assertFalse(ahpq.contains(1));
        npq.add(3, 3);
        npq.add(6, 6);
        ahpq.add(3, 3);
        ahpq.add(6, 6);
        assertEquals(npq.removeSmallest(), ahpq.removeSmallest());
        assertEquals(npq.getSmallest(), ahpq.getSmallest());
        npq.add(4, 4);
        npq.add(2, 2);
        npq.add(9, 9);
        npq.add(5, 5);
        ahpq.add(9, 9);
        ahpq.add(5, 5);
        ahpq.add(4, 4);
        ahpq.add(2, 2);
        assertEquals(npq.getSmallest(), ahpq.getSmallest());
        assertEquals(npq.removeSmallest(), ahpq.removeSmallest());
        assertEquals(npq.removeSmallest(), ahpq.removeSmallest());
        assertEquals(npq.removeSmallest(), ahpq.removeSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> ahpq = new ArrayHeapMinPQ<>();
        ahpq.add(8, 8);
        ahpq.add(1, 1);
        assertTrue(ahpq.contains(1));
        ahpq.add(3, 3);
        ahpq.add(6, 6);
        ahpq.changePriority(1, 10);
        assertEquals(3, (int)ahpq.removeSmallest());
    }
}
