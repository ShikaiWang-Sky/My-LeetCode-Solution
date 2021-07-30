//给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。 
//
// 请返回所有可行解 s 中最长长度。 
//
// 
//
// 示例 1： 
//
// 输入：arr = ["un","iq","ue"]
//输出：4
//解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
// 
//
// 示例 2： 
//
// 输入：arr = ["cha","r","act","ers"]
//输出：6
//解释：可能的解答有 "chaers" 和 "acters"。
// 
//
// 示例 3： 
//
// 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
//输出：26
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 16 
// 1 <= arr[i].length <= 26 
// arr[i] 中只含有小写英文字母 
// 
// Related Topics 位运算 数组 字符串 回溯 
// 👍 174 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：串联字符串的最大长度
public class P1239MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        Solution solution = new P1239MaximumLengthOfAConcatenatedStringWithUniqueCharacters().new Solution();
        // TO TEST
        List<String> arr = new ArrayList<>(Arrays.asList("cha","r","act","ers"));
        System.out.println(solution.maxLength(arr));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int res=0;
        public int maxLength(List<String> arr) {
            List<Integer> masks =new ArrayList<>();
            for (String s : arr) {
                int mask = 0;
                for (int i = 0; i < s.length(); i++) {
                    int charInt = s.charAt(i) - 'a';
                    if (((mask >> charInt) & 1) != 0) {
                        mask = 0;
                        break;
                    }
                    mask = mask | (1 << charInt);
                }
                if (mask > 0) {
                    masks.add(mask);
                }
            }
            backtrack(masks,0,0);
            return res;
        }

        public void backtrack(List<Integer> masks, int pos, int mask){
            if (pos == masks.size()) {
                res = Math.max(res, Integer.bitCount(mask));
                return;
            }
            if ((mask & masks.get(pos)) == 0) {
                backtrack(masks, pos + 1, mask | masks.get(pos));
            }
            // 上一个递归结束后return出来从头执行下面的语句(pos=0)
            backtrack(masks, pos + 1, mask);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
