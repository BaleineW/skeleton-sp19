/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeOffByOneFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("/Users/jingjing/Java/cs61b/library-sp19-master/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int count = 0;

        while (!in.isEmpty()) {
            String word = in.readString();
            CharacterComparator cc = new OffByOne();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
                count += 1;
            }
        }
        System.out.println("In total "+count+" words.");
    }
}