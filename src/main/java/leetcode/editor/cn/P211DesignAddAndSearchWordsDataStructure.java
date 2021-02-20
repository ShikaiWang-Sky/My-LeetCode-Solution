//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 
//
// 实现词典类 WordDictionary ： 
//
// 
// WordDictionary() 初始化词典对象 
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '
//.' ，每个 . 都可以表示任何一个字母。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search","se
//arch"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 500 
// addWord 中的 word 由小写英文字母组成 
// search 中的 word 由 '.' 或小写英文字母组成 
// 最多调用 50000 次 addWord 和 search 
// 
// Related Topics 深度优先搜索 设计 字典树 回溯算法 
// 👍 208 👎 0

package leetcode.editor.cn;

//Java：添加与搜索单词 - 数据结构设计
public class P211DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
        WordDictionary solution = new P211DesignAddAndSearchWordsDataStructure().new WordDictionary();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class WordDictionary {

        class TrieNode {
            TrieNode[] child;
            private static final int SIZE = 26;
            boolean isWord;

            public TrieNode() {
                child = new TrieNode[SIZE];
                isWord = false;
            }

            public boolean isContainsKey(char c) {
                return child[c - 'a'] != null;
            }

            public TrieNode getChild(char c) {
                return child[c - 'a'];
            }

            public void putChild(char c, TrieNode newNode) {
                child[c - 'a'] = newNode;
            }
        }
        /**
         * Initialize your data structure here.
         */

        private class Trie {
            TrieNode root;
            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode currentNode = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!currentNode.isContainsKey(c)) {
                        currentNode.putChild(c, new TrieNode());
                    }
                    currentNode = currentNode.getChild(c);
                }
                currentNode.isWord = true;
            }

            public boolean dfs(TrieNode root, String word, int index) {
                if (index >= word.length()) {
                    return root.isWord;
                }
                char c = word.charAt(index);
                // 不是通配符, 遍历
                if (c != '.') {
                    // 如果子节点包含字符, 继续向下递归
                    if (root.isContainsKey(c)) {
                        return dfs(root.getChild(c), word, index + 1);
                    }
                    // 不包含字符, 返回false
                    return false;
                }
                // 是通配符
                for (TrieNode trieNode : root.child) {
                    // 如果子节点不为空并且继续遍历符合要求, 返回true
                    if (null != trieNode && dfs(trieNode, word, index + 1)) {
                        return true;
                    }
                }
                return false;
            }

            public boolean search (String word) {
                return dfs(root, word, 0);
            }
        }

        private Trie trie;
        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return trie.search(word);
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
