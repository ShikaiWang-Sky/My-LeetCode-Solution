//给一非空的单词列表，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。 
//
// 示例 1： 
//
// 
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 
// 输入的单词均由小写字母组成。 
// 
//
// 
//
// 扩展练习： 
//
// 
// 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// 
// Related Topics 堆 字典树 哈希表 
// 👍 205 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：前K个高频单词
public class P692TopKFrequentWords {
    public static void main(String[] args) {
        Solution solution = new P692TopKFrequentWords().new Solution();
        // TO TEST
        String[] words = {"a","aa","aaa"};
        int k = 3;
        System.out.println(solution.topKFrequent(words, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> countFreq = new HashMap<>();
            // kv键值对存储word和词频
            for (String word : words) {
                // 相同词频 + 1, 否则默认0
                countFreq.put(word, countFreq.getOrDefault(word, 0) + 1);
            }

            // 实现桶排序
            Trie[] buckets = new Trie[words.length];

            for (Map.Entry<String, Integer> entry : countFreq.entrySet()) {
                String word = entry.getKey();
                int freq = entry.getValue();
                // 根据词频分组, 当前词频的桶不存在, 则先建立一个字典树桶
                if (buckets[freq] == null) {
                    buckets[freq] = new Trie();
                }
                // 加入桶中
                buckets[freq].addWord(word);
            }

            // 存储word
            List<String> ans = new LinkedList<>();
            // 倒序遍历, 从高到低
            for (int i = buckets.length - 1; i >= 0; i--) {
                if (buckets[i] != null) {
                    // 存储桶中的单词
                    List<String> temp = new LinkedList<>();
                    // list是引用传递
                    buckets[i].getWords(buckets[i].root, temp);
                    // 如果当前词频的个数小于k
                    if (temp.size() < k) {
                        // 当前词频的词全部加入
                        ans.addAll(temp);
                        // 更新k
                        k = k - temp.size();
                    }
                    // 如果当前词频大于k
                    else {
                        // 加入前k个
                        for (int j = 0; j <= k - 1; j++) {
                            ans.add(temp.get(j));
                        }
                        break;
                    }
                }
            }
            return ans;
        }
    }

    class Trie {
        class TrieNode {
            TrieNode[] child;
            String word;

            TrieNode() {
                this.child = new TrieNode[26];
                this.word = null;
            }
        }

        TrieNode root = new TrieNode();

        public void addWord (String word) {
            TrieNode currentNode = root;
            for (char c : word.toCharArray()) {
                if (currentNode.child[c - 'a'] == null) {
                    currentNode.child[c - 'a'] = new TrieNode();
                    currentNode = currentNode.child[c - 'a'];
                }
            }
            // 遍历完字符串中的字符, 在最后一个节点处存储word
            currentNode.word = word;
        }

        public void getWords(TrieNode root, List<String> ans) {
            if (root == null) {
                return;
            }
            // 如果当前node存储了word
            if (root.word != null) {
                ans.add(root.word);
            }
            // 遍历子节点, 递归
            for (int i = 0; i < 26; i++) {
                if (root.child[i] != null) {
                    getWords(root.child[i], ans);
                }
            }

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
