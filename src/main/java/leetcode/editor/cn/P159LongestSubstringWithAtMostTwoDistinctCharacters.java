//给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。 
//
// 示例 1: 
//
// 输入: "eceba"
//输出: 3
//解释: t 是 "ece"，长度为3。
// 
//
// 示例 2: 
//
// 输入: "ccaabbb"
//输出: 5
//解释: t 是 "aabbb"，长度为5。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 93 👎 0

package leetcode.editor.cn;

/**
 * @author Wang
 */
//Java：至多包含两个不同字符的最长子串
public class P159LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new P159LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
        // TO TEST
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("abaccc"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int length = s.length();
            int[] map = new int[128];
            int right = 0, left = 0;
            // count 为不同字符的数量
            int count = 0;
            while (right < length) {
                /* 右侧新字符进入窗口,
                如果此时在map数组中没有记录, 则对应的ASCII码的索引处存放的个数加一
                同时count++
                 */
                if (map[s.charAt(right++)]++ == 0) {
                    count++;
                }
                /* 如果新字符进入窗口后使得 不同字符数量大于2
                则左侧窗口也向右滑动一个（窗口平移）
                 */
                if (count > 2) {
                    // 如果移除左侧元素的计数, 结果为0, count--
                    // if内的条件无论如何都执行, 代表先减小left指针指向元素的计数, 再右移left指针
                    // 直到count <= 2
                    if (--map[s.charAt(left++)] == 0) {
                        count--;
                    }
                }
            }
            return right - left;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
