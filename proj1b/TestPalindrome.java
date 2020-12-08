import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the auto-grader might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String w1 = "beeb";
        String w2 = "perfect";
        assertTrue(palindrome.isPalindrome(w1));
        assertFalse(palindrome.isPalindrome(w2));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String w1 = "flake";
        String w2 = "perfect";
        String w3 = "racecar";
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(w1, cc));
        assertFalse(palindrome.isPalindrome(w2, cc));
        assertTrue(palindrome.isPalindrome(w3, cc));
    }

    @Test
    public void testIsPalindromeOffByN() {
        String w1 = "flake";
        String w2 = "perfect";
        String w3 = "ac";
        CharacterComparator cc = new OffByN(3);
        assertTrue(palindrome.isPalindrome(w1, cc));
        assertFalse(palindrome.isPalindrome(w2, cc));
        assertTrue(palindrome.isPalindrome(w3, cc));
    }


}