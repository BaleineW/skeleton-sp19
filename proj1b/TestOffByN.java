import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offByN = new OffByN(3);

    // Your tests go here.
    @Test
    public void testOffByN() {
        assertTrue(offByN.equalChars('a', 'c'));
        assertTrue(offByN.equalChars('x', 'u'));
        assertFalse(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('t', 'b'));
    }
}
