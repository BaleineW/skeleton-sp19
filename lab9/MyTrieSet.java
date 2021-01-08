import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MyTrieSet implements TrieSet61B {

    private trieNode root;

    public MyTrieSet() {
        root = new trieNode('\0', false);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = null;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return false;
        }
        trieNode curr = root;
        trieNode next = null;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            next = curr.children.get(c);
            if (next == null) return false;
            curr = next;
        }
        return curr.isWord;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return;
        }
        trieNode curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new trieNode(c, false));
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0 || root == null) {
            throw new IllegalArgumentException();
        }
        List<String> results = new ArrayList<>();
        trieNode curr = root;
        for (int i = 0; i < prefix.length(); i += 1) {
            char c = prefix.charAt(i);
            curr = curr.children.get(c);
        }
        if (curr.isWord == true) results.add(prefix);
        for (trieNode nextNode : curr.children.values()) {
            if (nextNode != null) {
                keysWithPrefix(results, prefix, nextNode);
            }
        }
        return results;
    }

    private void keysWithPrefix(List<String> results, String prefix, trieNode node) {
        if (node.isWord) results.add(prefix + node.ch);
        for (trieNode nextNode : node.children.values()) {
            if (nextNode != null) {
                keysWithPrefix(results, prefix + node.ch, nextNode);
            }
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie*/
    public String longestPrefixOf(String key) {
        if (key == null || key.length() == 0 || root == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder lonestPrefix = new StringBuilder();
        trieNode curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                return lonestPrefix.toString();
            } else {
                lonestPrefix.append(c);
                curr = curr.children.get(c);
            }
        }
        return lonestPrefix.toString();
    }

    private class trieNode {
        private char ch;
        private boolean isWord;
        private Map<Character, trieNode> children;

        public trieNode(char ch, boolean isWord) {
            this.ch = ch;
            this.isWord = isWord;
            this.children = new HashMap<>();
        }
    }
}
