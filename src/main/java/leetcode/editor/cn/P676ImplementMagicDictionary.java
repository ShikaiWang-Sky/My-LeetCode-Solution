//设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单
//词存在于你构建的字典中。 
//
// 实现 MagicDictionary 类： 
//
// 
// MagicDictionary() 初始化对象 
// void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字
//符串互不相同 
// bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得
//所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
//输出
//[null, null, false, true, false, false]
//
//解释
//MagicDictionary magicDictionary = new MagicDictionary();
//magicDictionary.buildDict(["hello", "leetcode"]);
//magicDictionary.search("hello"); // 返回 False
//magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
//magicDictionary.search("hell"); // 返回 False
//magicDictionary.search("leetcoded"); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= dictionary.length <= 100 
// 1 <= dictionary[i].length <= 100 
// dictionary[i] 仅由小写英文字母组成 
// dictionary 中的所有字符串 互不相同 
// 1 <= searchWord.length <= 100 
// searchWord 仅由小写英文字母组成 
// buildDict 仅在 search 之前调用一次 
// 最多调用 100 次 search 
// 
// 
// 
// 
// Related Topics 字典树 哈希表 
// 👍 78 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Java：实现一个魔法字典
public class P676ImplementMagicDictionary {
    public static void main(String[] args) {
        MagicDictionary solution = new P676ImplementMagicDictionary().new MagicDictionary();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MagicDictionary {

        /**
         * Initialize your data structure here.
         */

        /**
         * 字典树的定义
         */
        class TreeNode{
            // 假设字典的元素都是小写字母
            private static final int SIZE = 26;
            TreeNode[] child;
            // 标记当前节点元素是否为单词的结尾
            boolean isWord;
            public TreeNode() {
                isWord = false;
                child = new TreeNode[SIZE];
            }
        }

        /**
         * 前序遍历字典树
         * @param root 根节点
         */
        public void preOrder(TreeNode root) {
            for (int i = 0; i < 26; i++) {
                // 如果某个子节点存在
                if (root.child[i] != null) {
                    System.out.println(i + 'a');
                    if (root.child[i].isWord) {
                        System.out.println("end");
                    }
                }
                // 依次访问子树
                preOrder(root.child[i]);
            }
        }

        /**
         * 插入字符串
         * @param root 根节点
         * @param word 待插入的字符串
         */
        public void insert(TreeNode root, String word) {
            char[] chars = word.toCharArray();
            TreeNode presentNode = root;
            for (char aChar : chars) {
                // 当前字符下标
                int wordPos = aChar - 'a';
                // 当前字符不在子树中, 新建子树
                if (presentNode.child[wordPos] == null) {
                    presentNode.child[wordPos] = new TreeNode();
                }
                // 如果在, 移动指针, 继续遍历
                presentNode = presentNode.child[wordPos];
            }
            // 标记单词末尾
            presentNode.isWord = true;
        }

        /**
         * 搜索字符串
         * @param root 根节点
         * @param word 待搜索字符串
         * @return 是否存在
         */
        public boolean search(TreeNode root, String word) {
            char[] chars = word.toCharArray();
            TreeNode presentNode = root;
            for (char aChar : chars) {
                // 当前字符下标
                int wordPos = aChar - 'a';
                // 当前字符不在子树中, 新建子树
                if (presentNode.child[wordPos] == null) {
                    return false;
                }
                // 如果在, 移动指针, 继续遍历
                presentNode = presentNode.child[wordPos];
            }
            return presentNode.isWord;
        }

        // 字符串长度, 字符串映射
        Map<Integer, ArrayList<String>> wordMap;

        public MagicDictionary() {
            wordMap = new HashMap<>();
        }

        /**
         * 建立字典树
         * @param dictionary 字符串数组
         */
        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                // 如果不存在长度的key, 新建一个value, 最后都执行add操作
                wordMap.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
            }
        }

        public boolean search(String searchWord) {
            // 如果不存在待查找单词的长度的key, 返回false
            if (!wordMap.containsKey(searchWord.length())) {
                return false;
            }
            for (String possibleWord : wordMap.get(searchWord.length())) {
                // 计算不同的字符的差值
                int wordDiff = countWordDiff(searchWord, possibleWord);
                if (wordDiff == 1) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 计算不同字符的长度是否为1
         * @param searchWord 待查找字符串
         * @param possibleWord 字典中的字符串
         * @return 只会返回0 1 2三种结果
         */
        private int countWordDiff(String searchWord, String possibleWord) {
            int diff = 0;
            for (int i = 0; i < searchWord.length(); i++) {
                if (searchWord.charAt(i) != possibleWord.charAt(i)) {
                    // 不同的字符大于一个, 直接跳出循环
                    if (++diff > 1) {
                        break;
                    }
                }
            }
            return diff;
        }
    }



/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
