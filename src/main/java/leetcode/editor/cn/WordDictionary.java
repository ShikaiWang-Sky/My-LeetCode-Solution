package leetcode.editor.cn;

public class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        char[] array =  word.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.child[array[i] - 'a'] == null) {
                current.child[array[i] - 'a'] = new TrieNode();
            }
            current = current.child[array[i] - 'a'];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return searchHelp(word, root);
    }

    private boolean searchHelp(String word, TrieNode root) {
        char[] array = word.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            // 对于 . ,递归判断所有不为空的孩子
            if (array[i] == '.') {
                for (int j = 0; j < 26; j++) {
                    // 如果子树不为空, 则搜索后续的字符串是否存在(跳过通配符)
                    if (current.child[j] != null) {
                        if (searchHelp(word.substring(i + 1), current.child[j]))
                            return true;
                    }
                }
                // 后续子串不存在, 返回false
                return false;
            }
            if (current.child[array[i] - 'a'] == null)
                return false;
            current = current.child[array[i] - 'a'];
        }
        return current.isWord;
    }
}
