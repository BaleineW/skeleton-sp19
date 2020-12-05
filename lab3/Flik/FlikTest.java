import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int a = 128;
        int b = 128;
        int c = 500;
        assertTrue(Flik.isSameNumber(a, b));
        assertFalse(Flik.isSameNumber(a, c));
    }
}
