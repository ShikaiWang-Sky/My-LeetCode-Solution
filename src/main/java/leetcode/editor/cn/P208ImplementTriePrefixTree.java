//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 523 👎 0

package leetcode.editor.cn;

//Java：实现 Trie (前缀树)
public class P208ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie solution = new P208ImplementTriePrefixTree().new Trie();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        class TrieNode {
            private TrieNode[] child;
            private static final int SIZE = 26;
            private boolean isWord;

            public TrieNode() {
                child = new TrieNode[SIZE];
                isWord = false;
            }
        }
        /**
         * Initialize your data structure here.
         */
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (currentNode.child[c - 'a'] == null) {
                    currentNode.child[c - 'a'] = new TrieNode();
                }
                currentNode = currentNode.child[c - 'a'];
            }
            currentNode.isWord = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieNode currentNode = root;
            for (char c : chars) {
                int charPos = c - 'a';
                if (currentNode.child[charPos] == null) {
                    return false;
                }
                currentNode = currentNode.child[charPos];
            }
            return currentNode.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TrieNode currentNode = root;
            for (char c : chars) {
                int charPos = c - 'a';
                if (currentNode.child[charPos] == null) {
                    return false;
                }
                currentNode = currentNode.child[charPos];
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
