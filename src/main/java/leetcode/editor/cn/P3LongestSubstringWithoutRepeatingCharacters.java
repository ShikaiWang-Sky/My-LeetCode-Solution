//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4863 👎 0

package leetcode.editor.cn;

//Java：无重复字符的最长子串
public class P3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P3LongestSubstringWithoutRepeatingCharacters().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 哈希算法
         * 根据给定的字符计算出一个 int 类型的哈希值
         *
         * @param key 输入的字符
         * @return 哈希值
         */
        int hash(char key) {
            return key;
        }

        /**
         * 最优解: 哈希表 + 双指针
         *
         * @param s 字符串
         * @return 最长不重复子串的长度
         */
        public int lengthOfLongestSubstring(String s) {
            int len;
            if (s == null || (len = s.length()) == 0) {
                return 0;
            }
            // 最长不重复子串的长度, 左指针索引, 右指针索引
            int res = 0, left = 0, right = 0;
            // 1. 定义哈希表
            char[] chs = new char[128];
            // 2. 遍历字符串的所有字符
            while (right < len) {
                // 右指针字符
                char rightChar = s.charAt(right);
                // hash算法 => 按位与相当于 hash(rightChar) % chs.length
                char c = chs[hash(rightChar) & (chs.length - 1)];
                if (rightChar != c) {
                    // 未重复出现
                    right++;
                    // 将不重复字符记录到哈希表中
                    chs[hash(rightChar) & (chs.length - 1)] = rightChar;
                    // 记录子串长度, 并计算最大值
                    int size = right - left;
                    res = Math.max(res, size);
                } else {
                    // 重复出现
                    char leftChar = s.charAt(left++);
                    chs[hash(leftChar) & (chs.length - 1)] = '\u0000';
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
