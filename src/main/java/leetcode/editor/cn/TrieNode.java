package leetcode.editor.cn;

public class TrieNode {
    private final int SIZE = 26;
    TrieNode[] child;
    boolean isWord;
    public TrieNode() {
        isWord = false;
        child = new TrieNode[SIZE];
    }

}
