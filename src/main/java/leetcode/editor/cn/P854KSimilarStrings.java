//如果可以通过将 A 中的两个小写字母精确地交换位置 K 次得到与 B 相等的字符串，我们称字符串 A 和 B 的相似度为 K（K 为非负整数）。 
//
// 给定两个字母异位词 A 和 B ，返回 A 和 B 的相似度 K 的最小值。 
//
// 
//
// 示例 1： 
//
// 输入：A = "ab", B = "ba"
//输出：1
// 
//
// 示例 2： 
//
// 输入：A = "abc", B = "bca"
//输出：2
// 
//
// 示例 3： 
//
// 输入：A = "abac", B = "baca"
//输出：2
// 
//
// 示例 4： 
//
// 输入：A = "aabc", B = "abca"
//输出：2 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length == B.length <= 20 
// A 和 B 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母。 
// 
// Related Topics 广度优先搜索 字符串 
// 👍 99 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：相似度为 K 的字符串
public class P854KSimilarStrings {
    public static void main(String[] args) {
        Solution solution = new P854KSimilarStrings().new Solution();
        // TO TEST
        System.out.println(solution.kSimilarity("abccaacceecdeea", "bcaacceeccdeaae"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String swap(String s, int index1, int index2) {
            char[] chars = s.toCharArray();
            char temp = chars[index1];
            chars[index1] = chars[index2];
            chars[index2] = temp;
            return String.valueOf(chars);
        }

        public List<String> neighbors(String s1, String s2) {
            List<String> res = new ArrayList<>();
            int i = 0;
            // 找到第一个不同的字符
            for (; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i))
                    break;
            }

            //char[] s1c = s1.toCharArray();
            // 遍历,对于S1,从不同的字符后一个开始招,直到找到与S2第一个不同字符相同的字符为止, 交换
            for (int j = i + 1; j < s1.length(); j++) {
                if (s1.charAt(j) == s2.charAt(i)) {
                    res.add(swap(s1, i, j));
                }
            }
            return res;

        }

        public int kSimilarity(String s1, String s2) {
            if (s1.equals(s2))
                return 0;
            Queue<String> queue = new ArrayDeque<>();
            queue.add(s1);

            // 对到达每个交换后的字符串的次数计数
            Map<String, Integer> swapCount = new HashMap<>();
            swapCount.put(s1, 0);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (current.equals(s2))
                    return swapCount.get(current);
                for (String currentS1 : neighbors(current, s2)) {
                    // 当前MAP中没有当前交换队列中的字符串
                    if (!swapCount.containsKey(currentS1)) {
                        // 将交换后的字符串放入队列中, 并将交换次数加一
                        swapCount.put(currentS1, swapCount.get(current) + 1);
                        // 将交换后的字符串放入队列
                        queue.add(currentS1);
                    }
                }
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
