import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort {
    @org.junit.Test
    public void testSort(){
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        selectionSort.sort(input);
        assertEquals(expected, input);
    }
    @org.junit.Test
    public void testFindSmallest(){
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";
        String res = input[selectionSort.findSmallest(input, 0)];
        assertEquals(expected, res);
    }

}
