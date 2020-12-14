import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {

    @Test
    public void testSizeOf() {
        UnionFind lst = new UnionFind(10);
        assertEquals(1, lst.sizeOf(3));
        assertEquals(1, lst.sizeOf(7));
    }

    @Test
    public void testBasics() {
        UnionFind lst = new UnionFind(10);
        lst.union(1, 2);
        lst.union(3, 4);
        lst.union(1, 5);
        lst.union(6, 7);
        lst.union(2, 3);
        assertEquals(-5, lst.parent(1));
        assertEquals(5, lst.sizeOf(2));
        assertEquals(3, lst.parent(4));
        assertEquals(6, lst.parent(7));
        assertEquals(-2, lst.parent(6));
        assertEquals(1, lst.sizeOf(8));
        assertTrue(lst.connected(2, 5));
        assertFalse(lst.connected(7, 1));
    }

}
