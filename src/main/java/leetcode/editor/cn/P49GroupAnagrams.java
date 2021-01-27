//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 640 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：字母异位词分组
public class P49GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new P49GroupAnagrams().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            int length = strs.length;
            // 非空判断
            if (strs == null || length == 0) {
                return new ArrayList<>();
            }
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                // 排序后的字符串作为key, 同时这个key也是每次输入字符串排序后的结果
                String key = String.valueOf(array);
                // 如果map中没有这个排序后的结果, 则为新的单词, 常见一个空的kv
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                // 将本次的字符串放到map的list中 => 如果此key已经有了, 则追加list, 否则向新的list添加第一个元素
                map.get(key).add(str);
            }
            // 返回map的全部value, 转为一个List
            return new ArrayList<>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
